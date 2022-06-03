package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;

public class OrderHistoryPageEntity extends BasePageEntity implements Serializable {
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

    public List<OrderItemEntity> getOrderItemEntities() {
        return orderItemEntities;
    }

    public void setOrderItemEntities(List<OrderItemEntity> orderItemEntities) {
        this.orderItemEntities = orderItemEntities;
    }
}
