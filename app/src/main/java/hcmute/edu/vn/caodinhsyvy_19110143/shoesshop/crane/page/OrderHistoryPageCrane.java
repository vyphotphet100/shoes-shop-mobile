package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.HomePageEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.OrderHistoryPageEntity;

// Crane package: to communicate with server through API
public class OrderHistoryPageCrane {
    private final String subUrl = "page/order-history";

    // get data for Order History Activity from server
    public OrderHistoryPageEntity getDataOrderHistory() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization",
                    Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<OrderHistoryPageEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                        HttpMethod.GET, entity, OrderHistoryPageEntity.class);
        return resp.getBody();
    }
}
