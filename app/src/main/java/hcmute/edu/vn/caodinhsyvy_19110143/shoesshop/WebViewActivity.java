package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ReviewPaymentPageEntity;

public class WebViewActivity extends AppCompatActivity {

    private Context context;
    public WebView webView;

    // to get reviewPaymentPageEntity passed from other activity
    public ReviewPaymentPageEntity reviewPaymentPageEntity;

    // check if user click back button when not having done paypal payment
    public Boolean checkBack = true;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        webView = findViewById(R.id.webViewAct_webView);
        this.reviewPaymentPageEntity = (ReviewPaymentPageEntity) getIntent().getSerializableExtra("reviewPaymentPageEntity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mapping();

        // set event for webview
        webView.loadUrl(this.reviewPaymentPageEntity.getRedirectLink());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // avoid open default browser in phone
                if (!url.contains(AppConstant.BASE_URL))
                    return super.shouldOverrideUrlLoading(view, url);

                // get paymentId from url
                StringBuilder paymentId = new StringBuilder();
                if (url.contains("paymentId"))
                    for (int i = url.indexOf("paymentId"); i < url.length(); i++) {
                        if (url.charAt(i) == '&')
                            break;

                        paymentId.append(url.charAt(i));
                    }
                paymentId = new StringBuilder(paymentId.toString().split("=")[1]);

                // get PayerId from url
                StringBuilder PayerID = new StringBuilder();
                if (url.contains("PayerID"))
                    for (int i = url.indexOf("PayerID"); i < url.length(); i++)
                        PayerID.append(url.charAt(i));
                PayerID = new StringBuilder(PayerID.toString().split("=")[1]);

                // start Review payment activity
                Intent intent = new Intent(context, ReviewPaymentActivity.class);
                reviewPaymentPageEntity.setPaymentId(paymentId.toString());
                reviewPaymentPageEntity.setPayerID(PayerID.toString());
                checkBack = false;
                intent.putExtra("reviewPaymentPageEntity", reviewPaymentPageEntity);
                startActivity(intent);
                finish();
                return true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // start Shopping cart activity when user click back button
        if (checkBack) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }
    }
}