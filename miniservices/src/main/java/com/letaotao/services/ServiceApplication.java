package com.letaotao.services;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.util.AddressVerifier;
import com.letaotao.util.BlockConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@org.springframework.boot.autoconfigure.SpringBootApplication
@ServletComponentScan("com.letaotao.common.filter")
public class ServiceApplication {
    private static final Logger LOGGER = LogManager.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Yanzhe Temperature TEST HHH ");
        LogUtil.info(log, "server starting... ");
//        if(args!=null && args.length>0){
//            LogUtil.info(log, "Reading file from "+args[0]);
//            AddressVerifier.loadAddresses(args[0]);
//        }
//        if (args!=null && args.length>1){
//            if(args[1].equalsIgnoreCase("up") ||
//                    args[1].equalsIgnoreCase("down")){
//                BlockConfig.configs.put("DIRECTION", args[1].toUpperCase());
//            }
//        }
        SpringApplication.run(ServiceApplication.class, args);
    }
}
