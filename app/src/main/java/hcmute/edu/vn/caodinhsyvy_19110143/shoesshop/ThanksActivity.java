package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ThanksPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ThanksPageEntity;

public class ThanksActivity extends AppCompatActivity {

    // to contain header card view
    public FrameLayout frameLayHeaderContainer;
    public HeaderCard headerCard;

    // to get paymentId and PayerID passed from other activity
    public String paymentId;
    public String PayerID;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        frameLayHeaderContainer = findViewById(R.id.thanksAct_headerContainer);
        headerCard = new HeaderCard(this);
        this.paymentId = getIntent().getExtras().getString("paymentId");
        this.PayerID = getIntent().getExtras().getString("PayerID");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        mapping();
        setHeader();

        loadInitData();
    }

    //load data from api before beginning activity
    private void loadInitData() {
        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                ThanksPageCrane thanksPageCrane = new ThanksPageCrane();
                ThanksPageEntity thanksPageEntity = thanksPageCrane.getDataThanksPage(paymentId, PayerID);

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    //add headerCard into frameLayHeaderContainer when activity_thanks loaded
    private void setHeader() {
        frameLayHeaderContainer.removeAllViews();
        frameLayHeaderContainer.addView(headerCard.getView());
    }

}