package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.OrderHistoryItemCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.OrderDetailPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.OrderHistoryPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.OrderDetailPageEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.OrderHistoryPageEntity;

public class OrderDetailsActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        this.context = this;

        loadInitData();
    }

    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                OrderDetailPageCrane orderDetailPageCrane = new OrderDetailPageCrane();
                OrderDetailPageEntity orderDetailPageEntity = orderDetailPageCrane.getDataOrderDetail(73);

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

}