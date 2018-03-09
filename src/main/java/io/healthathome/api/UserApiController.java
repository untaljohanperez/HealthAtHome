package io.healthathome.api;

import io.healthathome.dto.User;
import io.healthathome.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Controller
public class UserApiController implements UserApi {

    @Autowired
    UserService service;


    public ResponseEntity<Void> addUser(@ApiParam(value = "User object that needs to be added" ,required=true )  @Valid @RequestBody User body) {
        service.insert(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "User to delete",required=true ) @PathVariable("id") String user) {
        service.delete(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(@ApiParam(value = "User id to delete",required=true ) @PathVariable("id") String user) {
        return new ResponseEntity<User>(service.getUserByUser(user), HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "User object that needs to be added" ,required=true )  @Valid @RequestBody User body) {
        service.update(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
