package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.LoginActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class AppConstant {
//    public static final String BASE_REST_URL = "http://192.168.1.13:8080/rest/";
//    public static final String BASE_URL = "http://192.168.1.13:8080";
    public static final String BASE_REST_URL = "https://shoes-shop-mobile.herokuapp.com/rest/";
    public static final String BASE_URL = "https://shoes-shop-mobile.herokuapp.com";

    public static UserEntity loggedInUserEntity;

    // function waiting animation with : context and time duration
    public static void waitingAnimation(Context context, Integer duration) {
        ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                "Loading...", true);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });

            }
        }.start();
    }

    //Function check login user entity
    public static Boolean checkLoggedIn(Context context) {
        if (AppConstant.loggedInUserEntity == null) {
            final ProgressDialog[] progressDialog = {null};
            ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog[0] = ProgressDialog.show(context, "Noriva",
                            "You need to log in to do this action!", true);
                }
            });


            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            while (progressDialog[0] == null) {
                            }
                            progressDialog[0].dismiss();
                            Intent intent = new Intent(context, LoginActivity.class);
                            ((AppCompatActivity) context).startActivity(intent);
                            ((AppCompatActivity) context).finish();
                        }
                    });

                }
            }.start();
            return false;
        }

        return true;
    }

    //Add account card, info card and header card views before beginning activity
    public static void initSetupLayout(Context context, FrameLayout frmAccountLayContainer,
                                       FrameLayout frmInfoLayContainer, FrameLayout frameHeaderContainer) {
        HeaderCard headerCard = new HeaderCard(context);
        AccountCard accountCard = new AccountCard(context);
        AccountAfterLoginCard accountAfterLoginCard = new AccountAfterLoginCard(context);
        InformationCard informationCard = new InformationCard(context);


        frmAccountLayContainer.removeAllViews();
        if (AppConstant.loggedInUserEntity == null) // user did not log in
            frmAccountLayContainer.addView(accountCard.getView());
        else // user logged in -> add 'account after login card' view
            frmAccountLayContainer.addView(accountAfterLoginCard.getView());

        frmInfoLayContainer.removeAllViews();
        frmInfoLayContainer.addView(informationCard.getView());

        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

}
