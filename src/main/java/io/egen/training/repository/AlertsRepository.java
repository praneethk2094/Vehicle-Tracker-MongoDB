package io.egen.training.repository;

import io.egen.training.entity.Alerts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/*
 *Alerts repository extends MongoRepository from Spring DATA whose implementation is taken care by Spring DATA
 * */
@Repository
public interface AlertsRepository extends MongoRepository<Alerts, String> {
    void deleteAllByVin(String vin);

    List<Alerts> findAllByVin(String vin);

    int countAlertsByVinAndEngineRpmAlert(String vin,String high);

    int countAlertsByVinAndFuelVolumeAlert(String vin,String medium);

    int countAlertsByVinAndTirePressureAlert(String vin,String low);

    int countAlertsByVinAndEngineRpmAlertAndTimestampBetween(String vin, String high, Date end, Date start);
}
