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

public class AboutUsActivity extends AppCompatActivity {
    // declare the necessary variables used in the form
    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountCard accountCard;
    public AccountAfterLoginCard accountAfterLoginCard;
    public InformationCard informationCard;

    //Function to map object in code to view in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.aboutUsAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.aboutUsAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.aboutUsAct_headerContainer);
        headerCard = new HeaderCard(context);
        accountCard = new AccountCard(context);
        accountAfterLoginCard = new AccountAfterLoginCard(context);
        informationCard = new InformationCard(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         // save instance state
        setContentView(R.layout.activity_about_us); // show ui activity about us
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();

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