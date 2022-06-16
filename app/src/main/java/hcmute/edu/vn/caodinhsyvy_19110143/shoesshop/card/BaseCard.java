package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.view.View;

public abstract class BaseCard{
    // declare the necessary variables used in the base card
    private View view;
    private Context context;

    protected abstract void mapping(); //Function to map object in code

    public abstract View getView();
//create
    protected void onCreate() {
        mapping(); //Function to map object in code
        setListenerEvent();
    }

    protected abstract void setListenerEvent();
}
