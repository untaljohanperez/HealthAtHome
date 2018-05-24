package io.healthathome.api;

import io.healthathome.dto.Message;
import io.healthathome.dto.OperationResult;
import io.healthathome.dto.Product;
import io.healthathome.service.ProductImageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductApiController implements ProductApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;

    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> getProductByCategoryId(@ApiParam(value = "Category ID of product to return", required = true) @PathVariable("id") String id) {
        return new ResponseEntity(productService.getProductByCategoryId(id), HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductById(@ApiParam(required = true) @PathVariable("id") String id) {
        return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductByName(@ApiParam(required = true) @PathVariable("name") String name) {
        return new ResponseEntity(productService.getProductByName(name), HttpStatus.OK);
    }

    public ResponseEntity<Message> addProduct(@ApiParam(value = "Product object that needs to be added", required = true) @Valid @RequestBody Product product) {
        return new ResponseEntity(Message.build(productService.insert(product)), HttpStatus.OK);
    }

    public ResponseEntity<Void> updateProduct(@ApiParam(value = "Product object that needs to be added", required = true) @Valid @RequestBody Product product
            , @ApiParam(value = "user", required = true) @PathVariable("user") String user) {
        productService.update(product, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id to delete", required = true) @PathVariable("id") String id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<byte[]> getProductImageByIdProductAndIdImage(@ApiParam(required = true) @PathVariable("idProduct") String idProduct, @ApiParam(required = true) @PathVariable("idImage") String idImage) throws IOException {
        return new ResponseEntity(productImageService.getProductImageByIdProductAndIdImage(idProduct, idImage), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> uploadImage(@ApiParam(required=true ) @PathVariable("idProduct") String idProduct, @RequestParam("image") MultipartFile file) throws IOException {
        OperationResult operationResult =  productImageService.uploadImage(idProduct, file.getInputStream(), file.getBytes());
        return new ResponseEntity(Message.build("Uploaded! - idImage = " + operationResult.getMessage()), HttpStatus.OK);
    }
}
