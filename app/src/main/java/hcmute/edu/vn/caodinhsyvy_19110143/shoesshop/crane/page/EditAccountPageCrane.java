package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

// Crane package: to communicate with server through API
public class EditAccountPageCrane {
    private final String subUrl = "/customer/my-account/edit-account";

    // send user info to server to edit
    public UserEntity editAccount(String firstName, String lastName, String phone, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setPhone(phone);
        userEntity.setEmail(email);
        userEntity.setBirthday(AppConstant.loggedInUserEntity.getBirthday());
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization",
                    Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<UserEntity> entity = new HttpEntity<UserEntity>(userEntity, headers);
        try {
            ResponseEntity<UserEntity> resp =
                    restTemplate.exchange(AppConstant.BASE_URL + subUrl,
                            HttpMethod.PUT, entity, UserEntity.class);

            return resp.getBody();
        } catch (Exception ex) {
            UserEntity user = new UserEntity();
            user.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            user.setMessage("Something went wrong. Please check again!");
            return user;
        }

    }
}
