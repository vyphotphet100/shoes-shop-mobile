package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckOutPageEntity extends BasePageEntity{
    private UserEntity userEntity;
    private List<OrderItemEntity> readyOrderItems = new ArrayList<>();
    private Float total;

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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
