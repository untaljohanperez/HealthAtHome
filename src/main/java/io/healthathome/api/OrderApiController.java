package io.healthathome.api;

import io.healthathome.dto.Order;
import io.healthathome.service.OrderService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderApiController implements OrderApi {

    @Autowired
    private OrderService orderService;

    public ResponseEntity<List<String>> getOrdersByUser(@ApiParam(value = "User ID", required = true) @PathVariable("user") String user) {
        return new ResponseEntity<List<String>>(orderService.getAllOrdersByUser(user), HttpStatus.OK);
    }

    public ResponseEntity<Order> getOrder(@ApiParam(value = "Order ID", required = true) @PathVariable("idOrder") String idOrder) {
        return new ResponseEntity<Order>(orderService.getOrderDetail(idOrder), HttpStatus.OK);
    }
}
