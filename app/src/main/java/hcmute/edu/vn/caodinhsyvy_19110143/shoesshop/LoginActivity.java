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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.LoginPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class LoginActivity extends AppCompatActivity {

    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;

    // objects to map to ui
    public Button btnLogin;
    public EditText edtTxtUsername, edtTxtPassword;


    //Function to map objects in code to associated views in UI
    private void mapping() {
        btnLogin = findViewById(R.id.loginAct_btnLogin);
        edtTxtUsername = findViewById(R.id.cardLogin_edtTxtUsername);
        edtTxtPassword = findViewById(R.id.cardLogin_edtTxtPassword);
        frmAccountLayContainer = findViewById(R.id.loginAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.loginAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.loginAct_headerContainer);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;
        AppConstant.waitingAnimation(context, 800);
        mapping();

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);

        // Login clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // color animation
                btnLogin.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnLogin.setTextColor(Color.parseColor("#000000"));

                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);

                // send username and password to server through API
                new Thread() {
                    @Override
                    public void run() {
                        LoginPageCrane loginPageCrane = new LoginPageCrane();
                        UserEntity userEntity =
                                loginPageCrane.login(edtTxtUsername.getText().toString(),
                                        edtTxtPassword.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (userEntity.getHttpStatus() == HttpStatus.OK) { // if user entity have method getHttpStatus is ok
                                    AppConstant.loggedInUserEntity = userEntity; // login in user entity
                                    Toast.makeText(context, "Login successfully!", Toast.LENGTH_SHORT).show(); //Notice Login succesfully
                                    Intent intent = new Intent(context, HomeActivity.class); // create intent : Home Activity
                                    startActivity(intent); // Start Home activity
                                    finish();
                                }
                                else {
                                    Toast.makeText(context, "Username or Password is invalid. Please check again!", Toast.LENGTH_SHORT).show();
                                    //Notice Username or Password is invalid. Please check again
                                    }

                                progressDialog.dismiss();
                            }
                        });

                    }
                }.start();
            }
        });

    }

}