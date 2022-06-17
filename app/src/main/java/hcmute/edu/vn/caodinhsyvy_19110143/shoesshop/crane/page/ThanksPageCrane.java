package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ThanksPageEntity;

// Crane package: to communicate with server through API
public class ThanksPageCrane {
    private final String subUrl = "page/thanks";

    // get data from server for Thanks Activity
    public ThanksPageEntity getDataThanksPage(String paymentId, String PayerID) {

        StringBuilder params = new StringBuilder("?");
        if (paymentId != null && PayerID != null) {
            params.append("paymentId=" + paymentId + "&");
            params.append("PayerID=" + PayerID);
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization",
                    Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ThanksPageEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + params.toString(),
                        HttpMethod.POST, entity, ThanksPageEntity.class);
        return resp.getBody();

    }
}
