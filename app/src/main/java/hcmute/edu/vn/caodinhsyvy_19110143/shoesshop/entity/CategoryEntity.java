package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryEntity extends BaseEntity<CategoryEntity> implements Serializable {
    // declare the necessary variables used / properties of category entity
    private String code;
    private String name;
    private List<ProductEntity> products = new ArrayList<>();

    //get and set all value to all variables
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
