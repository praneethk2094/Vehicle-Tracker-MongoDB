package io.egen.training.repository;

import io.egen.training.entity.Alerts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AlertsRepository extends MongoRepository<Alerts, String> {
}
