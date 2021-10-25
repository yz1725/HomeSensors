package com.letaotao.services.repository;

import com.letaotao.services.model.BlockModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;


@Repository
public interface BlockLogsRepository extends JpaRepository<BlockModel, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
            value = "SELECT blockModel" +
                    " FROM BlockModel blockModel" +
                    " WHERE blockModel.id=:id"
    )
    BlockModel selectByIdForUpdate(@Param("id") String id);

    BlockModel queryByStart(String start);

    BlockModel queryById(int id);

    BlockModel findTopByOrderByIdDesc();
//    @Query(
//          value = "SELECT blockModel " + "  FROM BlockModel " +
//                  " ORDER " + " BY createdAt DESC LIMIT 1"
//    )
//    BlockModel getLatestBlock();
}
