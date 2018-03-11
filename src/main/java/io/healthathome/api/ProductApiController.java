package io.healthathome.api;

import io.healthathome.dto.Product;
import io.healthathome.service.ProductService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductApiController implements ProductApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);

    @Autowired
    private ProductService productService;

    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> getProductByCategoryId(@ApiParam(value = "Category ID of product to return", required = true) @PathVariable("id") String id) {
        return new ResponseEntity(productService.getProductByCategoryId(id), HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductById(@ApiParam(value = "", required = true) @PathVariable("id") String id) {
        return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductByName(@ApiParam(value = "", required = true) @PathVariable("name") String name) {
        return new ResponseEntity<Product>(productService.getProductByName(name), HttpStatus.OK);
    }

    public ResponseEntity<Void> addProduct(@ApiParam(value = "Product object that needs to be added", required = true) @Valid @RequestBody Product product) {
        productService.insert(product);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateProduct(@ApiParam(value = "Product object that needs to be added", required = true) @Valid @RequestBody Product product
            , @ApiParam(value = "user", required = true) @PathVariable("user") String user) {
        productService.update(product, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id to delete", required = true) @PathVariable("id") String id) {
        productService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
