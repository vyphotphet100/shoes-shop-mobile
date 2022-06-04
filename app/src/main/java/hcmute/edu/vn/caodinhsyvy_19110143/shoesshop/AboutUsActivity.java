package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class AboutUsActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;
    public HeaderCard headerCard;
    public AccountCard accountCard;
    public InformationCard informationCard;

    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.aboutUsAct_frmAccountLayContainer);
        frmInfoLayContainer = findViewById(R.id.aboutUsAct_frmInfoLayContainer);
        frameHeaderContainer = findViewById(R.id.aboutUsAct_headerContainer);
        headerCard = new HeaderCard(context);
        accountCard = new AccountCard(context);
        informationCard = new InformationCard(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();

    }

    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        frmAccountLayContainer.addView(accountCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }
}