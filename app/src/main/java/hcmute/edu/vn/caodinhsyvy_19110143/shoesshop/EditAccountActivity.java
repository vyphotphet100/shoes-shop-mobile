package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.EditAccountPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class EditAccountActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountAfterLoginCard accountAfterLoginCard;
    public InformationCard informationCard;
    public EditText edtTxtFirstName, edtTxtLastName, edtTxtPhone, edtTxtEmail;
    public Button btnSaveChange, btnBack;

    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.editAccountAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.editAccountAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.editAccountAct_headerContainer);
        headerCard = new HeaderCard(context);
        accountAfterLoginCard = new AccountAfterLoginCard(context);
        informationCard = new InformationCard(context);
        edtTxtFirstName = findViewById(R.id.editAccountAct_edtTxtFirstName);
        edtTxtLastName = findViewById(R.id.editAccountAct_edtTxtLastName);
        edtTxtPhone = findViewById(R.id.editAccountAct_edtTxtPhone);
        edtTxtEmail = findViewById(R.id.editAccountAct_edtTxtEmail);
        btnSaveChange = findViewById(R.id.editAccountAct_btnSaveChange);
        btnBack = findViewById(R.id.editAccountAct_btnBack);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();
        initSetupData();

        setEvent();

    }

    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        frmAccountLayContainer.addView(accountAfterLoginCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void initSetupData() {
        edtTxtFirstName.setText(AppConstant.loggedInUserEntity.getFirstName());
        edtTxtLastName.setText(AppConstant.loggedInUserEntity.getLastName());
        edtTxtPhone.setText(AppConstant.loggedInUserEntity.getPhone());
        edtTxtEmail.setText(AppConstant.loggedInUserEntity.getEmail());
    }

    private void setEvent() {
        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkValidate())
                    return;

                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);
                new Thread() {
                    @Override
                    public void run() {

                        EditAccountPageCrane editAccountPageCrane = new EditAccountPageCrane();
                        UserEntity userEntity = editAccountPageCrane.editAccount(edtTxtFirstName.getText().toString(),
                                edtTxtLastName.getText().toString(), edtTxtPhone.getText().toString(), edtTxtEmail.getText().toString());

                        if (userEntity.getHttpStatus() != HttpStatus.OK) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, userEntity.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });

                            return;
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AppConstant.loggedInUserEntity = userEntity;
                                Toast.makeText(context, userEntity.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                ((AppCompatActivity)context).finish();
                            }
                        });
                    }
                }.start();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)context).finish();
            }
        });
    }

    private Boolean checkValidate() {
        if (edtTxtFirstName.getText().toString().trim().equals("") ||
                edtTxtLastName.getText().toString().trim().equals("") ||
                edtTxtEmail.getText().toString().trim().equals("") ||
                edtTxtPhone.getText().toString().trim().equals("")) {
            Toast.makeText(context, "Some fields are empty. Please check again!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}