package io.swagger.api;

import io.swagger.model.Product;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Controller
public class ProductApiController implements ProductApi {



    public ResponseEntity<Void> addProduct(@ApiParam(value = "Product object that needs to be added" ,required=true )  @Valid @RequestBody Product product) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id to delete",required=true ) @PathVariable("id") String id) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> getProductByCategoryId(@ApiParam(value = "Category ID of product to return",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        return new ResponseEntity<List<Product>>(HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductById(@ApiParam(value = "",required=true ) @PathVariable("id") String id) {
        // do some magic!
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateProduct(@ApiParam(value = "Product object that needs to be added" ,required=true )  @Valid @RequestBody Product product) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
