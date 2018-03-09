
package io.healthathome.service;

import com.google.common.hash.Hashing;
import io.healthathome.dto.ChangePassword;
import io.healthathome.dto.Login;
import io.healthathome.dto.LoginState;
import io.healthathome.models.User;
import io.healthathome.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;

    public LoginState login(Login login) {
        LoginState loginState = new LoginState();
        User user = repository.findByUser(login.getUser());
        if (user == null)
            return loginState;

        loginState.setChangePassword(user.isChangePassword());

        String hashPassword = stringToHash(login.getPassword());
        if (user.getPassword().equals(hashPassword)) {
            loginState.setToken("SUepererweradsfawfea");
        }

        return loginState;
    }

    public io.healthathome.dto.User updatePassword(ChangePassword dto) {
        User user = repository.findByUser(dto.getUser());
        String newPassword = stringToHash(dto.getNewPassword());
        user.setPassword(newPassword);
        user.setChangePassword(false);
        return map(repository.save(user));
    }

    public io.healthathome.dto.User insert(io.healthathome.dto.User user) {
        User user_ = map(user);
        user_.setChangePassword(true);
        String password = UUID.randomUUID().toString();
        user_.setPassword(stringToHash(password));
        notifyEmail(user.getUser(), password);
        return map(repository.insert(user_));
    }

    private void notifyEmail(String user, String password) {
        //do some magic
        LOGGER.info("NOTIFICANDO POR CORREO");
    }

    public io.healthathome.dto.User update(io.healthathome.dto.User user) {
        return map(repository.save(map(user)));
    }

    public void delete(String user) {
        repository.delete(repository.findByUser(user));
    }

    private User map(io.healthathome.dto.User user) {
        return mapper.map(user, User.class);
    }

    private io.healthathome.dto.User map(User user) {
        return mapper.map(user, io.healthathome.dto.User.class);
    }

    public io.healthathome.dto.User getUserByUser(String user) {
        return map(repository.findByUser(user));
    }

    public String stringToHash(String text) {
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }

}
