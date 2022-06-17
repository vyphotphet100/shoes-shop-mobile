package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class ContactUsActivity extends AppCompatActivity {
    // declare the necessary variables used in the form
    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountCard accountCard;
    public AccountAfterLoginCard accountAfterLoginCard;
    public InformationCard informationCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // save instance state
        setContentView(R.layout.activity_contact_us);       //show activity contact us
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();
    }

    //Function to map object in code to view in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.contactUsAct_frmAccountLayContainer);
        frameHeaderContainer = findViewById(R.id.contactUsAct_headerContainer);
        frmInfoLayContainer = findViewById(R.id.contactUsAct_frmInfoLayContainer);
        headerCard = new HeaderCard(context);
        accountCard = new AccountCard(context);
        accountAfterLoginCard = new AccountAfterLoginCard(context);
        informationCard = new InformationCard(context);
    }

    //remove all view and add view header card
    //if login succesfully , add view account after login card. Else add view account card
    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        if (AppConstant.loggedInUserEntity == null)
            frmAccountLayContainer.addView(accountCard.getView());
        else
            frmAccountLayContainer.addView(accountAfterLoginCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }
}