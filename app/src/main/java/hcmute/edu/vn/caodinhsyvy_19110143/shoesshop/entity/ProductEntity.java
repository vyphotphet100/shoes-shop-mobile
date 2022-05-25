package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductEntity extends BaseEntity<ProductEntity>{
    private String code;
    private String title;
    private String description;
    private Integer size;
    private float price;
    private Integer quantity;
    private Integer rating;
    private Boolean inStock;
    private String pictureUrl;
    private Boolean isAvailable;
    private UserEntity seller;
    private CategoryEntity category;
    private BrandEntity brand;
    private List<OrderItemEntity> orderItems = new ArrayList<>();
}
