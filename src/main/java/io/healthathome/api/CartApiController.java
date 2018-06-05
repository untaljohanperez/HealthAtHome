package io.healthathome.api;

import io.healthathome.dto.*;
import io.healthathome.service.CartService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Controller
public class CartApiController implements CartApi {

    @Autowired
    private CartService cartService;

    public ResponseEntity<Void> cartAddProductIdUserPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ItemInput item,
        @ApiParam(value = "User ID", required=true ) @PathVariable("user") String user) {
        cartService.addProduct(item, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<OperationResult> cartPay(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pay pay) throws IOException {
        return new ResponseEntity<OperationResult>(cartService.pay(pay), HttpStatus.OK);
    }

    public ResponseEntity<Cart> getCart(@ApiParam(value = "User ID",required=true ) @PathVariable("user") String user) {
        return new ResponseEntity<Cart>(cartService.getCartByUser(user), HttpStatus.OK);
    }
}
