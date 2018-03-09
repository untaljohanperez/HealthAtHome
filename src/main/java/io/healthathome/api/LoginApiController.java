package io.healthathome.api;

import io.healthathome.model.ChangePassword;
import io.healthathome.model.Login;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Controller
public class LoginApiController implements LoginApi {



    public ResponseEntity<Void> changePassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangePassword body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Login body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
