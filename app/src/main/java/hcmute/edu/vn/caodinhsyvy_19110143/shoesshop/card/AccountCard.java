package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.LoginActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.RegisterActivity;

public class AccountCard extends BaseCard{
    // declare the necessary variables used in the account card
    private int layout = R.layout.card_account;
    private View view;
    private Context context;

    public TextView txtLogin, txtRegister;

    //Function to map object in code to text view
    @Override
    protected void mapping() {
        txtLogin = view.findViewById(R.id.cardAccount_txtLogin);
        txtRegister = view.findViewById(R.id.cardAccount_txtRegister);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof LoginActivity)) { //when txt Login clicked
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent); // start login activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            //when txt Register clicked
            @Override
            public void onClick(View v) {
                if (!(context instanceof RegisterActivity)) {
                    Intent intent = new Intent(context, RegisterActivity.class);
                    context.startActivity(intent); // start register activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

    }

    //create account card
    public AccountCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
