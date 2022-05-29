package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ThanksPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ThanksPageEntity;

public class ThanksActivity extends AppCompatActivity {

    public FrameLayout frameLayHeaderContainer;
    public HeaderCard headerCard;

    private void mapping() {
        frameLayHeaderContainer = findViewById(R.id.thanksAct_headerContainer);
        headerCard = new HeaderCard(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        mapping();
        setHeader();

        loadInitData();
    }

    private void loadInitData() {
        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                ThanksPageCrane thanksPageCrane = new ThanksPageCrane();
                ThanksPageEntity thanksPageEntity = thanksPageCrane.getDataThanksPage();

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

    private void setHeader() {
        frameLayHeaderContainer.removeAllViews();
        frameLayHeaderContainer.addView(headerCard.getView());
    }

}