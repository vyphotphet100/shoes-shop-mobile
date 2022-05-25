package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryEntity extends BaseEntity<CategoryEntity>{

    private String code;
    private String name;
    private List<ProductEntity> products = new ArrayList<>();
}
