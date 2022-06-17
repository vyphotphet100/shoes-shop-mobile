package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.AboutUsActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ContactUsActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.PrivacyPolicyActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.TermsAndConditionsActivity;

public class InformationCard extends BaseCard{
    private int layout = R.layout.card_information; // main layout
    private View view;
    private Context context;

    // objects to map to ui of card
    public TextView txtAboutUs, txtPrivacyPolicy, txtTerms, txtContactUs;

    //Function to map objects in code to associated views in UI
    @Override
    protected void mapping() {
        txtAboutUs = view.findViewById(R.id.cardInfo_txtAboutUs);
        txtPrivacyPolicy = view.findViewById(R.id.cardInfo_txtPrivacyPolicy);
        txtTerms = view.findViewById(R.id.cardInfo_txtTerms);
        txtContactUs = view.findViewById(R.id.cardInfo_txtContactUs);
    }

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    // set events for objects
    @Override
    protected void setListenerEvent() {
        // when txt about us clicked
        txtAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof AboutUsActivity)) {
                    Intent intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent); // Start about us activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        // when txt Privacy Policy clicked
        txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof PrivacyPolicyActivity)) {
                    Intent intent = new Intent(context, PrivacyPolicyActivity.class);
                    context.startActivity(intent);  //start privacy policy activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        // when txt Terms clicked
        txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof TermsAndConditionsActivity)) {
                    Intent intent = new Intent(context, TermsAndConditionsActivity.class);
                    context.startActivity(intent); //start term and condition activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        //when txt contact us clicked
        txtContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof ContactUsActivity)) {
                    Intent intent = new Intent(context, ContactUsActivity.class);
                    context.startActivity(intent); //start contact us activity
                    ((AppCompatActivity)context).finish();
                }
            }
        });

    }

    //create information Card
    public InformationCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
