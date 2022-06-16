package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.squareup.picasso.Picasso;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ProductCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.HomePageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.HomePageEntity;

public class HomeActivity extends AppCompatActivity {

    public static Boolean checkActivity = false;
    private Context context;
    public NestedScrollView srv;
    public ConstraintLayout container;
    public LinearLayout itemContainer, cate1Container, cate2Container;
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;
    public ConstraintLayout nikeBannerConsLayout;
    public ConstraintLayout adidasBannerConsLayout;
    public ConstraintLayout pumaBannerConsLayout;
    public ConstraintLayout converseBannerConsLayout;
    public HorizontalScrollView banner;
    public FrameLayout frm;
    public TextView txtBrand1, txtBrand2,
            txtBrand3, txtBrand4,
            txtBrand5, txtBrand6,
            txtBrand7,
            txtCategory1, txtCategory2,
            txtBrand1ShopNow, txtBrand2ShopNow, txtBrand3ShopNow, txtBrand4ShopNow;

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
        frm = findViewById(R.id.homeAct_frm);
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
        txtBrand2ShopNow = findViewById(R.id.homeAct_txtBrand2ShopNow);
        txtBrand3ShopNow = findViewById(R.id.homeAct_txtBrand3ShopNow);
        txtBrand4ShopNow = findViewById(R.id.homeAct_txtBrand4ShopNow);

        frameHeaderContainer = findViewById(R.id.homeAct_headerContainer);
        headerCard = new HeaderCard(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mapping();
        initSetupLayout();
        loadInitData();
        setEvent();

    }

    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                HomePageCrane homePageCrane = new HomePageCrane();
                HomePageEntity homePageEntity = homePageCrane.getDataHomePage();

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
                            ProductCard productCard = new ProductCard(context, productEntity);
                            productCard.txtName.setText(productEntity.getTitle());
                            productCard.txtPrice.setText(String.valueOf(productEntity.getPrice()));
                            Picasso.with(context)
                                    .load(AppConstant.BASE_URL + productEntity.getPictureUrl())
                                    .into(productCard.img);

                            productCard.getView().setLayoutParams(new ViewGroup.LayoutParams((int) (1100 / Resources.getSystem().getDisplayMetrics().density), ViewGroup.LayoutParams.WRAP_CONTENT));
                            cate1Container.addView(productCard.getView());
                        }

                        for (ProductEntity productEntity : homePageEntity.getCate2Products()) {
                            ProductCard productCard = new ProductCard(context, productEntity);
                            productCard.txtName.setText(productEntity.getTitle());
                            productCard.txtPrice.setText(String.valueOf(productEntity.getPrice()));
                            Picasso.with(context)
                                    .load(AppConstant.BASE_URL + productEntity.getPictureUrl())
                                    .into(productCard.img);

                            productCard.getView().setLayoutParams(new ViewGroup.LayoutParams((int) (1100 / Resources.getSystem().getDisplayMetrics().density), ViewGroup.LayoutParams.WRAP_CONTENT));
                            cate2Container.addView(productCard.getView());
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void setEvent() {
        txtBrand1ShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand1ShopNow.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand1ShopNow.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND1");
                context.startActivity(intent);
            }
        });

        txtBrand2ShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand2ShopNow.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand2ShopNow.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND2");
                context.startActivity(intent);
            }
        });

        txtBrand3ShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand3ShopNow.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand3ShopNow.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND3");
                context.startActivity(intent);
            }
        });

        txtBrand4ShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand4ShopNow.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand4ShopNow.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND4");
                context.startActivity(intent);
            }
        });

        txtBrand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand5.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND5");
                context.startActivity(intent);
            }
        });

        txtBrand6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand6.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND6");
                context.startActivity(intent);
            }
        });

        txtBrand7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBrand7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtBrand7.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "brandCode=BRAND7");
                context.startActivity(intent);
            }
        });
    }


}
