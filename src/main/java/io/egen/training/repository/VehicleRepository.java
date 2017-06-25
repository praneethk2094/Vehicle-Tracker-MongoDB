package io.egen.training.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import io.egen.training.entity.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>  {

}
