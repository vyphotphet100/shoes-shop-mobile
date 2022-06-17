package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;

    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.privacyPolicyAct_frmAccountLayContainer);
        frameHeaderContainer = findViewById(R.id.privacyPolicyAct_headerContainer);
        frmInfoLayContainer = findViewById(R.id.privacyPolicyAct_frmInfoLayContainer);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        mapping();
        AppConstant.waitingAnimation(context, 800);

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);
    }

}