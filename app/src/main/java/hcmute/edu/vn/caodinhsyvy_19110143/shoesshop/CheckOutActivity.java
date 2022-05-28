package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ConfirmOrderItemCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.CheckOutPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.CheckOutPageEntity;

public class CheckOutActivity extends AppCompatActivity {

    private Context context;
    public EditText edtTxtFirstName, edtTxtLastName, edtTxtPhone, edtTxtAddress;
    public TextView txtTotal, txtPay;
    public TableLayout tbLayOrderItemContainer;

    private void mapping() {
        edtTxtFirstName = findViewById(R.id.checkOutAct_edtTxtFirstName);
        edtTxtLastName = findViewById(R.id.checkOutAct_edtTxtLastName);
        edtTxtPhone = findViewById(R.id.checkOutAct_edtTxtPhone);
        edtTxtAddress = findViewById(R.id.checkOutAct_edtTxtAddress);
        txtTotal = findViewById(R.id.checkOutAct_txtTotal);
        txtPay = findViewById(R.id.checkOutAct_txtPay);
        tbLayOrderItemContainer = findViewById(R.id.checkOutAct_tbLayOrderItemContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        this.context = this;
        mapping();

        loadInitData();

        txtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewPaymentActivity.class);
                intent.putExtra("phone", edtTxtPhone.getText().toString());
                intent.putExtra("address", edtTxtAddress.getText().toString());
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

                        for (int i=0; i<3; i++)
                            tbLayOrderItemContainer.removeViewAt(1);

                        for (OrderItemEntity orderItemEntity: checkOutPageEntity.getReadyOrderItems()) {
                            ConfirmOrderItemCard confirmOrderItemCard = new ConfirmOrderItemCard(context, orderItemEntity);
                            tbLayOrderItemContainer.addView(confirmOrderItemCard.getView(), tbLayOrderItemContainer.getChildCount()-1);
                        }

                        txtTotal.setText(checkOutPageEntity.getTotal().toString());

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

}