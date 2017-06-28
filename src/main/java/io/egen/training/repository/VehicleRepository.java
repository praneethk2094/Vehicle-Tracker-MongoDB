package io.egen.training.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import io.egen.training.entity.Vehicle;
import org.springframework.stereotype.Repository;
/*
 *VehicleRepository extends MongoRepository from Spring DATA whose implementation is taken care by Spring DATA
 */
@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>  {
}
