package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.page_entity;

import org.springframework.http.HttpStatus;

public class BasePageEntity {
    private HttpStatus httpStatus = HttpStatus.OK;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
