package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import java.io.Serializable;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;

public class OrderDetailPageEntity extends BasePageEntity implements Serializable {

    // declare the necessary variables used / properties
    private OrderItemEntity orderItemEntity;

    //get and set all value to all variables
    public OrderItemEntity getOrderItemEntity() {
        return orderItemEntity;
    }

    public void setOrderItemEntity(OrderItemEntity orderItemEntity) {
        this.orderItemEntity = orderItemEntity;
    }
}
