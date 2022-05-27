package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntity extends BaseEntity<CategoryEntity>{

    private String code;
    private String name;
    private List<ProductEntity> products = new ArrayList<>();

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
