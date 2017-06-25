package io.egen.training.repository;

import io.egen.training.entity.VehicleReading;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReadingRepository extends MongoRepository<VehicleReading, String> {
}
