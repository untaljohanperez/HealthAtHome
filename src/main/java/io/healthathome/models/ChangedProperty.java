package io.healthathome.models;

public class ChangedProperty {

    private String _id;
    private String propertyName;
    private String oldValue;
    private String newValue;

    public ChangedProperty(String propertyName, String oldValue, String newValue) {
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getPropertyName() {
        return propertyName;
    }
    public String getOldValue() {
        return oldValue;
    }
    public String getNewValue() {
        return newValue;
    }
}
