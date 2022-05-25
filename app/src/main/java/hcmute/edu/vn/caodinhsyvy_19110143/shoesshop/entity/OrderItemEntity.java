package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemEntity extends BaseEntity<OrderItemEntity>{

    private Integer id;
    private Integer quantityBought;
    private Float totalCost;
    private UserEntity customer;
    private ProductEntity product;
    private PaymentEntity payment;
}
