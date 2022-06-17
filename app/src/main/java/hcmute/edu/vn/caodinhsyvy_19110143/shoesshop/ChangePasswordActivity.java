package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ChangePasswordPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class ChangePasswordActivity extends AppCompatActivity {

    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;

    // objects to map to ui
    public EditText edtTxtOldPassword, edtTxtNewPassword, edtTxtPasswordConfirm;
    public Button btnSaveChange, btnBack;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.changePasswordAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.changePasswordAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.changePasswordAct_headerContainer);
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

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);

        setEvent();
    }

    // set events for objects
    private void setEvent() {
        // click Save Change button
        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkValidate())
                    return;

                // Color animation when clicking
                btnSaveChange.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnSaveChange.setTextColor(Color.parseColor("#000000"));

                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true); // Waiting dialog

                // get data from API
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

        // click Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBack.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnBack.setTextColor(Color.parseColor("#000000"));
                ((AppCompatActivity)context).finish();
            }
        });
    }

    // check if there are any fields blank
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