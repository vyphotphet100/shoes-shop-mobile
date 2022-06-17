package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.CheckOutPageEntity;

// Crane package: to communicate with server through API
public class CheckOutPageCrane {
    String subUrl = "page/check-out";

    // get data for check out activity from server
    public CheckOutPageEntity getDataCheckOutPage() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization", Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<CheckOutPageEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                        HttpMethod.GET, entity, CheckOutPageEntity.class);
        return resp.getBody();

    }

}
