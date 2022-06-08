package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.LoginActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.UserEntity;

public class AppConstant {
    public static final String BASE_REST_URL = "http://192.168.1.13:8080/rest/";
    public static final String BASE_URL = "http://192.168.1.13:8080";

    public static UserEntity loggedInUserEntity;

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

                ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });

            }
        }.start();
    }

    public static Boolean checkLoggedIn(Context context) {
        if (AppConstant.loggedInUserEntity == null) {
            ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                    "You need to log in before visiting this page...", true);

            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Intent intent = new Intent(context, LoginActivity.class);
                            ((AppCompatActivity)context).startActivity(intent);
                            ((AppCompatActivity)context).finish();
                        }
                    });

                }
            }.start();
            return false;
        }

        return true;
    }

}
