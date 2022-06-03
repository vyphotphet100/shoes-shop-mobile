package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThanksPageEntity extends BasePageEntity implements Serializable {
    private UserEntity userEntity;
    private Boolean paymentResult;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Boolean getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(Boolean paymentResult) {
        this.paymentResult = paymentResult;
    }
}
