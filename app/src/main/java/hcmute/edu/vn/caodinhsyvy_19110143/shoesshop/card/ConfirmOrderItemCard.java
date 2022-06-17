package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;

public class ConfirmOrderItemCard extends BaseCard{
    // declare the necessary variables used in the confirm order item card
    private int layout = R.layout.card_confirm_order_item;
    private View view;
    private Context context;

    public TextView txtProductName, txtCategory, txtBrand, txtQuantity, txtUnitPrice, txtTotalCost;

    //Function to map object in code to text view
    @Override
    protected void mapping() {
        txtProductName = view.findViewById(R.id.cardConfirmOrderItem_txtProductName);
        txtCategory = view.findViewById(R.id.cardConfirmOrderItem_txtCategory);
        txtBrand = view.findViewById(R.id.cardConfirmOrderItem_txtBrand);
        txtQuantity = view.findViewById(R.id.cardConfirmOrderItem_txtQuantity);
        txtUnitPrice = view.findViewById(R.id.cardConfirmOrderItem_txtUnitPrice);
        txtTotalCost = view.findViewById(R.id.cardConfirmOrderItem_txtTotalCost);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {

    }

    // function confirm order item card
    public ConfirmOrderItemCard(Context context, OrderItemEntity orderItemEntity) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();

        txtProductName.setText(orderItemEntity.getProduct().getTitle());
        txtCategory.setText(orderItemEntity.getProduct().getCategory().getName());
        txtBrand.setText(orderItemEntity.getProduct().getBrand().getName());
        txtQuantity.setText(orderItemEntity.getQuantityBought().toString());
        txtUnitPrice.setText(String.valueOf(orderItemEntity.getProduct().getPrice()));
        txtTotalCost.setText(orderItemEntity.getTotalCost().toString());

    }
}
