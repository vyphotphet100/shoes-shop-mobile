package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePageEntity implements Serializable {
    // declare the necessary variables used / properties
    private HttpStatus httpStatus = HttpStatus.OK;

    //get and set all value to all variables
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
