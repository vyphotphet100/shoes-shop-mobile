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
    private int layout = R.layout.card_account_after_login;
    private View view;
    private Context context;

    public TextView txtMyAccount, txtOrderHistory;

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
        txtMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof MyAccountActivity)) {
                    Intent intent = new Intent(context, MyAccountActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        txtOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof OrderHistoryActivity)) {
                    Intent intent = new Intent(context, OrderHistoryActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

    }

    public AccountAfterLoginCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
