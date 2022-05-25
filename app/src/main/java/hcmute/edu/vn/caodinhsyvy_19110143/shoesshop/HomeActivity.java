package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop;

import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.BrandEntity;

public class HomeActivity extends AppCompatActivity {

    public NestedScrollView srv;
    public ConstraintLayout container;
    public LinearLayout itemContainer;
    public ConstraintLayout nikeBannerConsLayout;
    public ConstraintLayout adidasBannerConsLayout;
    public ConstraintLayout pumaBannerConsLayout;
    public ConstraintLayout converseBannerConsLayout;
    public HorizontalScrollView banner;
    public View headerCard;
    public ImageView imgDown;

    private void mapping() {
        srv = findViewById(R.id.homeAct_srv);
        container = findViewById(R.id.homeAct_container);
        itemContainer = findViewById(R.id.homeAct_itemContainer);
        nikeBannerConsLayout = findViewById(R.id.homeAct_nikeBannerConsLayout);
        adidasBannerConsLayout = findViewById(R.id.homeAct_adidasBannerConsLayout);
        pumaBannerConsLayout = findViewById(R.id.homeAct_pumaBannerConsLayout);
        converseBannerConsLayout = findViewById(R.id.homeAct_converseBannerConsLayout);
        banner = findViewById(R.id.homeAct_hsvBannerContainer);
        headerCard = View.inflate(this, R.layout.card_header, null);
        imgDown = findViewById(R.id.homeAct_imgDown);
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
    }

    private void test() {
        ClientConfig clientConfig = new DefaultClientConfig();


        // Tạo đối tượng Client dựa trên cấu hình.
        Client client = Client.create(clientConfig);

        WebResource webResource = client.resource("http://localhost:8080/RESTfulCRUD/rest/employees/E01");

        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON) //
                .header("content-type", MediaType.APPLICATION_JSON);

        ClientResponse response = builder.get(ClientResponse.class);


        // Trạng thái 200 là thành công
        if (response.getStatus() != 200) {
            System.out.println("Failed with HTTP Error code: " + response.getStatus());
            String error = response.getEntity(String.class);
            System.out.println("Error: " + error);
            return;
        }

        System.out.println("Output from Server .... \n");

        BrandEntity brand = (BrandEntity) response.getEntity(BrandEntity.class);


    }
}