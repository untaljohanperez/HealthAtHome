package io.healthathome.service;

import io.healthathome.dto.Coordinate;
import io.healthathome.dto.Order;
import io.healthathome.dto.TrackingInfo;
import io.healthathome.models.Pay;
import io.healthathome.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
        order.setTrackingInfo(getTrackingInfo());
        return order;
    }

    private TrackingInfo getTrackingInfo() {
        TrackingInfo trackingInfo = new TrackingInfo();
        Coordinate coordinate = new Coordinate(1234, 1234);
        trackingInfo.setOrigin(coordinate);
        trackingInfo.setDestination(coordinate);
        trackingInfo.setPath(Arrays.asList(coordinate));
        return trackingInfo;
    }
}
