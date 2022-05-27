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
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.CategoryEntity;

public class CategoryCrane {

    public CategoryEntity findOne(String code, Boolean showProducts) {
        class FindOneTask extends AsyncTask<Object, Void, ResponseEntity<CategoryEntity>> {

            private final String subUrl = "category/";
            private CategoryEntity body = null;

            @Override
            protected ResponseEntity<CategoryEntity> doInBackground(Object... objects) {
                String code = (String) objects[0];
                Boolean showProducts = (Boolean) objects[1];

                RestTemplate restTemplate = new RestTemplate();

                try {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

//                    LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//                    params.add("showProducts", hashMap.get("showProducts"));
//
//                    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AppConstant.BASE_REST_URL + subUrl)
//                            .pa.queryParams(params);
//                    UriComponents uriComponents = builder.build().encode(); // encode() is to ensure that characters like {, }, are preserved and not encoded. Skip if not needed.
//
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<Object> entity = new HttpEntity<Object>(headers);
                    ResponseEntity<CategoryEntity> resp =
                            restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + "{code}?showProducts={showProducts}",
                                    HttpMethod.GET, entity, CategoryEntity.class, code, showProducts);
                    CategoryEntity categoryEntity = resp.getBody();

                    this.body = categoryEntity;
                    return resp;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            public CategoryEntity getBody() {
                return this.body;
            }
        }

        try {
            FindOneTask findOneTask = new FindOneTask();
            findOneTask.execute(code, showProducts);
            while (findOneTask.getBody() == null) {
            }

            CategoryEntity categoryEntity = findOneTask.getBody();

            return categoryEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public List<CategoryEntity> findAll(Boolean showProducts) {
        class FindAllTask extends AsyncTask<Object, Void, List<CategoryEntity>> {

            private final String subUrl = "categories";
            private List<CategoryEntity> body = null;

            @Override
            protected List<CategoryEntity> doInBackground(Object... objects) {
                Boolean showProducts = (Boolean) objects[0];
                RestTemplate restTemplate = new RestTemplate();

                try {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity<String> entity = new HttpEntity<String>(headers);
                    ResponseEntity<CategoryEntity[]> resp =
                            restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + "?showProducts={showProducts}",
                            HttpMethod.GET, entity, CategoryEntity[].class, showProducts);
                    CategoryEntity[] categoryEntities = resp.getBody();
                    List<CategoryEntity> res = new ArrayList<>(Arrays.asList(categoryEntities));

                    this.body = res;
                    return res;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            public List<CategoryEntity> getBody() {
                return this.body;
            }

        }

        try {
            FindAllTask findAllTask = new FindAllTask();
            findAllTask.execute(showProducts);
            while (findAllTask.getBody() == null) {
            }

            List<CategoryEntity> categoryEntities = findAllTask.getBody();
            return categoryEntities;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
