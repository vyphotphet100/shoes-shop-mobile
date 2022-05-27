package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.card;

import android.content.Context;
import android.view.View;

public abstract class BaseCard{
    private View view;
    private Context context;

    protected abstract void mapping();

    public abstract View getView();

    protected void onCreate() {
        mapping();
        setListenerEvent();
    }

    protected abstract void setListenerEvent();
}
