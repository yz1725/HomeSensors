package com.letaotao.services.repository;

import com.letaotao.services.model.TemperatureSensorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;


@Repository
public interface TemperatureSensorsRepository extends JpaRepository<TemperatureSensorModel, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
            value = "SELECT temperatureSensorModel" +
                    " FROM TemperatureSensorModel temperatureSensorModel" +
                    " WHERE temperatureSensorModel.id=:id"
    )
    TemperatureSensorModel selectByIdForUpdate(@Param("id") String id);

    TemperatureSensorModel queryById(int id);
}
