
package io.healthathome.service;

import com.google.common.hash.Hashing;
import io.healthathome.dto.ChangePassword;
import io.healthathome.dto.Login;
import io.healthathome.dto.LoginState;
import io.healthathome.dto.UserType;
import io.healthathome.models.User;
import io.healthathome.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import static io.healthathome.configuration.JWTConfig.EXPIRATION_TIME;
import static io.healthathome.configuration.JWTConfig.SECRET;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private EmailService emailService;

    public LoginState login(Login login) {
        LoginState loginState = new LoginState();
        User user = repository.findFirstByUser(login.getUser());
        if (user == null)
            return loginState;

        loginState.setChangePassword(user.isChangePassword());

        String hashPassword = stringToHash(login.getPassword());
        if (user.getPassword().equals(hashPassword)) {
            loginState.setToken(generateToken(user));
        }

        return loginState;
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUser())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
    }

    public io.healthathome.dto.User updatePassword(ChangePassword dto) throws Exception {
        User user = repository.findFirstByUser(dto.getUser());

        if (!user.getPassword().equalsIgnoreCase(dto.getOldPassword()))
            throw new Exception("Incorrect password");

        String newPassword = stringToHash(dto.getNewPassword());
        user.setPassword(newPassword);
        user.setChangePassword(false);
        return Mapper.map(repository.save(user));
    }

    public boolean existUserByUser(String user) {
        return repository.existsUserByUser(user);
    }

    public io.healthathome.dto.User insert(io.healthathome.dto.User user) {
        User userToSave = Mapper.map(user);
        userToSave.setChangePassword(true);
        String password = UUID.randomUUID().toString().substring(0, 8);
        userToSave.setPassword(stringToHash(password));
        notifyEmail(userToSave, password);
        return Mapper.map(repository.insert(userToSave));
    }

    private void notifyEmail(User user, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getUser());
        message.setSubject("User Created!");
        message.setText("Hi! your new password is " + password);
        emailService.sendEmail(message);
    }

    public io.healthathome.dto.User update(io.healthathome.dto.User user) {
        User userStore = repository.findFirstByUser(user.getUser());
        User userDto = Mapper.map(user);
        userStore.setGender(userDto.getGender());
        userStore.setAge(userDto.getAge());
        userStore.setName(userDto.getName());
        userStore.setType(userDto.getType());
        userStore.setLastName(userDto.getLastName());
        return Mapper.map(repository.save(userStore));
    }

    public void delete(String user) {
        repository.delete(repository.findFirstByUser(user));
    }

    public io.healthathome.dto.User getUserByUser(String user) {
        return Mapper.map(repository.findFirstByUser(user));
    }

    public String stringToHash(String text) {
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }

    public boolean areValidFieldsByUserType(io.healthathome.dto.User user) {
        if (UserType.ADMIN.equals(user.getType()))
            return areValidNameAndAge(user);
        else if (UserType.CARRIER.equals(user.getType()))
            return areValidNameAndAge(user) && !StringUtils.isEmpty(user.getWorkingHours());
        else
            return true;
    }

    private boolean areValidNameAndAge(io.healthathome.dto.User user) {
        return !StringUtils.isEmpty(user.getName()) && user.getAge() != null;
    }
}
