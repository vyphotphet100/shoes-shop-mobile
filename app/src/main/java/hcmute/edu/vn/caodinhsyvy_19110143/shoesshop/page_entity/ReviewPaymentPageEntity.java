package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewPaymentPageEntity extends BasePageEntity{
    private UserEntity userEntity;
    private List<OrderItemEntity> readyOrderItems = new ArrayList<>();
    private String description;
    private Float total;
    private Float exchangeRate;

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
}
