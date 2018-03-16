/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.healthathome.api;

import io.healthathome.dto.Message;
import io.healthathome.dto.OperationResult;
import io.healthathome.dto.Product;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Api(value = "product", description = "the product API")
public interface ProductApi {

    @ApiOperation(value = "Find product by Category ID", notes = "Returns a single product", response = Product.class, responseContainer = "List", authorizations = {
            @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Product.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class) })

    @RequestMapping(value = "/product",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Product>> getAllProducts();

    @ApiOperation(value = "Add a new product", notes = "", response = Void.class, authorizations = {
        @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Product created succesfully", response = Void.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    
    @RequestMapping(value = "/product",
        produces = { "application/json" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addProduct(@ApiParam(value = "Product object that needs to be added" ,required=true )  @Valid @RequestBody Product product);


    @ApiOperation(value = "Deletes a product", notes = "", response = Void.class, authorizations = {
        @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Product deleted", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    
    @RequestMapping(value = "/product/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id to delete",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "Find product by Category ID", notes = "Returns a single product", response = Product.class, responseContainer = "List", authorizations = {
        @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Product.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 404, message = "Product not found", response = Void.class) })
    
    @RequestMapping(value = "/product/category/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Product>> getProductByCategoryId(@ApiParam(value = "Category ID of product to return",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "Find product by ID", notes = "Returns a single product", response = Product.class, authorizations = {
        @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Product.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 404, message = "Product not found", response = Void.class) })
    
    @RequestMapping(value = "/product/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Product> getProductById(@ApiParam(value = "",required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "Find product by name", notes = "Returns a single product", response = Product.class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Product.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 404, message = "Product not found", response = Void.class) })

    @RequestMapping(value = "/product/name/{name}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Product> getProductByName(@ApiParam(value = "",required=true ) @PathVariable("name") String name);

    @ApiOperation(value = "Update an existing product", notes = "", response = Void.class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product updated", response = Void.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 404, message = "User not found", response = Void.class),
            @ApiResponse(code = 405, message = "Validation exception", response = Void.class) })

    @RequestMapping(value = "/product/{user}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateProduct(@ApiParam(value = "Product object that needs to be added" ,required=true )  @Valid @RequestBody Product product, @ApiParam(value = "User",required=true ) @PathVariable("user") String user);

    @ApiOperation(value = "Get product iamge", notes = "Returns a single image", response = byte[].class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Product.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 404, message = "Product not found", response = Void.class) })

    @RequestMapping(value = "/product/{idProduct}/image/{idImage}",
            produces = { "image/jpeg" },
            consumes = { "*/*" },
            method = RequestMethod.GET)
    ResponseEntity<byte[]> getProductImageByIdProductAndIdImage(@ApiParam(value = "",required=true ) @PathVariable("idProduct") String idProduct, @PathVariable("idImage") String idImage) throws IOException;


    @ApiOperation(value = "Find product by name", notes = "Returns a single product", response = OperationResult.class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Product.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 404, message = "Product not found", response = Void.class) })

    @RequestMapping(value = "/product/{idProduct}/image",
            produces = { "application/json" },
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    ResponseEntity<Message> uploadImage(@ApiParam(required=true ) @PathVariable("idProduct") String idProduct, @RequestParam("image") MultipartFile file) throws IOException;
}
