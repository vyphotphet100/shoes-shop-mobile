package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.BrandEntity;

public class BrandCrane {

    public BrandEntity findOne(String code, Boolean showProducts) {
        class FindOneTask extends AsyncTask<Object, Void, ResponseEntity<BrandEntity>> {

            private final String subUrl = "brand/";
            private BrandEntity body = null;

            @Override
            protected ResponseEntity<BrandEntity> doInBackground(Object... objects) {
                String code = (String) objects[0];
                Boolean showProducts = (Boolean) objects[1];

                RestTemplate restTemplate = new RestTemplate();

                try {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<String> entity = new HttpEntity<String>(headers);
                    ResponseEntity<BrandEntity> resp =
                            restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + "{code}?showProducts={showProducts}",
                                    HttpMethod.GET, entity, BrandEntity.class, code, showProducts);
                    BrandEntity brandEntity = resp.getBody();

                    body = brandEntity;
                    return resp;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            public BrandEntity getBody() {
                return this.body;
            }

        }

        try {
            FindOneTask findOneTask = new FindOneTask();
            findOneTask.execute(code, showProducts);
            while (findOneTask.getBody() == null) {
            }

            BrandEntity brandEntity = findOneTask.getBody();

            return brandEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<BrandEntity> findAll(Boolean showProducts) {
        class FindAllTask extends AsyncTask<Object, Void, List<BrandEntity>> {

            private final String subUrl = "brands";
            private List<BrandEntity> body = null;

            @Override
            protected List<BrandEntity> doInBackground(Object... objects) {
                Boolean showProducts = (Boolean) objects[0];

                RestTemplate restTemplate = new RestTemplate();
                try {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<String> entity = new HttpEntity<String>(headers);
                    ResponseEntity<BrandEntity[]> resp =
                            restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl+"?showProducts={showProducts}",
                            HttpMethod.GET, entity, BrandEntity[].class, showProducts);
                    BrandEntity[] brandEntities = resp.getBody();
                    List<BrandEntity> res = new ArrayList<>(Arrays.asList(brandEntities));

                    body = res;
                    return res;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            public List<BrandEntity> getBody() {
                return this.body;
            }

        }

        try {
            FindAllTask findAllTask = new FindAllTask();
            findAllTask.execute(showProducts);
            while (findAllTask.getBody() == null) {
            }

            List<BrandEntity> brandEntities = findAllTask.getBody();
            return brandEntities;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
