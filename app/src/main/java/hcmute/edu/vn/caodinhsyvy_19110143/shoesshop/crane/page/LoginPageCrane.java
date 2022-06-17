package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

// Crane package: to communicate with server through API
public class LoginPageCrane {
    private final String subUrl = "login";

    // send username and password to server to login
    public UserEntity login(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserEntity> entity = new HttpEntity<UserEntity>(userEntity, headers);
        try {
            ResponseEntity<UserEntity> resp =
                    restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                            HttpMethod.POST, entity, UserEntity.class);

            return resp.getBody();
        } catch (Exception ex) {
            UserEntity user = new UserEntity();
            user.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            user.setMessage("Username or Password is invalid. Please check again!");
            return user;
        }

    }
}
