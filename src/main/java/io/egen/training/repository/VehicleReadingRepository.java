package io.egen.training.repository;

import io.egen.training.entity.VehicleReading;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleReadingRepository extends MongoRepository<VehicleReading, String> {
    List<VehicleReading> findAllByVin(String vin);
}
