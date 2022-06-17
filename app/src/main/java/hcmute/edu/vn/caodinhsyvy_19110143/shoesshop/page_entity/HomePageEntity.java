package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.BrandEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.CategoryEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomePageEntity extends BasePageEntity implements Serializable {

    // declare the necessary variables used / properties
    private List<CategoryEntity> categoryEntities = new ArrayList<>();
    private List<BrandEntity> brandEntities = new ArrayList<>();
    private List<ProductEntity> cate1Products = new ArrayList<>();
    private List<ProductEntity> cate2Products = new ArrayList<>();

    //get and set all value to all variables
    public List<CategoryEntity> getCategoryEntities() {
        return categoryEntities;
    }

    public void setCategoryEntities(List<CategoryEntity> categoryEntities) {
        this.categoryEntities = categoryEntities;
    }

    public List<BrandEntity> getBrandEntities() {
        return brandEntities;
    }

    public void setBrandEntities(List<BrandEntity> brandEntities) {
        this.brandEntities = brandEntities;
    }

    public List<ProductEntity> getCate1Products() {
        return cate1Products;
    }

    public void setCate1Products(List<ProductEntity> cate1Products) {
        this.cate1Products = cate1Products;
    }

    public List<ProductEntity> getCate2Products() {
        return cate2Products;
    }

    public void setCate2Products(List<ProductEntity> cate2Products) {
        this.cate2Products = cate2Products;
    }
}
