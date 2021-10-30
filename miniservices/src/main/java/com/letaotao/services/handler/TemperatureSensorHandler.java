package com.letaotao.services.handler;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.model.TemperatureSensorModel;
import com.letaotao.services.partners.twilio.TwilioService;
import com.letaotao.services.repository.TemperatureSensorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Slf4j
@Component
public class TemperatureSensorHandler {

    @Autowired
    protected TemperatureSensorsRepository temperatureSensorsRepository;

    private static final Logger LOGGER = LogManager.getLogger(TemperatureSensorHandler.class);

    public void notificationCheck(String target, String ambient){
        LogUtil.info(log, "notificationCheck, target : "+target +" and the ambient: "+ambient);

        if (Float.parseFloat(target) - Float.parseFloat(ambient) >= 10 || Float.parseFloat(target) >= 37){
            TwilioService.smsAlert(target, ambient);
            LogUtil.info(log, "sms notificationCheck sent");
            TwilioService.callOutAlert(target, ambient);
            LogUtil.info(log, "callout notificationCheck sent");
        }else{
            LogUtil.info(log, "looks normal no need to notify");
        }

    }

    public void createTemperatureRecord(String target, String ambient, String timeCollectedAt,  String comments){

        TemperatureSensorModel temperatureSensorModel = new TemperatureSensorModel();
        temperatureSensorModel.setAmbient(ambient);
        temperatureSensorModel.setTarget(target);
        temperatureSensorModel.setTimeCollected(timeCollectedAt);
        temperatureSensorModel.setComments(comments);
        temperatureSensorModel.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        temperatureSensorModel.setUpdatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        temperatureSensorsRepository.save(temperatureSensorModel);
    }

    public void updateResult(TemperatureSensorModel temperatureSensorModel){
        temperatureSensorsRepository.save(temperatureSensorModel);
    }
}
