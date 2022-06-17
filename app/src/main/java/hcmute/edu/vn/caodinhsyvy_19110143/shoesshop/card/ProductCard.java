package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ProductDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ShoppingCartPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;

public class ProductCard extends BaseCard {
    private int layout = R.layout.card_product; // main layout
    private View view;
    private Context context;

    // objects to map to ui of card
    public ImageView img, imgSeeDetail;
    public TextView txtName, txtPrice, txtAddToCard;
    public ProductEntity productEntity;
    public Integer quantity = 1;

    //Function to map objects in code to associated views in UI
    @Override
    protected void mapping() {
        img = view.findViewById(R.id.cardProduct_img);
        txtName = view.findViewById(R.id.cardProduct_txtName);
        txtPrice = view.findViewById(R.id.cardProduct_txtPrice);
        txtAddToCard = view.findViewById(R.id.cardProduct_txtAddToCard);
        imgSeeDetail = view.findViewById(R.id.cardProduct_imgSeeDetail);
    }

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    // set events for objects
    @Override
    protected void setListenerEvent() {
        //when txt Add To Card clicked
        txtAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);

                new Thread() {
                    @Override
                    public void run() {
                        ShoppingCartPageCrane shoppingCartPageCrane = new ShoppingCartPageCrane();
                        OrderItemEntity orderItemEntity = shoppingCartPageCrane.addToCart(context, productEntity.getCode(), quantity);

                        ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (orderItemEntity.getMessage() != null && !orderItemEntity.getMessage().trim().equals(""))
                                    Toast.makeText(context, orderItemEntity.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                }.start();
            }
        });

        //when img See Detail clicked
        imgSeeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("code", productEntity.getCode());
                context.startActivity(intent); //start product detail activity
                ((AppCompatActivity)context).finish();
            }
        });

        //when img clicked
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSeeDetail.callOnClick();
            }
        });

    }

    //create product card
    public ProductCard(Context context, ProductEntity productEntity) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();

        this.productEntity = productEntity;

    }

}
