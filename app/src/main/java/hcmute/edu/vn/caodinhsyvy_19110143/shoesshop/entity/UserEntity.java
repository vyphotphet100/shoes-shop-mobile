package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserEntity extends BaseEntity<UserEntity> {
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

}
