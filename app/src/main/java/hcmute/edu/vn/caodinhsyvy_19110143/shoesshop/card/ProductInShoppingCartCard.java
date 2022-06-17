package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ProductDetailActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.ShoppingCartActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ShoppingCartPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;

public class ProductInShoppingCartCard extends BaseCard {
    private int layout = R.layout.card_product_in_shopping_cart; // main layout
    private View view;
    private ShoppingCartActivity context;

    // objects to map to ui of card
    public ImageView img, imgResetQuantity, imgDelete;
    public TextView txtProductName, txtBrand, txtUnitPrice, txtTotalCost, txtIncQty, txtDecQty;
    public EditText edtTxtQuantity;
    public CheckBox chBxPay;
    public OrderItemEntity orderItemEntity;

    //Function to map objects in code to associated views in UI
    @Override
    protected void mapping() {
        txtProductName = view.findViewById(R.id.cardProductInShoppingCart_txtProductName);
        txtBrand = view.findViewById(R.id.cardProductInShoppingCart_txtBrand);
        txtUnitPrice = view.findViewById(R.id.cardProductInShoppingCart_txtUnitPrice);
        txtTotalCost = view.findViewById(R.id.cardProductInShoppingCart_txtTotalCost);
        txtIncQty = view.findViewById(R.id.cardProductInShoppingCart_txtIncQty);
        txtDecQty = view.findViewById(R.id.cardProductInShoppingCart_txtDecQty);
        edtTxtQuantity = view.findViewById(R.id.cardProductInShoppingCart_edtTxtQuantity);
        chBxPay = view.findViewById(R.id.cardProductInShoppingCart_chBxPay);
        img = view.findViewById(R.id.cardProductInShoppingCart_img);
        imgDelete = view.findViewById(R.id.cardProductInShoppingCart_imgDelete);
        imgResetQuantity = view.findViewById(R.id.cardProductInShoppingCart_imgResetQuantity);
    }

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    // set events for objects
    @Override
    protected void setListenerEvent() {
        //when img Reset Quantity clicked
        imgResetQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTxtQuantity.setText(orderItemEntity.getQuantityBought().toString());
            }
        });

        //when edt Txt Quantity changed text
        edtTxtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtTxtQuantity.getText().toString().trim().equals("")) {
                    Float totalCost = orderItemEntity.getProduct().getPrice() * Integer.valueOf(edtTxtQuantity.getText().toString());
                    txtTotalCost.setText("$" + totalCost.toString());
                    context.updateTotalCost();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //when txtIncQty clicked
        txtIncQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer qty = Integer.valueOf(edtTxtQuantity.getText().toString());
                edtTxtQuantity.setText((++qty).toString());
            }
        });

        //when txtDecQty clicked
        txtDecQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer qty = Integer.valueOf(edtTxtQuantity.getText().toString());
                if (qty != 1)
                    edtTxtQuantity.setText((--qty).toString());
            }
        });

        //when chBxPay changed checked
        chBxPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                context.updateTotalCost();
            }
        });

        //when imgDelete clicked
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        ShoppingCartPageCrane shoppingCartPageCrane = new ShoppingCartPageCrane();
                        Boolean isDeleted = shoppingCartPageCrane.deleteOrderItemById(orderItemEntity.getId());

                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isDeleted)
                                    context.deleteOrderItem(orderItemEntity.getId());
                            }
                        });
                    }
                }.start();
            }
        });

        //when img clicked
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("code", orderItemEntity.getProduct().getCode());
                context.startActivity(intent);
            }
        });
    }

    //create Product In Shopping Cart Card
    public ProductInShoppingCartCard(ShoppingCartActivity context, OrderItemEntity orderItemEntity) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
        this.orderItemEntity = orderItemEntity;

        Picasso.with(context)
                .load(AppConstant.BASE_URL + orderItemEntity.getProduct().getPictureUrl())
                .into(img);
        txtProductName.setText(orderItemEntity.getProduct().getTitle());
        txtBrand.setText(orderItemEntity.getProduct().getBrand().getName());
        edtTxtQuantity.setText(orderItemEntity.getQuantityBought().toString());
        txtUnitPrice.setText("$" + String.valueOf(orderItemEntity.getProduct().getPrice()));
        txtTotalCost.setText("$" + orderItemEntity.getTotalCost().toString());
    }
}
