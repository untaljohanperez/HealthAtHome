package io.healthathome.api;

import io.healthathome.model.Cart;
import io.healthathome.model.Item;
import io.healthathome.model.Pay;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Controller
public class CartApiController implements CartApi {



    public ResponseEntity<Void> cartAddProductIdUserPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Item body,
        @ApiParam(value = "User ID",required=true ) @PathVariable("idUser") Integer idUser) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> cartPayPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pay body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Cart>> getCart(@ApiParam(value = "User ID",required=true ) @PathVariable("idUser") Integer idUser) {
        // do some magic!
        return new ResponseEntity<List<Cart>>(HttpStatus.OK);
    }

}
