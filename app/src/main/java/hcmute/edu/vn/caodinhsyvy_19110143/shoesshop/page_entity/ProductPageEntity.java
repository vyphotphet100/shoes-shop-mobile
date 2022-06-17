package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPageEntity extends BasePageEntity implements Serializable {
    // declare the necessary variables used / properties
    private List<ProductEntity> products = new ArrayList<>();

    //get and set all value to all variables
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
