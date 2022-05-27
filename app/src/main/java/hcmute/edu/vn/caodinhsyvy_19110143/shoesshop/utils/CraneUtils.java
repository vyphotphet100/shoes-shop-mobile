package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.utils;

import java.util.HashMap;

public class CraneUtils {
    public static HashMap<String, String> extractValueFromString(String inpStr) {
        HashMap<String, String> hashMap = new HashMap<>();
        String[] vars = inpStr.split(";");
        for (String var: vars) {
            var = var.trim();
            if (var.contains("=")) {
                String[] kv = var.split("=");
                String k = kv[0];
                String v = kv[1];
                hashMap.put(k, v);
            }
        }

        return hashMap;
    }
}
