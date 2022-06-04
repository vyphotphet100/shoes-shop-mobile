package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

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

}
