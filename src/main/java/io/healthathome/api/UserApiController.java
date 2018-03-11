package io.healthathome.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.healthathome.dto.Message;
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
    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<String> addUser(@ApiParam(value = "User object that needs to be added" ,required=true )  @Valid @RequestBody User user) throws JsonProcessingException {
        if(!service.areValidFieldsByUserType(user))
            return new ResponseEntity<String>(objectMapper.writeValueAsString(Message.build("Invalid Input")), HttpStatus.BAD_REQUEST);
        if (service.existUserByUser(user.getUser()))
            return new ResponseEntity<String>(objectMapper.writeValueAsString(Message.build("User Already Exists")), HttpStatus.BAD_REQUEST);
        service.insert(user);
        return new ResponseEntity<String>(objectMapper.writeValueAsString(Message.build("Created")), HttpStatus.OK);
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
