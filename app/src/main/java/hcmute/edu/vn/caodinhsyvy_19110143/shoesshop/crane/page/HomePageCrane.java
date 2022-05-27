package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.HomePageEntity;

public class HomePageCrane {
    public HomePageEntity getDataHomePage() {
        class GetDataHomePageTask extends AsyncTask<Object, Void, ResponseEntity<HomePageEntity>> {

            private final String subUrl = "page/home";
            private HomePageEntity body = null;

            @Override
            protected ResponseEntity<HomePageEntity> doInBackground(Object... objects) {
                RestTemplate restTemplate = new RestTemplate();

                try {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<String> entity = new HttpEntity<String>(headers);
                    ResponseEntity<HomePageEntity> resp =
                            restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl,
                                    HttpMethod.GET, entity, HomePageEntity.class);
                    HomePageEntity homePageEntity = resp.getBody();

                    body = homePageEntity;
                    return resp;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            public HomePageEntity getBody() {
                return this.body;
            }

        }

        try {
            GetDataHomePageTask getDataHomePageTask = new GetDataHomePageTask();
            getDataHomePageTask.execute();
            while (getDataHomePageTask.getBody() == null) {
            }

            HomePageEntity homePageEntity = getDataHomePageTask.getBody();

            return homePageEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
