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

public class TermsAndConditionsActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountCard accountCard;
    public AccountAfterLoginCard accountAfterLoginCard;
    public InformationCard informationCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();

    }

    //mapping all elements on activity_terms_and_conditions
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.termsAct_frmAccountLayContainer);
        frameHeaderContainer = findViewById(R.id.termsAct_headerContainer);
        frmInfoLayContainer = findViewById(R.id.termsAct_frmInfoLayContainer);
        headerCard = new HeaderCard(context);
        accountCard = new AccountCard(context);
        accountAfterLoginCard = new AccountAfterLoginCard(context);
        informationCard = new InformationCard(context);
    }


    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        //check if customer logged in or not
        if (AppConstant.loggedInUserEntity == null)
            //if customer have not logged in, add accountCard into frmAccountLayContainer
            frmAccountLayContainer.addView(accountCard.getView());
        else
            //else add accountAfterLoginCard into frmAccountLayContainer
            frmAccountLayContainer.addView(accountAfterLoginCard.getView());

        //add informationCard into frmInfoLayContainer when activity_terms_and_conditions loaded
        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        //add headerCard into frameHeaderContainer when activity_terms_and_conditions loaded
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

}