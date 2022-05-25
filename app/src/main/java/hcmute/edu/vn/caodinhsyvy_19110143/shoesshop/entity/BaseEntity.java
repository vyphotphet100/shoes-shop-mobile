package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import net.minidev.json.JSONObject;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class BaseEntity<D> implements Cloneable{
    private String createdBy;

    @Temporal(TIMESTAMP)
    private Date createdDate;

    private String modifiedBy;

    @Temporal(TIMESTAMP)
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

    private String httpStatus = "OK";

    private HashMap<String, Object> listRequest = new HashMap<>();

    private HashMap<String, Object> listResult = new HashMap<>();

    private Integer id;

    private String code;

    public BaseEntity clone() throws CloneNotSupportedException {
        return (BaseEntity)super.clone();
    }

}
