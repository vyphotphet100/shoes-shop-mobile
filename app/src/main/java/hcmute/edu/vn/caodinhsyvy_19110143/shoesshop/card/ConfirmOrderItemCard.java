package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;

public class ConfirmOrderItemCard extends BaseCard{
    private int layout = R.layout.card_confirm_order_item; // main layout
    private View view;
    private Context context;

    // objects to map to ui of card
    public TextView txtProductName, txtCategory, txtBrand, txtQuantity, txtUnitPrice, txtTotalCost;

    //Function to map objects in code to associated views in UI
    @Override
    protected void mapping() {
        txtProductName = view.findViewById(R.id.cardConfirmOrderItem_txtProductName);
        txtCategory = view.findViewById(R.id.cardConfirmOrderItem_txtCategory);
        txtBrand = view.findViewById(R.id.cardConfirmOrderItem_txtBrand);
        txtQuantity = view.findViewById(R.id.cardConfirmOrderItem_txtQuantity);
        txtUnitPrice = view.findViewById(R.id.cardConfirmOrderItem_txtUnitPrice);
        txtTotalCost = view.findViewById(R.id.cardConfirmOrderItem_txtTotalCost);
    }

    // return to view of this layout
    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {

    }

    // create confirm order item card
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
