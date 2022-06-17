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

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.LoginPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class LoginActivity extends AppCompatActivity {
    // declare the necessary variables used in the form
    private Context context;
    public Button btnLogin;
    public EditText edtTxtUsername, edtTxtPassword;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountCard accountCard;
    public InformationCard informationCard;

    //Function to map object in code to view in UI
    private void mapping() {
        btnLogin = findViewById(R.id.loginAct_btnLogin);
        edtTxtUsername = findViewById(R.id.cardLogin_edtTxtUsername);
        edtTxtPassword = findViewById(R.id.cardLogin_edtTxtPassword);
        frmAccountLayContainer = findViewById(R.id.loginAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.loginAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.loginAct_headerContainer);

        //Create 3 new card: headerCard, AccountCard, InformationCard
        headerCard = new HeaderCard(context);
        accountCard = new AccountCard(context);
        informationCard = new InformationCard(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // save instance state
        setContentView(R.layout.activity_login);    // show activity login
        this.context = this;
        AppConstant.waitingAnimation(context, 800); //create loading animation that lasts 800ms
        mapping();  //Function to map object in code to view in UI
        initSetupLayout(); //remove all view and then add 3 new view : headerCard, AccountCard, InformationCard

        btnLogin.setOnClickListener(new View.OnClickListener() { //when button login clicked
            @Override
            public void onClick(View v) {
                btnLogin.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnLogin.setTextColor(Color.parseColor("#000000"));
                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);    // create new progressdialog and show

                new Thread() {
                    @Override
                    public void run() {
                        LoginPageCrane loginPageCrane = new LoginPageCrane(); // create new login page crane
                        UserEntity userEntity =
                                loginPageCrane.login(edtTxtUsername.getText().toString(),
                                        edtTxtPassword.getText().toString());
                                //user entity have login with 2 variables: username and password

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

    //Remove All view and add 3 view , there are: accountCard, informationCard, headerCard
    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        frmAccountLayContainer.addView(accountCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

}