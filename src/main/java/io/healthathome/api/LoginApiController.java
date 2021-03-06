package io.healthathome.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.healthathome.configuration.JWTConfig;
import io.healthathome.dto.ChangePassword;
import io.healthathome.dto.Login;
import io.healthathome.dto.LoginState;
import io.healthathome.dto.Message;
import io.healthathome.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

@Controller
public class LoginApiController implements LoginApi {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;


    public ResponseEntity<Message> changePassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangePassword body) {
        try {
            userService.updatePassword(body);
            return new ResponseEntity<Message>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(Message.build(e.getMessage()), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Login body) throws JsonProcessingException {
        LoginState loginState = userService.login(body);
        if(loginState.isChangePassword())
            return new ResponseEntity<String>(objectMapper.writeValueAsString(Message.build("Change Password")), HttpStatus.NOT_ACCEPTABLE);
        else if (loginState.getToken() == null)
            return new ResponseEntity<String>(objectMapper.writeValueAsString(Message.build("Incorrect user or password")), HttpStatus.UNAUTHORIZED);
        else
            return new ResponseEntity<String>("{\"token\": \"" +loginState.getToken() + "\"}", getHeaders(loginState),HttpStatus.OK);
    }

    private MultiValueMap<String,String> getHeaders(LoginState loginState) {
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add(JWTConfig.HEADER_STRING, JWTConfig.TOKEN_PREFIX + loginState.getToken());
        return headers;
    }
}
