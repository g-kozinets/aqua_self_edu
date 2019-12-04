package project.api.data;

import project.enums.UrlParam;

import java.util.HashMap;
import java.util.Set;

public class ParametersMap {
    HashMap<String, Object> params = new HashMap<>();

    public ParametersMap() {
    }

    private ParametersMap(HashMap<String, Object> params) {
        this.params = params;
    }

    public ParametersMap put(UrlParam key, Object value) {
        params.put(key.getParam(), value);
        return new ParametersMap(params);
    }

    public ParametersMap newPut(UrlParam key, Object value) {
        params.clear();
        return put(key, value);
    }

    public HashMap<String, Object> getMap() {
        return params;
    }

    public Set<String> keySet() {
        return params.keySet();
    }

    public Object get(Object key) {
        return params.get(key);
    }
}