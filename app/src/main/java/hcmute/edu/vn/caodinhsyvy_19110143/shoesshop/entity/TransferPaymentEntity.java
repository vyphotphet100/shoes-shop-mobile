package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferPaymentEntity extends BaseEntity<TransferPaymentEntity> implements Serializable {
    // declare the necessary variables used / properties of transfer payment entity
    private Integer id;
    private Date transferDate;
    private Float amount;
    private UserEntity admin;
    private UserEntity seller;
    private PaymentEntity payment;

    //get and set all value to all variables
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
}
