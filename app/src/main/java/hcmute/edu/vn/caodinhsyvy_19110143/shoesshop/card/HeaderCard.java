package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.RegisterActivity;

public class HeaderCard extends BaseCard{
    private int layout = R.layout.card_header;
    private View view;
    private Context context;

    public ImageView imgSearch, imgAccount, imgShoppingCart;


    @Override
    protected void mapping() {
        imgSearch = view.findViewById(R.id.headerCard_imgSearch);
        imgAccount = view.findViewById(R.id.headerCard_imgAccount);
        imgShoppingCart = view.findViewById(R.id.headerCard_imgShoppingCart);
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
                Intent intent = new Intent(context, RegisterActivity.class);
                context.startActivity(intent);
//                ((AppCompatActivity)context).finish();
            }
        });

    }

    public HeaderCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }
}
