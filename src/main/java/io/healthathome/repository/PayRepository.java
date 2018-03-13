package io.healthathome.repository;

import io.healthathome.models.Pay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PayRepository extends MongoRepository<Pay, String> {

}
