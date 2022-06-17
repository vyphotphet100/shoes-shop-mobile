package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ShoppingCartActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;

public class HeaderCard extends BaseCard {
    private int layout = R.layout.card_header; // main layout
    private View view;
    private Context context;

    // objects to map to ui of card
    public ImageView imgSearch, imgAccount, imgShoppingCart, imgLogo;
    public TextView txtWomenShoes, txtMenShoes, txtSportsWear;

    //Function to map objects in code to associated views in UI
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

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    // set events for objects
    @Override
    protected void setListenerEvent() {
        // when img Account clicked
        imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstant.loggedInUserEntity == null) { // if log in user entity is null
                    if (!(context instanceof LoginActivity)) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        if (!(context instanceof HomeActivity))
                            ((AppCompatActivity) context).finish();
                    }
                } else {
                    if (!(context instanceof MyAccountActivity)) {
                        Intent intent = new Intent(context, MyAccountActivity.class);
                        context.startActivity(intent);
                        if (!(context instanceof HomeActivity))
                            ((AppCompatActivity) context).finish();
                    }
                }

            }
        });

        // when imgSearch clicked
        imgSearch.setOnClickListener(new View.OnClickListener() { //when img Search clicked
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                View cardSearch = View.inflate(context, R.layout.card_search, null);
                TextView txtSearch = cardSearch.findViewById(R.id.cardSearch_txtSearch);
                TextView edtTxtSearch = cardSearch.findViewById(R.id.cardSearch_edtTxtSearch);
                b.setView(cardSearch);
                AlertDialog al = b.create();
                al.show();
                txtSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtSearch.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        txtSearch.setTextColor(Color.parseColor("#000000"));
                        Intent intent = new Intent(context, ProductListActivity.class);
                        intent.putExtra("params", "keyword=" + edtTxtSearch.getText().toString());
                        context.startActivity(intent);
                        if (!(context instanceof HomeActivity))
                            ((AppCompatActivity) context).finish();
                        al.dismiss();
                    }
                });
            }
        });

        // when imgLogo clicked
        imgLogo.setOnClickListener(new View.OnClickListener() { //when img Logo clicked
            @Override
            public void onClick(View v) {
                if (!(context instanceof HomeActivity)) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    if (!(context instanceof HomeActivity))
                        ((AppCompatActivity) context).finish();
                }
            }
        });

        // when imgShoppingCart clicked
        imgShoppingCart.setOnClickListener(new View.OnClickListener() { //when img shopping cart clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingCartActivity.class);
                context.startActivity(intent);
                if (!(context instanceof HomeActivity))
                    ((AppCompatActivity) context).finish();
            }
        });

        // when txtWomenShoes clicked
        txtWomenShoes.setOnClickListener(new View.OnClickListener() { //when txt womenshoes clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "categoryCode=CATEGORY1");
                context.startActivity(intent);
                if (!(context instanceof HomeActivity))
                    ((AppCompatActivity) context).finish();
            }
        });

        // when txtMenShoes clicked
        txtMenShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "categoryCode=CATEGORY2");
                context.startActivity(intent);
                if (!(context instanceof HomeActivity))
                    ((AppCompatActivity) context).finish();
            }
        });

        // when txtSportsWear clicked
        txtSportsWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("params", "categoryCode=CATEGORY3");
                context.startActivity(intent);
                if (!(context instanceof HomeActivity))
                    ((AppCompatActivity) context).finish();
            }
        });

    }

    //create header card
    public HeaderCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
