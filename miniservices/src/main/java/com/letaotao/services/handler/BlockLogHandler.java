package com.letaotao.services.handler;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.ServiceApplication;
import com.letaotao.services.model.BlockModel;
import com.letaotao.services.model.FoundAddressModel;
import com.letaotao.services.repository.BlockLogsRepository;
import com.letaotao.services.repository.FoundAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;

@Slf4j
@Component
public class BlockLogHandler {

    @Autowired
    protected BlockLogsRepository blockLogsRepository;

    @Autowired
    protected FoundAddressRepository foundAddressRepository;

    private static final Logger LOGGER = LogManager.getLogger(BlockLogHandler.class);

    public void createBlock(String start, String end, BitSet result){

//        LogUtil.info(log, "test of create block");
//        LOGGER.info("start to createBlock");
//        blockLogsRepository.queryByStart("1");
        BlockModel blockModel = new BlockModel();
        blockModel.setStart(start);
        blockModel.setEnd(end);
        blockModel.setResult(result.toByteArray());
        blockModel.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        blockModel.setUpdatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        blockLogsRepository.save(blockModel);
    }

    public String getLatestBlock(){
        BlockModel blockModel =blockLogsRepository.findTopByOrderByIdDesc();
        return blockModel==null? null: blockModel.getStart();
    }

    public BlockModel getByStart(String start){
        BlockModel blockModel = blockLogsRepository.queryByStart(start);
        return blockModel;
    }

    public void updateResult(BlockModel blockModel){
        blockLogsRepository.save(blockModel);
    }

    public void saveFoundAddress(String privateKey, String publicAddress){
        FoundAddressModel foundAddressModel = new FoundAddressModel();
        foundAddressModel.setPrivateKey(privateKey);
        foundAddressModel.setPublicAddress(publicAddress);
        foundAddressModel.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        foundAddressModel.setUpdatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        foundAddressRepository.save(foundAddressModel);
    }
}
