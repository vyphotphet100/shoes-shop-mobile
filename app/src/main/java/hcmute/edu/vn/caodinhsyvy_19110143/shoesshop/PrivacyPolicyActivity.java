package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountCard accountCard;
    public AccountAfterLoginCard accountAfterLoginCard;
    public InformationCard informationCard;

    //mapping all elements on activity_privacy_policy
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.privacyPolicyAct_frmAccountLayContainer);
        frameHeaderContainer = findViewById(R.id.privacyPolicyAct_headerContainer);
        frmInfoLayContainer = findViewById(R.id.privacyPolicyAct_frmInfoLayContainer);
        headerCard = new HeaderCard(context);
        accountAfterLoginCard = new AccountAfterLoginCard(context);
        accountCard = new AccountCard(context);
        informationCard = new InformationCard(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();
    }

    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        //check if the customer logged in the account or not
        if (AppConstant.loggedInUserEntity == null)
            frmAccountLayContainer.addView(accountCard.getView());
        else
            frmAccountLayContainer.addView(accountAfterLoginCard.getView());

        //add informationCard into frmInfoLayContainer when activity_privacy_policy loaded
        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());
        //add headerCard into frameHeaderContainer when activity_privacy_policy loaded
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }


}