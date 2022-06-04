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
    private int layout = R.layout.card_information;
    private View view;
    private Context context;

    public TextView txtAboutUs, txtPrivacyPolicy, txtTerms, txtContactUs;

    @Override
    protected void mapping() {
        txtAboutUs = view.findViewById(R.id.cardInfo_txtAboutUs);
        txtPrivacyPolicy = view.findViewById(R.id.cardInfo_txtPrivacyPolicy);
        txtTerms = view.findViewById(R.id.cardInfo_txtTerms);
        txtContactUs = view.findViewById(R.id.cardInfo_txtContactUs);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        txtAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof AboutUsActivity)) {
                    Intent intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof PrivacyPolicyActivity)) {
                    Intent intent = new Intent(context, PrivacyPolicyActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof TermsAndConditionsActivity)) {
                    Intent intent = new Intent(context, TermsAndConditionsActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        txtContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof ContactUsActivity)) {
                    Intent intent = new Intent(context, ContactUsActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

    }

    public InformationCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
