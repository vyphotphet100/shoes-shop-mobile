package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.view.View;

// card to be extended from other cards, contain common methods of all cards
public abstract class BaseCard{
    private View view;
    private Context context;

    protected abstract void mapping(); //Function to map objects in code to associated views in UI

    // return to view of layout
    public abstract View getView();

    protected void onCreate() {
        mapping();
        setListenerEvent();
    }

    // set events for objects
    protected abstract void setListenerEvent();
}
