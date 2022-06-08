package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpStatus;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ShoppingCartPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ShoppingCartPageEntity;

public class ProductCard extends BaseCard{
    private int layout = R.layout.card_product;
    private View view;
    private Context context;

    public ImageView img;
    public TextView txtName, txtPrice, txtAddToCard;
    public ProductEntity productEntity;

    @Override
    protected void mapping() {
        img = view.findViewById(R.id.cardProduct_img);
        txtName = view.findViewById(R.id.cardProduct_txtName);
        txtPrice = view.findViewById(R.id.cardProduct_txtPrice);
        txtAddToCard = view.findViewById(R.id.cardProduct_txtAddToCard);

    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        txtAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);

                new Thread() {
                    @Override
                    public void run() {
                        ShoppingCartPageCrane shoppingCartPageCrane = new ShoppingCartPageCrane();
                        OrderItemEntity orderItemEntity = shoppingCartPageCrane.addToCart(context, productEntity.getCode(), 1);

                        ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, orderItemEntity.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                }.start();
            }
        });

    }

    public ProductCard(Context context, ProductEntity productEntity) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();

        this.productEntity = productEntity;

    }

}
