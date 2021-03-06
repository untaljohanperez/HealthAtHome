package io.healthathome.repository;

import io.healthathome.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, String> {

    User findFirstByUser(String user);

    boolean existsUserByUser(String user);

}
