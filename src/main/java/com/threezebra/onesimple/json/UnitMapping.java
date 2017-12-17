
package com.threezebra.onesimple.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.threezebra.domain.Unit;

public class UnitMapping {

    private List<Unit> units = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
