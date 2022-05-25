package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BrandEntity extends BaseEntity<BrandEntity>{
    private String code;
    private String name;
    private List<ProductEntity> products = new ArrayList<>();
}
