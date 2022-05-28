package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ConfirmOrderItemCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ReviewPaymentPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ReviewPaymentPageEntity;

public class ReviewPaymentActivity extends AppCompatActivity {

    private Context context;
    public TableLayout tbLayOrderItemContainer;
    public TextView txtShippingAddress, txtDescription, txtSubTotal, txtTotal,
            txtFirstName, txtLastName, txtEmail;

    public String phone, address;

    private void mapping() {
        tbLayOrderItemContainer = findViewById(R.id.reviewPaymentAct_tbLayOrderItemContainer);
        txtShippingAddress = findViewById(R.id.reviewPaymentAct_txtShippingAddress);
        txtDescription = findViewById(R.id.reviewPaymentAct_txtDescription);
        txtSubTotal = findViewById(R.id.reviewPaymentAct_txtSubTotal);
        txtTotal = findViewById(R.id.reviewPaymentAct_txtTotal);
        txtFirstName = findViewById(R.id.reviewPaymentAct_txtFirstName);
        txtLastName = findViewById(R.id.reviewPaymentAct_txtLastName);
        txtEmail = findViewById(R.id.reviewPaymentAct_txtEmail);
        Bundle extras = getIntent().getExtras();
        this.phone = extras.getString("phone");
        this.address = extras.getString("address");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_payment);
        this.context = this;
        mapping();

        loadInitData();
    }

    private void loadInitData() {
        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                ReviewPaymentPageCrane reviewPaymentPageCrane = new ReviewPaymentPageCrane();
                ReviewPaymentPageEntity reviewPaymentPageEntity = reviewPaymentPageCrane.getDataReviewPaymentPage(phone, address);

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 3; i++)
                            tbLayOrderItemContainer.removeViewAt(1);

                        for (OrderItemEntity orderItemEntity : reviewPaymentPageEntity.getReadyOrderItems()) {
                            ConfirmOrderItemCard confirmOrderItemCard = new ConfirmOrderItemCard(context, orderItemEntity);
                            tbLayOrderItemContainer.addView(confirmOrderItemCard.getView(), tbLayOrderItemContainer.getChildCount());
                        }

                        txtShippingAddress.setText(reviewPaymentPageEntity.getUserEntity().getAddress());
                        txtDescription.setText(reviewPaymentPageEntity.getDescription());
                        txtSubTotal.setText(reviewPaymentPageEntity.getTotal().toString() + " USD");
                        Float total = reviewPaymentPageEntity.getTotal() * reviewPaymentPageEntity.getExchangeRate();
                        txtTotal.setText(reviewPaymentPageEntity.getTotal() + " USD -> " + total.toString() + " VND");

                        txtFirstName.setText(reviewPaymentPageEntity.getUserEntity().getFirstName());
                        txtLastName.setText(reviewPaymentPageEntity.getUserEntity().getLastName());
                        txtEmail.setText(reviewPaymentPageEntity.getUserEntity().getEmail());

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

}