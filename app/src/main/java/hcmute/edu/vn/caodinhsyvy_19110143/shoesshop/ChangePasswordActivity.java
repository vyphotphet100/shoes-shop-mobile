package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ChangePasswordPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.EditAccountPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class ChangePasswordActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountAfterLoginCard accountAfterLoginCard;
    public InformationCard informationCard;
    public EditText edtTxtOldPassword, edtTxtNewPassword, edtTxtPasswordConfirm;
    public Button btnSaveChange, btnBack;

    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.changePasswordAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.changePasswordAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.changePasswordAct_headerContainer);
        headerCard = new HeaderCard(context);
        accountAfterLoginCard = new AccountAfterLoginCard(context);
        informationCard = new InformationCard(context);
        edtTxtOldPassword = findViewById(R.id.changePasswordAct_edtTxtOldPassword);
        edtTxtNewPassword = findViewById(R.id.changePasswordAct_edtTxtNewPassword);
        edtTxtPasswordConfirm = findViewById(R.id.changePasswordAct_edtTxtPasswordConfirm);
        btnSaveChange = findViewById(R.id.changePasswordAct_btnSaveChange);
        btnBack = findViewById(R.id.changePasswordAct_btnBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();

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

                        ChangePasswordPageCrane changePasswordPageCrane = new ChangePasswordPageCrane();
                        UserEntity userEntity = changePasswordPageCrane.changePassword(edtTxtOldPassword.getText().toString(),
                                edtTxtNewPassword.getText().toString(), edtTxtPasswordConfirm.getText().toString());

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
        if (edtTxtOldPassword.getText().toString().trim().equals("") ||
                edtTxtNewPassword.getText().toString().trim().equals("") ||
                edtTxtPasswordConfirm.getText().toString().trim().equals("")) {
            Toast.makeText(context, "Some fields are empty. Please check again!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}