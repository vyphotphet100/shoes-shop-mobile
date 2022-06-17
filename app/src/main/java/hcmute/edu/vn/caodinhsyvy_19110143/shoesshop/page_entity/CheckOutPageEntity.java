package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckOutPageEntity extends BasePageEntity implements Serializable {
    // declare the necessary variables used / properties
    private UserEntity userEntity;
    private List<OrderItemEntity> readyOrderItems = new ArrayList<>();
    private Float total;

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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
