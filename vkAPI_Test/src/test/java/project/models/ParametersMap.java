package project.models;

import java.util.HashMap;

public class ParametersMap extends HashMap<String, Object> {
    HashMap<String, Object> params = new HashMap<>();
    private Object ParametersMap;

    public ParametersMap() {
    }

    private ParametersMap(HashMap<String, Object> params) {
        this.params = params;
    }

    public ParametersMap put(String key, Object value) {
        params.put(key, value);
        return new ParametersMap(params);
    }
}
