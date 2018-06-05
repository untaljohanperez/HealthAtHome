package io.healthathome.service;

import io.healthathome.dto.Order;
import io.healthathome.models.Pay;
import io.healthathome.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {

    @Autowired
    private PayRepository payRepository;
    @Autowired
    private CartService cartService;

    public List<String> getAllOrdersByUser(String user) {
        return payRepository.getPayListByUser(user).stream().map(x -> x.get_id()).collect(Collectors.toList());
    }

    public Order getOrderDetail(String orderId) {
        Pay pay = payRepository.findById(orderId).get();
        Order order = new Order();
        order.setItems(cartService.getCartById(pay.getCartId()).getItems());
        return order;
    }
}
