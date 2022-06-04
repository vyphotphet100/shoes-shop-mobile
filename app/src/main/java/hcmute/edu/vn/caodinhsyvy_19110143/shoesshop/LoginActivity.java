package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.LoginPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    public Button btnLogin;
    public EditText edtTxtUsername, edtTxtPassword;

    private void mapping() {
        btnLogin = findViewById(R.id.loginAct_btnLogin);
        edtTxtUsername = findViewById(R.id.cardLogin_edtTxtUsername);
        edtTxtPassword = findViewById(R.id.cardLogin_edtTxtPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;
        mapping();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);

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
                                if (userEntity.getHttpStatus() == HttpStatus.OK) {
                                    AppConstant.loggedInUserEntity = userEntity;
                                    Toast.makeText(context, "Login successfully!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(context, "Username or Password is invalid. Please check again!", Toast.LENGTH_SHORT).show();
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