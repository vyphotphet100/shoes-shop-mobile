package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransferPaymentEntity extends BaseEntity<TransferPaymentEntity>{
    private Integer id;
    private Date transferDate;
    private Float amount;
    private UserEntity admin;
    private UserEntity seller;
    private PaymentEntity payment;

}
