package com.letaotao.services.repository;

import com.letaotao.services.model.BlockModel;
import com.letaotao.services.model.FoundAddressModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;


@Repository
public interface FoundAddressRepository extends JpaRepository<FoundAddressModel, String> {

}