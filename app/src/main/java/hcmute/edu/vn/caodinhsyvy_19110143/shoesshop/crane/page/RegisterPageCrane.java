package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

// Crane package: to communicate with server through API
public class RegisterPageCrane {
    private final String subUrl = "page/register";

    // send user info to server to register
    public UserEntity register(UserEntity userEntity) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserEntity> entity = new HttpEntity<UserEntity>(userEntity, headers);
        ResponseEntity<UserEntity> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                        HttpMethod.POST, entity, UserEntity.class);
        return resp.getBody();

    }
}
