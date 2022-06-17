package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.minidev.json.JSONObject;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity<D> implements Cloneable, Serializable {
    // declare the necessary variables used / properties of base entity
    private String createdBy;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;

    private String addedData;

    public void setAddedData(String data) throws Exception {
        this.addedData = data;
    }

    public void setAddedData(HashMap<String, Object> data){
        this.addedData = (new JSONObject(data)).toString();
    }

    public HashMap<String, Object> retrieveAddedDataAsHashMap() {
        if (this.addedData == null || this.addedData.length() == 2)
            return null;
        String value = this.addedData.substring(1, this.addedData.length()-1);
        String[] keyValuePairs = value.split(",");
        HashMap<String,Object> hashMap = new HashMap<String, Object>();

        for(String pair : keyValuePairs)
        {
            String[] entry = pair.split(":");
            hashMap.put(entry[0].trim().substring(1, entry[0].trim().length()-1), entry[1].trim().substring(1, entry[1].trim().length()-1));
        }

        return hashMap;
    }

    //get value key of hashmap in added data
    public Object getValueInAddedData(String key) {
        HashMap<String, Object> hashMap = this.retrieveAddedDataAsHashMap();
        return hashMap.get(key);
    }

    public void setValueInAddedData(String key, Object value) {
        HashMap<String, Object> hashMap = this.retrieveAddedDataAsHashMap();
        if (hashMap == null)
            hashMap = new HashMap<String, Object>();
        hashMap.put(key, value);
        this.setAddedData(hashMap);
    }

    public void removeFieldInAddedData(String key) {
        HashMap<String, Object> hashMap = this.retrieveAddedDataAsHashMap();
        hashMap.remove(key);
        this.setAddedData(hashMap);
    }

    private String message;

    private HttpStatus httpStatus = HttpStatus.OK;

    private HashMap<String, Object> listRequest = new HashMap<>();

    private HashMap<String, Object> listResult = new HashMap<>();

    private Integer id;

    private String code;

    public BaseEntity clone() throws CloneNotSupportedException {
        return (BaseEntity)super.clone();
    }

    //get and set all value to all variables
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HashMap<String, Object> getListRequest() {
        return listRequest;
    }

    public void setListRequest(HashMap<String, Object> listRequest) {
        this.listRequest = listRequest;
    }

    public HashMap<String, Object> getListResult() {
        return listResult;
    }

    public void setListResult(HashMap<String, Object> listResult) {
        this.listResult = listResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
