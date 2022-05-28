package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentEntity extends BaseEntity<PaymentEntity>{

    private Integer id;
    private Date paymentDate;
    private Date shipmentDate;
    private OrderItemEntity orderItem;
    private TransferPaymentEntity transferPayment;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public OrderItemEntity getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItemEntity orderItem) {
        this.orderItem = orderItem;
    }

    public TransferPaymentEntity getTransferPayment() {
        return transferPayment;
    }

    public void setTransferPayment(TransferPaymentEntity transferPayment) {
        this.transferPayment = transferPayment;
    }
}
