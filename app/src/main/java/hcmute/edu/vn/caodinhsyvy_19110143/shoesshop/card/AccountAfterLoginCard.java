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
    private int layout = R.layout.card_account_after_login; // main card layout
    private View view;
    private Context context;

    // objects to map to ui of card
    public TextView txtMyAccount, txtOrderHistory;

    //Function to map objects in code to associated views in UI - test2
    @Override
    protected void mapping() {
        txtMyAccount = view.findViewById(R.id.cardAccountAfterLogin_txtMyAccount);
        txtOrderHistory = view.findViewById(R.id.cardAccountAfterLogin_txtOrderHistory);
    }

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    // set events for objects
    @Override
    protected void setListenerEvent() {

        // when txt My account clicked
        txtMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof MyAccountActivity)) {
                    Intent intent = new Intent(context, MyAccountActivity.class);
                    context.startActivity(intent);   // start my account activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        // when txt order history clicked
        txtOrderHistory.setOnClickListener(new View.OnClickListener() {
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
