package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentEntity extends BaseEntity<PaymentEntity>{

    private Integer id;
    private Date paymentDate;
    private Date shipmentDate;
    private OrderItemEntity orderItem;
    private TransferPaymentEntity transferPayment;

}
