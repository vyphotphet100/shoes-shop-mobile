package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.adapter.RvContainerHomeAdapter;

public class HomeActivity extends AppCompatActivity {

    public ScrollView srv;
    public ConstraintLayout container;
    public RecyclerView rvContainer;
    public LinearLayout itemContainer;
    public ConstraintLayout nikeBannerConsLayout;
    public ConstraintLayout adidasBannerConsLayout;
    public ConstraintLayout pumaBannerConsLayout;
    public ConstraintLayout converseBannerConsLayout;
    public HorizontalScrollView banner;
    public View headerCard;

    private void mapping() {
        srv = findViewById(R.id.homeAct_srv);
        container = findViewById(R.id.homeAct_container);
        rvContainer = findViewById(R.id.homeAct_rvContainer);
        itemContainer = findViewById(R.id.homeAct_itemContainer);
        nikeBannerConsLayout = findViewById(R.id.homeAct_nikeBannerConsLayout);
        adidasBannerConsLayout = findViewById(R.id.homeAct_adidasBannerConsLayout);
        pumaBannerConsLayout = findViewById(R.id.homeAct_pumaBannerConsLayout);
        converseBannerConsLayout = findViewById(R.id.homeAct_converseBannerConsLayout);
        banner = findViewById(R.id.homeAct_hsvBannerContainer);
        headerCard = View.inflate(this, R.layout.card_header, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mapping();


    }

    @Override
    protected void onResume() {
        super.onResume();

//        List<View> lstViews = new ArrayList<>();
//        lstViews.add(headerCard);
//        lstViews.add(banner);
//        lstViews.add(nikeBannerConsLayout);
//        lstViews.add(adidasBannerConsLayout);
//        lstViews.add(pumaBannerConsLayout);
//        lstViews.add(converseBannerConsLayout);
//
//        itemContainer.removeAllViews();
//        srv.removeAllViews();
//        container.removeAllViews();
//
//        RvContainerHomeAdapter rvContainerHomeAdapter = new RvContainerHomeAdapter(this, lstViews);
//        rvContainer.setAdapter(rvContainerHomeAdapter);
//        rvContainer.setLayoutManager(new LinearLayoutManager(this));
//        container.addView(rvContainer);
    }
}