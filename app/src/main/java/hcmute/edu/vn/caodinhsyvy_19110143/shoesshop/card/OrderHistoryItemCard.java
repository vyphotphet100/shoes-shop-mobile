package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.OrderDetailsActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.OrderItemEntity;

public class OrderHistoryItemCard extends BaseCard {
    private int layout = R.layout.card_order_history_item;
    private View view;
    private Context context;

    public TextView txtOrderID, txtLastName, txtProductName,
            txtNoOfProducts, txtShipment, txtTotal, txtDateAdded;
    ImageButton btnShow;


    @Override
    protected void mapping() {
        txtOrderID = view.findViewById(R.id.cardOrderHistoryItem_txtOrderID);
        txtLastName = view.findViewById(R.id.cardOrderHistoryItem_txtLastName);
        txtProductName = view.findViewById(R.id.cardOrderHistoryItem_txtProductName);
        txtNoOfProducts = view.findViewById(R.id.cardOrderHistoryItem_txtNoOfProducts);
        txtShipment = view.findViewById(R.id.cardOrderHistoryItem_txtShipment);
        txtTotal = view.findViewById(R.id.cardOrderHistoryItem_txtTotal);
        txtDateAdded = view.findViewById(R.id.cardOrderHistoryItem_txtDateAdded);
        btnShow = view.findViewById(R.id.cardOrderHistoryItem_btnShow);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {

    }

    public OrderHistoryItemCard(Context context, OrderItemEntity orderItemEntity) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();

        txtOrderID.setText(orderItemEntity.getId().toString());
        txtLastName.setText(orderItemEntity.getCustomer().getLastName());
        txtProductName.setText(orderItemEntity.getProduct().getTitle());
        txtNoOfProducts.setText(orderItemEntity.getQuantityBought().toString());
        txtShipment.setText("Shipping");
        txtTotal.setText(orderItemEntity.getTotalCost().toString());
        txtDateAdded.setText(orderItemEntity.getPayment().getPaymentDate().toString());

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id", orderItemEntity.getId());
                context.startActivity(intent);
            }
        });
    }
}
