package io.healthathome.repository;

import io.healthathome.models.Pay;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PayRepository extends MongoRepository<Pay, String> {

    List<Pay> getPayListByUser(String user);

}
