package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountAfterLoginCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.AccountCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.InformationCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ProductCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card.ProductInShoppingCartCard;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.HomePageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page.ShoppingCartPageCrane;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.HomePageEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ShoppingCartPageEntity;

public class ShoppingCartActivity extends AppCompatActivity {

    private Context context;
    public FrameLayout frameHeaderContainer;
    public HeaderCard headerCard;
    public HorizontalScrollView hsv;
    public TableLayout tbLayOrderItemContainer;
    public TextView txtTotal, txtCheckOut, txtContinueShopping;
    public List<OrderItemEntity> orderItemEntities = new ArrayList<>();
    public List<ProductInShoppingCartCard> productInShoppingCartCards = new ArrayList<>();
    public ShoppingCartPageCrane shoppingCartPageCrane;


    private void mapping() {
        this.context = this;
        frameHeaderContainer = findViewById(R.id.shoppingCartAct_headerContainer);
        headerCard = new HeaderCard(context);
        hsv = findViewById(R.id.shoppingCartAct_hsv);
        tbLayOrderItemContainer = findViewById(R.id.shoppingCartAct_tbLayOrderItemContainer);
        txtTotal = findViewById(R.id.shoppingCartAct_txtTotal);
        txtCheckOut = findViewById(R.id.shoppingCartAct_txtCheckOut);
        txtContinueShopping = findViewById(R.id.shoppingCartAct_txtContinueShopping);
        shoppingCartPageCrane = new ShoppingCartPageCrane();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mapping();

        if (!AppConstant.checkLoggedIn(context))
            return;

        initSetupLayout();
        loadInitData();
        AppConstant.waitingAnimation(context, 600);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                hsv.setScrollX(0);
                hsv.setScrollY(0);
            }
        }.start();
        setEvent();
    }

    private void initSetupLayout() {
        frameHeaderContainer.removeAllViews();
        frameHeaderContainer.addView(headerCard.getView());
    }

    private void loadInitData() {

        ProgressDialog progressDialog = ProgressDialog.show(this, "Noriva",
                "Loading...", true);

        new Thread() {

            @Override
            public void run() {
                ShoppingCartPageEntity shoppingCartPageEntity = shoppingCartPageCrane.getDataShoppingCartPage();
                orderItemEntities = shoppingCartPageEntity.getOrderItemEntities();
                // set to UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++)
                            tbLayOrderItemContainer.removeViewAt(1);

                        productInShoppingCartCards = new ArrayList<>();
                        for (OrderItemEntity orderItemEntity : shoppingCartPageEntity.getOrderItemEntities()) {
                            ProductInShoppingCartCard productInShoppingCartCard = new ProductInShoppingCartCard((ShoppingCartActivity) context, orderItemEntity);
                            tbLayOrderItemContainer.addView(productInShoppingCartCard.getView());
                            productInShoppingCartCards.add(productInShoppingCartCard);
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    private void setEvent() {
        txtCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCheckOut.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtCheckOut.setTextColor(Color.parseColor("#000000"));
                ProgressDialog progressDialog = ProgressDialog.show(context, "Noriva",
                        "Loading...", true);
                new Thread() {
                    @Override
                    public void run() {

                        HashMap<String, Integer> orderItemIdsNeededUpdateQuantity = new HashMap<>();
                        for (ProductInShoppingCartCard productInShoppingCartCard : productInShoppingCartCards) {
                            if (productInShoppingCartCard.chBxPay.isChecked()) {
                                orderItemIdsNeededUpdateQuantity.put(
                                        productInShoppingCartCard.orderItemEntity.getId().toString(),
                                        Integer.valueOf(productInShoppingCartCard.edtTxtQuantity.getText().toString()));
                            }
                        }

                        if (orderItemIdsNeededUpdateQuantity.isEmpty()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "You have chosen nothing!", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                            return;
                        }

                        Boolean isCheckedOut = shoppingCartPageCrane.checkOut(orderItemIdsNeededUpdateQuantity);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isCheckedOut) {
                                    AppConstant.waitingAnimation(context, 600);
                                    Intent intent = new Intent(context, CheckOutActivity.class);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    finish();
                                }
                                else {
                                    Toast.makeText(context, "Something's wrong. Please check again!", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    }
                }.start();
            }
        });

        txtContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtContinueShopping.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtContinueShopping.setTextColor(Color.parseColor("#000000"));
                Intent intent = new Intent(context, ProductListActivity.class);
                context.startActivity(intent);
                finish();
            }
        });
    }

    public void updateTotalCost() {
        Float total = 0f;
        for (ProductInShoppingCartCard productInShoppingCartCard : productInShoppingCartCards) {
            if (productInShoppingCartCard.chBxPay.isChecked()) {
                total += Float.valueOf(productInShoppingCartCard.txtTotalCost.getText().toString().substring(1));
            }
        }

        txtTotal.setText("$" + total);
    }

    public void deleteOrderItem(Integer id) {
        int i = 0;
        for (; i < orderItemEntities.size(); i++) {
            if (orderItemEntities.get(i).getId().equals(id)) {
                orderItemEntities.remove(i);
                break;
            }
        }

        productInShoppingCartCards.remove(i);
        tbLayOrderItemContainer.removeViewAt(i + 1);

    }




}