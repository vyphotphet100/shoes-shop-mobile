package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.RegisterPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;


public class RegisterActivity extends AppCompatActivity {

    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frameHeaderContainer, frmAccountLayContainer, frmInfoLayContainer;

    // objects to map to ui
    public EditText edtTxtFirstName, edtTxtLastName, edtTxtPhone,
            edtTxtEmail, edtTxtUsername, edtTxtPassword, edtTxtConfirmPassword;
    public TextView txtLogin;
    public Button btnRegister;


    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        txtLogin = findViewById(R.id.cardAccount_txtLogin);
        btnRegister = findViewById(R.id.registerAct_btnRegister);
        frameHeaderContainer = findViewById(R.id.registerAct_headerContainer);
        frmAccountLayContainer = findViewById(R.id.registerAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.registerAct_frmInfoLayContainer);
        edtTxtFirstName = findViewById(R.id.cardRegister_edtTxtFirstName);
        edtTxtLastName = findViewById(R.id.cardRegister_edtTxtLastName);
        edtTxtPhone = findViewById(R.id.cardRegister_edtTxtPhone);
        edtTxtEmail = findViewById(R.id.cardRegister_edtTxtEmail);
        edtTxtUsername = findViewById(R.id.cardRegister_edtTxtUsername);
        edtTxtPassword = findViewById(R.id.cardRegister_edtTxtPassword);
        edtTxtConfirmPassword = findViewById(R.id.cardRegister_edtTxtConfirmPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();
        AppConstant.waitingAnimation(context, 800);

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);

        txtLogin.requestFocus();

        //set event onclick for registerAct_btnRegister
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegister.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnRegister.setTextColor(Color.parseColor("#000000"));
                register();
            }
        });
    }

    // send info to server through api
    private void register() {
        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        //check if all fields are filled in
        if (!checkBeforeRegister())
            return;

        //check if value on edtTxtConfirmPassword = edtTxtPassword or not
        if (!edtTxtPassword.getText().toString().equals(edtTxtConfirmPassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Confirm password doesn't match!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        //set all information into UserEntity
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(edtTxtFirstName.getText().toString());
        userEntity.setLastName(edtTxtLastName.getText().toString());
        userEntity.setPhone(edtTxtPhone.getText().toString());
        userEntity.setEmail(edtTxtEmail.getText().toString());
        userEntity.setUsername(edtTxtUsername.getText().toString());
        userEntity.setPassword(edtTxtPassword.getText().toString());
        userEntity.setConfirmPassword(edtTxtConfirmPassword.getText().toString());

        // send it to server
        new Thread() {

            @Override
            public void run() {
                RegisterPageCrane registerPageCrane = new RegisterPageCrane();
                UserEntity resEntity = registerPageCrane.register(userEntity);

                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resEntity.getHttpStatus() == HttpStatus.OK) {
                            Toast.makeText(RegisterActivity.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "Something went wrong: " + resEntity.getMessage(), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    //function to check all fields are filled in
    private Boolean checkBeforeRegister() {
        if (edtTxtFirstName.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "First name is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtTxtLastName.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "Last name is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtTxtPhone.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "Phone is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtTxtEmail.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "Email is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtTxtUsername.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "Username is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtTxtPassword.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "Password is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtTxtConfirmPassword.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "Confirm password is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}