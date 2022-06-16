package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.MyAccountActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.OrderHistoryActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;

public class AccountAfterLoginCard extends BaseCard{
    // declare the necessary variables used in the account after login card
    private int layout = R.layout.card_account_after_login;
    private View view;
    private Context context;

    public TextView txtMyAccount, txtOrderHistory;

    //Function to map object in code to text view
    @Override
    protected void mapping() {
        txtMyAccount = view.findViewById(R.id.cardAccountAfterLogin_txtMyAccount);
        txtOrderHistory = view.findViewById(R.id.cardAccountAfterLogin_txtOrderHistory);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        txtMyAccount.setOnClickListener(new View.OnClickListener() { // when txt My account clicked
            @Override
            public void onClick(View v) {
                if (!(context instanceof MyAccountActivity)) {
                    Intent intent = new Intent(context, MyAccountActivity.class);
                    context.startActivity(intent);   // start my account activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        txtOrderHistory.setOnClickListener(new View.OnClickListener() {  // when txt order history clicked
            @Override
            public void onClick(View v) {
                if (!(context instanceof OrderHistoryActivity)) {
                    Intent intent = new Intent(context, OrderHistoryActivity.class);
                    context.startActivity(intent);  // start order history activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

    }

    //create account after login card
    public AccountAfterLoginCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
