package io.healthathome.repository;

import io.healthathome.models.ProductChangeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductChangeLogRepository extends MongoRepository<ProductChangeLog, String> {

}
