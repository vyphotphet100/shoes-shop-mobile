package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class MyAccountActivity extends AppCompatActivity {

    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;

    // objects to map to ui
    public TextView txtViewOrderHistory, txtEditAccountInfo, txtChangePassword, txtLogout;


    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.myAccountAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.myAccountAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.myAccountAct_headerContainer);
        txtViewOrderHistory = findViewById(R.id.myAccountAct_txtViewOrderHistory);
        txtEditAccountInfo = findViewById(R.id.myAccountAct_txtEditAccountInfo);
        txtChangePassword = findViewById(R.id.myAccountAct_txtChangePassword);
        txtLogout = findViewById(R.id.myAccountAct_txtLogout);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        mapping();
        AppConstant.waitingAnimation(context, 800);

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);

        setEvent();

    }

    // set events for objects
    private void setEvent() {

        // 'View Order History' clicked
        txtViewOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof OrderHistoryActivity)) {
                    Intent intent = new Intent(context, OrderHistoryActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity) context).finish();
                }
            }
        });

        // 'Edit Account Information' clicked
        txtEditAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditAccountActivity.class);
                startActivity(intent);
//                ((AppCompatActivity)context).finish();
            }
        });

        // 'Change password' clicked
        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangePasswordActivity.class);
                startActivity(intent);
//                ((AppCompatActivity)context).finish();
            }
        });

        // 'Logout' clicked
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstant.loggedInUserEntity = null;
                Toast.makeText(context, "Log out successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                ((AppCompatActivity) context).finish();
            }
        });
    }

}