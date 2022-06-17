package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailPageEntity extends BasePageEntity implements Serializable {

    // declare the necessary variables used / properties
    private ProductEntity productEntity;

    List<ProductEntity> relatedProducts = new ArrayList<>();

    //get and set all value to all variables
    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public List<ProductEntity> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(List<ProductEntity> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }
}
