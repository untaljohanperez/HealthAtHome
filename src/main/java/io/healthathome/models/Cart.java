package io.healthathome.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cart")
public class Cart {

    @Id
    private String _id;
    private String cartId;
    private String user;
    private List<Item> items;
    private boolean state;

    public Cart() {
    }

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getCartId() {
        return cartId;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
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
        if (items == null)
            items = new ArrayList<>();
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}

