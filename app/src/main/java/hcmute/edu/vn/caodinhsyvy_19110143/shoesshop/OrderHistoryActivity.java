package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ConfirmOrderItemCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.OrderHistoryItemCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.OrderHistoryPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.OrderHistoryPageEntity;

public class OrderHistoryActivity extends AppCompatActivity {
    // declare the necessary variables used in the form
    private Context context;

    public TableLayout tbLayOrderItemContainer;
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;

    //Function to map object in code to view in UI
    private void mapping() {
        tbLayOrderItemContainer = findViewById(R.id.orderHistoryAct_tbLayOrderItemContainer);
        frameHeaderContainer = findViewById(R.id.orderHistoryAct_headerContainer);
        headerCard = new HeaderCard(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // save instance state
        setContentView(R.layout.activity_order_history);    // show activity order history
        this.context = this;
        mapping();
        initSetupLayout();

        loadInitData();//load data from api to activity order history
    }

    //load data from api to activity order history
    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                OrderHistoryPageCrane orderHistoryPageCrane = new OrderHistoryPageCrane();
                OrderHistoryPageEntity orderHistoryPageEntity = orderHistoryPageCrane.getDataOrderHistory();

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 3; i++)
                            tbLayOrderItemContainer.removeViewAt(1);

                        for (OrderItemEntity orderItemEntity : orderHistoryPageEntity.getOrderItemEntities()) {
                            OrderHistoryItemCard orderHistoryItemCard = new OrderHistoryItemCard(context, orderItemEntity);
                            tbLayOrderItemContainer.addView(orderHistoryItemCard.getView());
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    //remove all view and add view header card
    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }
}