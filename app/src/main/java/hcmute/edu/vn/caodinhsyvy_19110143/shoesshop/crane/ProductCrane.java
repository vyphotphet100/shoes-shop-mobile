package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;

public class ProductCrane {
    public ProductEntity findOne(String code) {
        class FindOneTask extends AsyncTask<Object, Void, ResponseEntity<ProductEntity>> {

            private final String subUrl = "product/";
            private ProductEntity body = null;

            @Override
            protected ResponseEntity<ProductEntity> doInBackground(Object... objects) {
                String code = (String) objects[0];
                RestTemplate restTemplate = new RestTemplate();

                try {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<String> entity = new HttpEntity<String>(headers);
                    ResponseEntity<ProductEntity> resp = restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + code, HttpMethod.GET, entity, ProductEntity.class);
                    ProductEntity productEntity = resp.getBody();

                    body = productEntity;
                    return resp;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            public ProductEntity getBody() {
                return this.body;
            }

        }

        try {
            FindOneTask findOneTask = new FindOneTask();
            findOneTask.execute(code);
            while (findOneTask.getBody() == null) {
            }

            ProductEntity productEntity = findOneTask.getBody();

            return productEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
