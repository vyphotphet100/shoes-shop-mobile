package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.squareup.picasso.Picasso;

import org.springframework.http.HttpStatus;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ProductCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.BrandCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.CategoryCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.ProductCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.HomePageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.LoginPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.BrandEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.CategoryEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.HomePageEntity;

public class HomeActivity extends AppCompatActivity {

    private HomeActivity context;
    public NestedScrollView srv;
    public ConstraintLayout container;
    public LinearLayout itemContainer, cate1Container, cate2Container;
    public ConstraintLayout nikeBannerConsLayout;
    public ConstraintLayout adidasBannerConsLayout;
    public ConstraintLayout pumaBannerConsLayout;
    public ConstraintLayout converseBannerConsLayout;
    public HorizontalScrollView banner;
    public HeaderCard headerCard;
    public ImageView imgDown;
    public FrameLayout frameHeaderContainer;
    public TextView txtBrand1, txtBrand2,
            txtBrand3, txtBrand4,
            txtBrand5, txtBrand6,
            txtBrand7,
            txtCategory1, txtCategory2, txtBrand1ShopNow;

    private void mapping() {
        this.context = this;
        srv = findViewById(R.id.homeAct_srv);
        container = findViewById(R.id.homeAct_container);
        itemContainer = findViewById(R.id.homeAct_itemContainer);
        cate1Container = findViewById(R.id.homeAct_cate1Container);
        cate2Container = findViewById(R.id.homeAct_cate2Container);
        nikeBannerConsLayout = findViewById(R.id.homeAct_nikeBannerConsLayout);
        adidasBannerConsLayout = findViewById(R.id.homeAct_adidasBannerConsLayout);
        pumaBannerConsLayout = findViewById(R.id.homeAct_pumaBannerConsLayout);
        converseBannerConsLayout = findViewById(R.id.homeAct_converseBannerConsLayout);
        banner = findViewById(R.id.homeAct_hsvBannerContainer);
        headerCard = new HeaderCard(this);
        frameHeaderContainer = findViewById(R.id.homeAct_headerContainer);
        imgDown = findViewById(R.id.homeAct_imgDown);
        txtBrand1 = findViewById(R.id.homeAct_txtBrand1);
        txtBrand2 = findViewById(R.id.homeAct_txtBrand2);
        txtBrand3 = findViewById(R.id.homeAct_txtBrand3);
        txtBrand4 = findViewById(R.id.homeAct_txtBrand4);
        txtBrand5 = findViewById(R.id.homeAct_txtBrand5);
        txtBrand6 = findViewById(R.id.homeAct_txtBrand6);
        txtBrand7 = findViewById(R.id.homeAct_txtBrand7);
        txtCategory1 = findViewById(R.id.homeAct_txtCategory1);
        txtCategory2 = findViewById(R.id.homeAct_txtCategory2);
        txtBrand1ShopNow = findViewById(R.id.homeAct_txtBrand1ShopNow);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mapping();

        setHeaderCard();
        setEvent();
        loadInitData();

        txtBrand1ShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstant.loggedInUserEntity != null){
                    Intent intent = new Intent(HomeActivity.this, CheckOutActivity.class);
                    startActivity(intent);
//                    finish();
                }

            }
        });
    }

    private void setHeaderCard() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void setEvent() {
//        headerCard
    }


    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                HomePageCrane homePageCrane = new HomePageCrane();
                HomePageEntity homePageEntity = homePageCrane.getDataHomePage();

                LoginPageCrane loginPageCrane = new LoginPageCrane();
                UserEntity userEntity = loginPageCrane.login("user5", "123456");
                if (userEntity.getHttpStatus() == HttpStatus.OK)
                    AppConstant.loggedInUserEntity = userEntity;

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtBrand1.setText(homePageEntity.getBrandEntities().get(0).getName());
                        txtBrand2.setText(homePageEntity.getBrandEntities().get(1).getName());
                        txtBrand3.setText(homePageEntity.getBrandEntities().get(2).getName());
                        txtBrand4.setText(homePageEntity.getBrandEntities().get(3).getName());
                        txtBrand5.setText(homePageEntity.getBrandEntities().get(4).getName());
                        txtBrand6.setText(homePageEntity.getBrandEntities().get(5).getName());
                        txtBrand7.setText(homePageEntity.getBrandEntities().get(6).getName());

                        txtCategory1.setText(homePageEntity.getCategoryEntities().get(0).getName());
                        txtCategory2.setText(homePageEntity.getCategoryEntities().get(1).getName());

                        cate1Container.removeAllViews();
                        cate2Container.removeAllViews();

                        for (ProductEntity productEntity : homePageEntity.getCate1Products()) {
                            ProductCard productCard = new ProductCard(context);
                            productCard.txtName.setText(productEntity.getTitle());
                            productCard.txtPrice.setText(String.valueOf(productEntity.getPrice()));
                            Picasso.with(context)
                                    .load(AppConstant.BASE_URL + productEntity.getPictureUrl())
                                    .into(productCard.img);
                            cate1Container.addView(productCard.getView());
                        }

                        for (ProductEntity productEntity : homePageEntity.getCate2Products()) {
                            ProductCard productCard = new ProductCard(context);
                            productCard.txtName.setText(productEntity.getTitle());
                            productCard.txtPrice.setText(String.valueOf(productEntity.getPrice()));
                            Picasso.with(context)
                                    .load(AppConstant.BASE_URL + productEntity.getPictureUrl())
                                    .into(productCard.img);
                            cate2Container.addView(productCard.getView());
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }


}
