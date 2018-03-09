package io.healthathome.repository;

import io.healthathome.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, String> {

    User findByUser(String user);
}
