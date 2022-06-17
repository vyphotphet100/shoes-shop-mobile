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
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.EditAccountPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class EditAccountActivity extends AppCompatActivity {

    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;

    // objects to map to ui
    public EditText edtTxtFirstName, edtTxtLastName, edtTxtPhone, edtTxtEmail;
    public Button btnSaveChange, btnBack;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.editAccountAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.editAccountAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.editAccountAct_headerContainer);
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

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);

        initSetupData();
        setEvent();

    }

    // add user info to edit text views
    private void initSetupData() {
        edtTxtFirstName.setText(AppConstant.loggedInUserEntity.getFirstName());
        edtTxtLastName.setText(AppConstant.loggedInUserEntity.getLastName());
        edtTxtPhone.setText(AppConstant.loggedInUserEntity.getPhone());
        edtTxtEmail.setText(AppConstant.loggedInUserEntity.getEmail());
    }

    // set events for objects
    private void setEvent() {

        // Save clicked
        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkValidate())
                    return;

                // color animation
                btnSaveChange.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnSaveChange.setTextColor(Color.parseColor("#000000"));

                // waiting dialog
                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);

                // send data to server
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

        // Back clicked
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // color animation
                btnBack.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnBack.setTextColor(Color.parseColor("#000000"));

                ((AppCompatActivity)context).finish();
            }
        });
    }

    // check if there are any fields blank
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