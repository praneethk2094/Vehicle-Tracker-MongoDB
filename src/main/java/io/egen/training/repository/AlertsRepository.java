package io.egen.training.repository;

import io.egen.training.entity.Alerts;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by diksh on 6/26/2017.
 */
public interface AlertsRepository extends MongoRepository<Alerts, String> {
}
