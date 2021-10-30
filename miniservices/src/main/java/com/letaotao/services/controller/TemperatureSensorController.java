package com.letaotao.services.controller;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.handler.TemperatureSensorHandler;
import com.letaotao.services.model.TemperatureSensorModel;
import com.letaotao.services.request.TemperatureSensorCreateRequest;
import com.letaotao.services.response.HttpBaseResponse;
import com.letaotao.services.response.TemperatureSensorCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TemperatureSensorController {

    @Autowired
    TemperatureSensorHandler temperatureSensorHandler;

    @RequestMapping(method = RequestMethod.POST, value = "/temperature/create")
    public ResponseEntity<HttpBaseResponse> createRecord(@RequestBody TemperatureSensorCreateRequest request) {
//        LogUtil.info(log, "get the incoming request" + request);

        LogUtil.info(log, "request create temperature : "+request);
        // curl -X POST --data '{"id": 123,"userId": "bbb", "data":["p1:a1:a2", "p2:a2:a2"] }' -H "Content-Type:application/json"  http://localhost:8080/review/create
        // update database result field
        /**
         * figure out the row in the database, and update the bit in the result, update the status if needed.
         */
        temperatureSensorHandler.createTemperatureRecord(request.getTarget(), request.getAmbient(), request.getTimeCollected(), "");

        temperatureSensorHandler.notificationCheck(request.getTarget(), request.getAmbient());

        TemperatureSensorCreateResponse response = new TemperatureSensorCreateResponse();
        response.setId(request.getId());
        response.setResult("SUCCESS");
        response.setMsg("SUCCESS");

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
