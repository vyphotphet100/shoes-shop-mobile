package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class ChangePasswordActivity extends AppCompatActivity {

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

                btnSaveChange.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnSaveChange.setTextColor(Color.parseColor("#000000"));

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
                btnBack.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnBack.setTextColor(Color.parseColor("#000000"));
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