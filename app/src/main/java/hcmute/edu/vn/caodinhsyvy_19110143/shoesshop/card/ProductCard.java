package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;

public class ProductCard extends BaseCard{
    private int layout = R.layout.card_product;
    private View view;
    private Context context;

    public ImageView img;
    public TextView txtName, txtPrice;

    @Override
    protected void mapping() {
        img = view.findViewById(R.id.cardProduct_img);
        txtName = view.findViewById(R.id.cardProduct_txtName);
        txtPrice = view.findViewById(R.id.cardProduct_txtPrice);

    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {

    }

    public ProductCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }

//    public void setOnClickListener(View.OnClickListener listener) {
//        container.setOnClickListener(listener);
//    }
}
