
package io.healthathome.service;

import com.google.common.hash.Hashing;
import io.healthathome.model.ChangePassword;
import io.healthathome.model.Login;
import io.healthathome.model.LoginState;
import io.healthathome.model.User;
import io.healthathome.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    public LoginState login(Login login) {
        LoginState loginState = new LoginState();
        User user = repository.findByUser(login.getUser());
        if (user == null)
            return loginState;

        loginState.setChangePassword(user.isChangePassword());

        String password = login.getPassword();
        String hashPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        if (user.getPassword().equals(hashPassword)) {
            loginState.setToken("SUepererweradsfawfea");
        }

        return loginState;
    }

    public User updatePassword(ChangePassword dto) {
        User user = repository.findByUser(dto.getUser());
        String newPassword = Hashing.sha256()
                .hashString(dto.getNewPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(newPassword);
        return repository.save(user);
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public User update(User user) {
        int a = 0;
        return repository.save(user);
    }

}
