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
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.PaymentMethodEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ReviewPaymentPageEntity;

// Crane package: to communicate with server through API
public class ReviewPaymentPageCrane {
    private final String subUrl = "page/review-payment";

    // get data for Review Payment Activity from server
    public ReviewPaymentPageEntity getDataReviewPaymentPage(String phone, String address, Integer paymentMethod) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhone(phone);
        userEntity.setAddress(address);
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(paymentMethod);
        userEntity.setPaymentMethod(paymentMethodEntity);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization",
                    Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<UserEntity> entity = new HttpEntity<UserEntity>(userEntity, headers);
        ResponseEntity<ReviewPaymentPageEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                        HttpMethod.POST, entity, ReviewPaymentPageEntity.class);
        return resp.getBody();

    }
}
