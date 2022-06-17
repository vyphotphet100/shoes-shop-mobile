package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ShoppingCartPageEntity;

// Crane package: to communicate with server through API
public class ShoppingCartPageCrane {
    String subUrl = "page/shopping-cart";

    // get data from server for Shopping Cart Activity
    public ShoppingCartPageEntity getDataShoppingCartPage() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization", Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<ShoppingCartPageEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                        HttpMethod.GET, entity, ShoppingCartPageEntity.class);
        return resp.getBody();

    }

    // send DELETE method request to server to delete order item in shopping cart
    public Boolean deleteOrderItemById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization", Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<OrderItemEntity> resp =
                restTemplate.exchange(AppConstant.BASE_URL + "/customer/paying/shopping-cart/delete-order-item?id={id}",
                        HttpMethod.DELETE, entity, OrderItemEntity.class, id);

        OrderItemEntity orderItemEntity = resp.getBody();
        if (orderItemEntity.getHttpStatus() == HttpStatus.OK)
            return true;
        return false;
    }

    // send info of chosen order items to server when user clicks Check Out button
    public Boolean checkOut(HashMap<String, Integer> orderItemIdsNeededUpdateQuantity) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization", Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.getListRequest().put("orderItemIdsNeededUpdateQuantity", orderItemIdsNeededUpdateQuantity);
        HttpEntity<OrderItemEntity> entity = new HttpEntity<OrderItemEntity>(orderItemEntity, headers);
        ResponseEntity<OrderItemEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + "/action/check-out",
                        HttpMethod.POST, entity, OrderItemEntity.class);

        OrderItemEntity resEntity = resp.getBody();
        if (resEntity.getHttpStatus() == HttpStatus.OK)
            return true;
        return false;
    }

    // send productCode to server to add that product to cart
    public OrderItemEntity addToCart(Context context, String productCode, Integer quantity) {
        OrderItemEntity errorEntity = new OrderItemEntity();
        errorEntity.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        if (!AppConstant.checkLoggedIn(context))
            return errorEntity;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization", Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.getListRequest().put("productCode", productCode);
        orderItemEntity.getListRequest().put("quantity", quantity);
        HttpEntity<OrderItemEntity> entity = new HttpEntity<OrderItemEntity>(orderItemEntity, headers);
        ResponseEntity<OrderItemEntity> resp = null;
//        restTemplate.exchange(AppConstant.BASE_URL + "/customer/product/add-product-to-cart",
//                HttpMethod.POST, entity, OrderItemEntity.class);

        try {
            resp = restTemplate.exchange(AppConstant.BASE_URL + "/customer/product/add-product-to-cart",
                    HttpMethod.POST, entity, OrderItemEntity.class);
        } catch (HttpStatusCodeException ex) {
            System.out.println(ex.getResponseBodyAsString());
            ObjectMapper objectMapper = new ObjectMapper();
            OrderItemEntity resOrderItem = new OrderItemEntity();
            try {
                resOrderItem = objectMapper.readValue(ex.getResponseBodyAsString(), OrderItemEntity.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resOrderItem;
        }

        return resp.getBody();
    }
}
