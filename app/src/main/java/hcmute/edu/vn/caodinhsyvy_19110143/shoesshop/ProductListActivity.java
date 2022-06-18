package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ProductCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;

public class ProductListActivity extends AppCompatActivity {
    private Context context;

    // to contain header card view
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;

    // to show product list page
    public WebView webView;
    public String params;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        frameHeaderContainer = findViewById(R.id.productListAct_headerContainer);
        headerCard = new HeaderCard(context);
        webView = findViewById(R.id.productListAct_webView);
        try {
            params = getIntent().getExtras().getString("params");
        } catch (Exception ex) {
            params = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mapping();
        initSetupLayout();


        // set events for web view
        final ProgressDialog[] progressDialog = {ProgressDialog.show(context, "Noriva",
                "Loading...", true)};

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressDialog[0] != null)
                    progressDialog[0].dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressDialog[0] = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);

                // if url not contain 'shoesshop-app://'
                if (!url.contains("shoesshop-app://"))
                    return super.shouldOverrideUrlLoading(view, url);

                // trig 'Add product to cart' function
                if (url.contains("/add-product-to-cart")) {
                    // get productCode
                    StringBuilder productCode = new StringBuilder();
                    if (url.contains("productCode"))
                        for (int i = url.indexOf("productCode"); i < url.length(); i++) {
                            if (url.charAt(i) == '&')
                                break;

                            productCode.append(url.charAt(i));
                        }
                    productCode = new StringBuilder(productCode.toString().split("=")[1]);

                    ProductCard productCard = new ProductCard(context, new ProductEntity());
                    productCard.productEntity.setCode(productCode.toString());
                    productCard.txtAddToCard.callOnClick();
                    progressDialog[0].dismiss();
                }

                // open product detail activity
                else if (url.contains("/product-detail")) {
                    // get productCode
                    StringBuilder productCode = new StringBuilder();
                    if (url.contains("code"))
                        for (int i = url.indexOf("code"); i < url.length(); i++) {
                            if (url.charAt(i) == '&')
                                break;

                            productCode.append(url.charAt(i));
                        }
                    productCode = new StringBuilder(productCode.toString().split("=")[1]);

                    ProductCard productCard = new ProductCard(context, new ProductEntity());
                    productCard.productEntity.setCode(productCode.toString());
                    productCard.imgSeeDetail.callOnClick();
                }

                return true;
            }
        });

        // visit product list page of website
        if (params != null)
            webView.loadUrl(AppConstant.BASE_URL + "/customer/m-product/product-list?limit=12&" + params);
        else
            webView.loadUrl(AppConstant.BASE_URL + "/customer/m-product/product-list?limit=12");

    }

    //add headerCard into frameHeaderContainer when activity_product_list loaded
    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }
}
