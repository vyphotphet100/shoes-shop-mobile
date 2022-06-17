package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class ContactUsActivity extends AppCompatActivity {
    private Context context;

    // frame layout to contain account card, info card, header card views
    public FrameLayout frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        mapping();
        AppConstant.waitingAnimation(context, 800); // waiting dialog: 800ms

        //Add account card, info card and header card views before beginning activity
        AppConstant.initSetupLayout(context, frmAccountLayContainer, frmInfoLayContainer, frameHeaderContainer);

    }

    //Function to map objects in code to associated views in UI
    private void mapping() {
        this.context = this;
        frmAccountLayContainer = findViewById(R.id.contactUsAct_frmAccountLayContainer);
        frameHeaderContainer = findViewById(R.id.contactUsAct_headerContainer);
        frmInfoLayContainer = findViewById(R.id.contactUsAct_frmInfoLayContainer);
    }
}