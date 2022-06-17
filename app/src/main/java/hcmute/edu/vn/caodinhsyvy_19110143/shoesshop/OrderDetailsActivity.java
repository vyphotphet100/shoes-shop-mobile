package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.OrderDetailPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.OrderDetailPageEntity;

public class OrderDetailsActivity extends AppCompatActivity {
    // declare the necessary variables used in the form
    private Context context;

    // to contain header card view
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;

    // order item id passed from other activity
    public Integer orderItemId;

    // objects to map to ui
    public TextView txtOrderID, txtCustomer, txtShipment, txtDateAdded, txtPaymentMethod,
            txtPaymentAddress, txtShippingAddress,
            txtProductName, txtBrand, txtQuantity, txtUnitPrice, txtTotal;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        txtOrderID = findViewById(R.id.cardOrderDetailsItem_txtOrderID);
        txtCustomer = findViewById(R.id.cardOrderDetailsItem_txtCustomer);
        txtShipment = findViewById(R.id.cardOrderDetailsItem_txtShipment);
        txtDateAdded = findViewById(R.id.cardOrderDetailsItem_txtDateAdded);
        txtPaymentMethod = findViewById(R.id.cardOrderDetailsItem_txtPaymentMethod);
        txtPaymentAddress = findViewById(R.id.orderDetailsAct_txtPaymentAddress);
        txtShippingAddress = findViewById(R.id.orderDetailsAct_txtShippingAddress);
        txtProductName = findViewById(R.id.cardProductDetailInOrderDetailsItem_txtProductName);
        txtBrand = findViewById(R.id.cardProductDetailInOrderDetailsItem_txtBrand);
        txtQuantity = findViewById(R.id.cardProductDetailInOrderDetailsItem_txtQuantity);
        txtUnitPrice = findViewById(R.id.cardProductDetailInOrderDetailsItem_txtPrice);
        txtTotal = findViewById(R.id.orderDetailsAct_txtTotal);
        orderItemId = getIntent().getExtras().getInt("id"); // get order item id from intent
        frameHeaderContainer = findViewById(R.id.orderDetailsAct_headerContainer);
        headerCard = new HeaderCard(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        this.context = this;
        mapping();
        initSetupLayout();
        loadInitData();
    }

    //load data from api before beginning activity
    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        // load from server
        new Thread() {

            @Override
            public void run() {
                OrderDetailPageCrane orderDetailPageCrane = new OrderDetailPageCrane();
                OrderDetailPageEntity orderDetailPageEntity = orderDetailPageCrane.getDataOrderDetail(orderItemId);

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //get order item entity
                        OrderItemEntity orderItemEntity = orderDetailPageEntity.getOrderItemEntity();
                        txtOrderID.setText("#" + orderItemEntity.getId().toString());
                        txtCustomer.setText(orderItemEntity.getCustomer().getLastName());
                        txtShipment.setText("Shipping");
                        txtDateAdded.setText(orderItemEntity.getPayment().getPaymentDate().toString());
                        txtPaymentMethod.setText(orderItemEntity.getCustomer().getPaymentMethod().getName());

                        if (orderItemEntity.getCustomer().getPaymentMethod().getId() == 3)
                            txtPaymentAddress.setText(orderItemEntity.getCustomer().getAddress());
                        else
                            txtPaymentAddress.setText("Online");
                        txtShippingAddress.setText(orderItemEntity.getCustomer().getAddress());

                        txtProductName.setText(orderItemEntity.getProduct().getTitle());
                        txtBrand.setText(orderItemEntity.getProduct().getBrand().getName());
                        txtQuantity.setText(orderItemEntity.getQuantityBought().toString());
                        txtUnitPrice.setText("$" + String.valueOf(orderItemEntity.getProduct().getPrice()));
                        txtTotal.setText("$" + orderItemEntity.getTotalCost().toString());

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }
    //add header card view
    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

}