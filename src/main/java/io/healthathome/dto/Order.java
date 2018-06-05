package io.healthathome.dto;

import java.util.List;

public class Order {

    private List<Item> items;
    private TrackingInfo trackingInfo;

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public TrackingInfo getTrackingInfo() {
        return trackingInfo;
    }
    public void setTrackingInfo(TrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
    }
}
