package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.HomeActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.LoginActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.MyAccountActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ProductListActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.RegisterActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ShoppingCartActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class HeaderCard extends BaseCard {
    private int layout = R.layout.card_header;
    private View view;
    private Context context;

    public ImageView imgSearch, imgAccount, imgShoppingCart, imgLogo;
    public TextView txtWomenShoes, txtMenShoes, txtSportsWear;


    @Override
    protected void mapping() {
        imgSearch = view.findViewById(R.id.headerCard_imgSearch);
        imgAccount = view.findViewById(R.id.headerCard_imgAccount);
        imgShoppingCart = view.findViewById(R.id.headerCard_imgShoppingCart);
        imgLogo = view.findViewById(R.id.cardHeader_imgLogo);
        txtWomenShoes = view.findViewById(R.id.headerCard_txtWomenShoes);
        txtMenShoes = view.findViewById(R.id.headerCard_txtMenShoes);
        txtSportsWear = view.findViewById(R.id.headerCard_txtSportsWear);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstant.loggedInUserEntity == null) {
                    if (!(context instanceof LoginActivity)) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        ((AppCompatActivity)context).finish();
                    }
                }
                else {
                    if (!(context instanceof MyAccountActivity)) {
                        Intent intent = new Intent(context, MyAccountActivity.class);
                        context.startActivity(intent);
                        ((AppCompatActivity)context).finish();
                    }
                }

            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                View cardSearch = View.inflate(context, R.layout.card_search, null);
                TextView txtSearch = cardSearch.findViewById(R.id.cardSearch_txtSearch);
                b.setView(cardSearch);
                AlertDialog al = b.create();
                al.show();
                txtSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        al.dismiss();
                    }
                });
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(context instanceof HomeActivity)) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });

        imgShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingCartActivity.class);
                context.startActivity(intent);
                ((AppCompatActivity)context).finish();
            }
        });

        txtWomenShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "categoryCode=CATEGORY1");
                context.startActivity(intent);
                ((AppCompatActivity)context).finish();
            }
        });

    }

    public HeaderCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
