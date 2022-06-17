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
    private int layout = R.layout.card_account; // main layout
    private View view;
    private Context context;

    // objects to map to ui of card
    public TextView txtLogin, txtRegister;

    //Function to map objects in code to associated views in UI
    @Override
    protected void mapping() {
        txtLogin = view.findViewById(R.id.cardAccount_txtLogin);
        txtRegister = view.findViewById(R.id.cardAccount_txtRegister);
    }

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    // set events for objects
    @Override
    protected void setListenerEvent() {

        //when txt Login clicked
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof LoginActivity)) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent); // start login activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        //when txt Register clicked
        txtRegister.setOnClickListener(new View.OnClickListener() {
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
