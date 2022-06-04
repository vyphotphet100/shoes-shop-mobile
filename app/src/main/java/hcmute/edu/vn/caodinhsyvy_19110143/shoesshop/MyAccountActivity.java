package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class MyAccountActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountAfterLoginCard accountCard;
    public InformationCard informationCard;
    public TextView txtViewOrderHistory;

    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.myAccountAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.myAccountAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.myAccountAct_headerContainer);
        headerCard = new HeaderCard(context);
        accountCard = new AccountAfterLoginCard(context);
        informationCard = new InformationCard(context);
        txtViewOrderHistory = findViewById(R.id.myAccountAct_txtViewOrderHistory);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();
        setEvent();

    }

    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        frmAccountLayContainer.addView(accountCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void setEvent() {
        txtViewOrderHistory.setOnClickListener(new View.OnClickListener() {
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

}