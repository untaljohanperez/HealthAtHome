package io.healthathome.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Cart {

    @Id
    private String _id;
    private String user;
    private List<Item> items;
    private boolean state;


    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}

