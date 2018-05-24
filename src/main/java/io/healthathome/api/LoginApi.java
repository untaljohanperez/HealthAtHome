/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.healthathome.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.healthathome.dto.Login;
import io.healthathome.dto.ChangePassword;

import io.healthathome.dto.Message;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Api(value = "login", description = "the login API")
public interface LoginApi {

    @ApiOperation(value = "", notes = "", response = Void.class, tags={ "login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Great", response = Void.class),
        @ApiResponse(code = 404, message = "Don't be a motherfucker", response = Void.class),
        @ApiResponse(code = 405, message = "Reset password", response = Void.class) })
    
    @RequestMapping(value = "/login/change-password",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Message> changePassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangePassword body);


    @ApiOperation(value = "log-in", notes = "", response = Void.class, tags={ "login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Great", response = Void.class),
        @ApiResponse(code = 401, message = "Don't be a motherfucker - Incorrect user or password", response = Void.class),
        @ApiResponse(code = 406, message = "Change password", response = Void.class) })
    
    @RequestMapping(value = "/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Login body) throws JsonProcessingException;

}
