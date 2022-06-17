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
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;
    public WebView webView;
    public String params;

    //mapping all elements on activity_product_list
    private void mapping() {
        this.context = this;
        frameHeaderContainer = findViewById(R.id.productListAct_headerContainer);
        headerCard = new HeaderCard(context);
        webView = findViewById(R.id.productListAct_webView);
        params = getIntent().getExtras().getString("params");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();

        final ProgressDialog[] progressDialog = {ProgressDialog.show(context, "Noriva",
                "Loading...", true)};
//        progressDialog[0].dismiss();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(AppConstant.BASE_URL + "/customer/m-product/product-list?limit=12&" + params);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                AppConstant.waitingAnimation(context, 500);
                if (!url.contains("shoesshop-app://"))
                    return super.shouldOverrideUrlLoading(view, url);

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
                } else if (url.contains("/product-detail")) {
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

    }

    //add headerCard into frameHeaderContainer when activity_product_list loaded
    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }
}
