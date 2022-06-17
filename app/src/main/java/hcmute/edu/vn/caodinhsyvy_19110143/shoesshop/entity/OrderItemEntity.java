package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemEntity extends BaseEntity<OrderItemEntity> implements Serializable {
    // declare the necessary variables used / properties of order item entity
    private Integer id;
    private Integer quantityBought;
    private Float totalCost;
    private UserEntity customer;
    private ProductEntity product;
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

    public Integer getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(Integer quantityBought) {
        this.quantityBought = quantityBought;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
}
