package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity extends BaseEntity<UserEntity> implements Serializable {
    // declare the necessary variables used / properties of user entity
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthday;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private Boolean isActive;
    private RoleEntity role;
    private PaymentMethodEntity paymentMethod;
    private List<ProductEntity> products = new ArrayList<>();
    private List<OrderItemEntity> orderItems = new ArrayList<>();
    private List<TransferPaymentEntity> adminTransferPayments = new ArrayList<>();
    private List<TransferPaymentEntity> sellerTransferPayments = new ArrayList<>();
    private String chatPluginScript;
    private String confirmPassword;
    private List<Object> authorities;

    //get and set all value to all variables
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public List<TransferPaymentEntity> getAdminTransferPayments() {
        return adminTransferPayments;
    }

    public void setAdminTransferPayments(List<TransferPaymentEntity> adminTransferPayments) {
        this.adminTransferPayments = adminTransferPayments;
    }

    public List<TransferPaymentEntity> getSellerTransferPayments() {
        return sellerTransferPayments;
    }

    public void setSellerTransferPayments(List<TransferPaymentEntity> sellerTransferPayments) {
        this.sellerTransferPayments = sellerTransferPayments;
    }

    public String getChatPluginScript() {
        return chatPluginScript;
    }

    public void setChatPluginScript(String chatPluginScript) {
        this.chatPluginScript = chatPluginScript;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Object> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Object> authorities) {
        this.authorities = authorities;
    }
}
