package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ConfirmOrderItemCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.CheckOutPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ReviewPaymentPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.CheckOutPageEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ReviewPaymentPageEntity;

public class CheckOutActivity extends AppCompatActivity {

    private Context context;
    public EditText edtTxtFirstName, edtTxtLastName, edtTxtPhone, edtTxtAddress;
    public TextView txtTotal, txtPay, txtEdit;
    public TableLayout tbLayOrderItemContainer;
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;
    private RadioButton rBtnPaypal;

    private void mapping() {
        edtTxtFirstName = findViewById(R.id.checkOutAct_edtTxtFirstName);
        edtTxtLastName = findViewById(R.id.checkOutAct_edtTxtLastName);
        edtTxtPhone = findViewById(R.id.checkOutAct_edtTxtPhone);
        edtTxtAddress = findViewById(R.id.checkOutAct_edtTxtAddress);
        txtTotal = findViewById(R.id.checkOutAct_txtTotal);
        txtPay = findViewById(R.id.checkOutAct_txtPay);
        txtEdit = findViewById(R.id.checkOutAct_txtEdit);
        tbLayOrderItemContainer = findViewById(R.id.checkOutAct_tbLayOrderItemContainer);
        frameHeaderContainer = findViewById(R.id.checkOutAct_headerContainer);
        headerCard = new HeaderCard(this);
        rBtnPaypal = findViewById(R.id.checkOutAct_rBtnPaypal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        this.context = this;
        mapping();
        if (!AppConstant.checkLoggedIn(context))
            return;

        AppConstant.waitingAnimation(context, 600);
        setHeader();

        loadInitData();

        txtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                            "Loading...", true);

                    @Override
                    public void run() {
                        Integer paymentMethod = 3;
                        if (rBtnPaypal.isChecked())
                            paymentMethod = 1;
                        ReviewPaymentPageCrane reviewPaymentPageCrane = new ReviewPaymentPageCrane();
                        ReviewPaymentPageEntity reviewPaymentPageEntity =
                                reviewPaymentPageCrane.getDataReviewPaymentPage(
                                        edtTxtPhone.getText().toString(),
                                        edtTxtAddress.getText().toString(),
                                        paymentMethod);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // check if it is paypal payment
                                if (reviewPaymentPageEntity.getRedirectLink() != null) {
                                    Intent intent = new Intent(context, WebViewActivity.class);
                                    intent.putExtra("reviewPaymentPageEntity", reviewPaymentPageEntity);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(context, ReviewPaymentActivity.class);
                                    intent.putExtra("reviewPaymentPageEntity", reviewPaymentPageEntity);
                                    startActivity(intent);
                                    finish();
                                }

                                progressDialog.dismiss();
                            }
                        });
                    }
                }.start();
            }
        });

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadInitData() {
        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {
            @Override
            public void run() {
                CheckOutPageCrane checkOutPageCrane = new CheckOutPageCrane();
                CheckOutPageEntity checkOutPageEntity = checkOutPageCrane.getDataCheckOutPage();

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edtTxtFirstName.setText(checkOutPageEntity.getUserEntity().getFirstName());
                        edtTxtLastName.setText(checkOutPageEntity.getUserEntity().getLastName());
                        edtTxtPhone.setText(checkOutPageEntity.getUserEntity().getPhone());
                        edtTxtAddress.setText(checkOutPageEntity.getUserEntity().getAddress());

                        for (int i = 0; i < 3; i++)
                            tbLayOrderItemContainer.removeViewAt(1);

                        for (OrderItemEntity orderItemEntity : checkOutPageEntity.getReadyOrderItems()) {
                            ConfirmOrderItemCard confirmOrderItemCard = new ConfirmOrderItemCard(context, orderItemEntity);
                            tbLayOrderItemContainer.addView(confirmOrderItemCard.getView(), tbLayOrderItemContainer.getChildCount() - 1);
                        }

                        txtTotal.setText(checkOutPageEntity.getTotal().toString());

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    private void setHeader() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

}