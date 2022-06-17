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

    // to contain header card view
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;

    // objects to map to ui
    public TextView txtTitle, txtBrand, txtCategory, txtAvailability, txtPrice,
            txtDec, txtInc, txtAddToCart, txtDescription;
    public ImageView img;
    public EditText edtTxtQuantity;
    public LinearLayout cateContainer;

    public String code;


    //Function to map objects in code to associated views in UI
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

    //add headerCard into frameHeaderContainer when activity_product_detail loaded
    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    //load data from api before beginning activity
    private void loadInitData() {
        // loading screen
        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                ProductDetailPageCrane productDetailPageCrane = new ProductDetailPageCrane();
                ProductDetailPageEntity productDetailPageEntity = productDetailPageCrane.getDataProductDetail(code);

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ProductEntity productEntity = productDetailPageEntity.getProductEntity();
                        //get title for the product
                        txtTitle.setText(productEntity.getTitle());
                        //get brand for the product
                        txtBrand.setText("Brand: " + productEntity.getBrand().getName());
                        //get category for the product
                        txtCategory.setText("Category: " + productEntity.getCategory().getName());
                        //get price for the product
                        txtPrice.setText("$" + productEntity.getPrice());
                        //get description for the product
                        txtDescription.setText(productEntity.getDescription());
                        //get picture for the product
                        Picasso.with(context)
                                .load(AppConstant.BASE_URL + productEntity.getPictureUrl())
                                .into(img);

                        //add product information into productDetailAct_cateContainer
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

    // set events to objects
    private void setEvent() {
        //set event click for productDetailAct_txtAddToCart
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

        //set event click for productDetailAct_txtInc
        txtInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTxtQuantity.setText(String.valueOf(Integer.valueOf(edtTxtQuantity.getText().toString()) + 1));
            }
        });

        //set event click for productDetailAct_txtDec
        txtDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtTxtQuantity.getText().toString().trim().equals("1"))
                    edtTxtQuantity.setText(String.valueOf(Integer.valueOf(edtTxtQuantity.getText().toString()) - 1));
            }
        });
    }

}