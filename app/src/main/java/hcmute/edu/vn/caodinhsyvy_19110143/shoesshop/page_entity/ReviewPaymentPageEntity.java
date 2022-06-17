package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewPaymentPageEntity extends BasePageEntity implements Serializable {

    // declare the necessary variables used / properties
    private UserEntity userEntity;
    private List<OrderItemEntity> readyOrderItems = new ArrayList<>();
    private String description;
    private Float total;
    private Float exchangeRate;
    private String paymentId;
    private String PayerID;
    private String redirectLink;

    //get and set all value to all variables
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<OrderItemEntity> getReadyOrderItems() {
        return readyOrderItems;
    }

    public void setReadyOrderItems(List<OrderItemEntity> readyOrderItems) {
        this.readyOrderItems = readyOrderItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public void setRedirectLink(String redirectLink) {
        this.redirectLink = redirectLink;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayerID() {
        return PayerID;
    }

    public void setPayerID(String payerID) {
        PayerID = payerID;
    }
}
