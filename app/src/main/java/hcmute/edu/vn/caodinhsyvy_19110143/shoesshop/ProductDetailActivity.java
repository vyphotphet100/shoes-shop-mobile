package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ProductCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ProductDetailPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ProductDetailPageEntity;

public class ProductDetailActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frameHeaderContainer;
    public TextView txtTitle, txtBrand, txtCategory, txtAvailability, txtPrice,
            txtDec, txtInc, txtAddToCart, txtDescription;
    public ImageView img;
    public EditText edtTxtQuantity;
    public LinearLayout cateContainer;

    public HeaderCard headerCard;
    public String code;
//    public ProductEntity productEntity;

    private void mapping() {
        this.context = this;
        code = getIntent().getExtras().getString("code");
        frameHeaderContainer = findViewById(R.id.productDetailAct_headerContainer);
        headerCard = new HeaderCard(context);
        txtTitle = findViewById(R.id.productDetailAct_txtTitle);
        txtBrand = findViewById(R.id.productDetailAct_txtBrand);
        txtCategory = findViewById(R.id.productDetailAct_txtCategory);
        txtAvailability = findViewById(R.id.productDetailAct_txtAvailability);
        txtAddToCart = findViewById(R.id.productDetailAct_txtAddToCart);
        txtInc = findViewById(R.id.productDetailAct_txtInc);
        txtDec = findViewById(R.id.productDetailAct_txtDec);
        txtPrice = findViewById(R.id.productDetailAct_txtPrice);
        txtDescription = findViewById(R.id.productDetailAct_txtDescription);
        edtTxtQuantity = findViewById(R.id.productDetailAct_edtTxtQuantity);
        img = findViewById(R.id.productDetailAct_img);
        cateContainer = findViewById(R.id.productDetailAct_cateContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        mapping();
        initSetupLayout();

        loadInitData();

        setEvent();
    }

    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                ProductDetailPageCrane productDetailPageCrane = new ProductDetailPageCrane();
                ProductDetailPageEntity productDetailPageEntity = productDetailPageCrane.getDataPageDetail(code);

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ProductEntity productEntity = productDetailPageEntity.getProductEntity();
                        txtTitle.setText(productEntity.getTitle());
                        txtBrand.setText("Brand: " + productEntity.getBrand().getName());
                        txtCategory.setText("Category: " + productEntity.getCategory().getName());
                        txtPrice.setText("$" + productEntity.getPrice());
                        txtDescription.setText(productEntity.getDescription());
                        Picasso.with(context)
                                .load(AppConstant.BASE_URL + productEntity.getPictureUrl())
                                .into(img);

                        cateContainer.removeAllViews();
                        for (ProductEntity product : productDetailPageEntity.getRelatedProducts()) {
                            ProductCard productCard = new ProductCard(context, product);
                            productCard.txtName.setText(product.getTitle());
                            productCard.txtPrice.setText(String.valueOf(product.getPrice()));
                            Picasso.with(context)
                                    .load(AppConstant.BASE_URL + product.getPictureUrl())
                                    .into(productCard.img);

                            productCard.getView().setLayoutParams(new ViewGroup.LayoutParams((int) (1100 / Resources.getSystem().getDisplayMetrics().density), ViewGroup.LayoutParams.WRAP_CONTENT));
                            cateContainer.addView(productCard.getView());
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    private void setEvent() {
        txtAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setCode(code);
                ProductCard productCard = new ProductCard(context, productEntity);
                productCard.quantity = Integer.valueOf(edtTxtQuantity.getText().toString());
                productCard.txtAddToCard.callOnClick();
            }
        });

        txtInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTxtQuantity.setText(String.valueOf(Integer.valueOf(edtTxtQuantity.getText().toString()) + 1));
            }
        });

        txtDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtTxtQuantity.getText().toString().trim().equals("1"))
                    edtTxtQuantity.setText(String.valueOf(Integer.valueOf(edtTxtQuantity.getText().toString()) - 1));
            }
        });
    }

}