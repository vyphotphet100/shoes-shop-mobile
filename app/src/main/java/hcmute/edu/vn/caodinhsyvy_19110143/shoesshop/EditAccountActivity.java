package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class EditAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        mapping();
        AppConstant.waitingAnimation(context, 800);
        initSetupLayout();
        initSetupData();

        setEvent();

    }

    private void initSetupLayout() {
        frmAccountLayContainer.removeAllViews();
        frmAccountLayContainer.addView(accountAfterLoginCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void initSetupData() {
        edtTxtFirstName.setText(AppConstant.loggedInUserEntity.getFirstName());
        edtTxtLastName.setText(AppConstant.loggedInUserEntity.getLastName());
        edtTxtPhone.setText(AppConstant.loggedInUserEntity.getPhone());
        edtTxtEmail.setText(AppConstant.loggedInUserEntity.getEmail());
    }
}