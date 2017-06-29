package io.egen.training.repository;

import io.egen.training.entity.VehicleReading;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Alerts repository extends MongoRepository from Spring DATA whose implementation is taken care by Spring DATA
 *findAllByVin finds vehicle readings by given vin
 * deleteAllByVin deletes vehicle readings by given vin
 * */
@Repository
public interface VehicleReadingRepository extends MongoRepository<VehicleReading, String> {
    List<VehicleReading> findAllByVin(String vin);

    void deleteAllByVin(String vin);
}
