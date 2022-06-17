package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.crane.page;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.constant.AppConstant;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity.ProductEntity;
import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity.ProductPageEntity;

// Crane package: to communicate with server through API
public class ProductPageCrane {
    private final String subUrl = "products";

    // parameters for GET method of this page
    public Integer limit=12, offset, prize, size;
    public String keyword, categoryCode, brandCode;

    // get data for Product List Activity from server
    public List<ProductEntity> getDataProductPage() {
        StringBuilder params = new StringBuilder();
        if (limit == null)
            return new ArrayList<>();

        params.append("limit=" + limit + "&");

        if (offset != null)
            params.append("offset=" + offset + "&");

        if (keyword != null)
            params.append("keyword=" + keyword + "&");

        if (categoryCode != null)
            params.append("categoryCode=" + categoryCode + "&");


        if (brandCode != null)
            params.append("brandCode=" + brandCode + "&");

        if (prize != null)
            params.append("prize=" + prize + "&");

        if (size != null)
            params.append("size=" + size + "&");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (AppConstant.loggedInUserEntity != null)
            headers.put("Authorization",
                    Collections.singletonList("Token " + AppConstant.loggedInUserEntity.getValueInAddedData("token").toString()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<List> resp =
                restTemplate.exchange(AppConstant.BASE_REST_URL + subUrl + "?" + params,
                        HttpMethod.GET, entity, List.class);
        return resp.getBody();

    }
}
