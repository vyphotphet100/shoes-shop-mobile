package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ProductDetailPageEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ProductPageEntity;

// Crane package: to communicate with server through API
public class ProductDetailPageCrane {
    private final String subUrl = "page/product-detail";

    // get data for Product Detail Activity from server
    public ProductDetailPageEntity getDataProductDetail(String code) {
        String params = "?code=" + code;

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization",
                    Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<ProductDetailPageEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + params,
                        HttpMethod.GET, entity, ProductDetailPageEntity.class);
        return resp.getBody();
    }
}
