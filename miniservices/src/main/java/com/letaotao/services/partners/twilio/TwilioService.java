package com.letaotao.services.partners.twilio;

import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;


public class TwilioService {

//    twilio.account.sid=ACa66026076f140e134043b8ffa3ef9362
//    twilio.account.token=6702ef4d174a1813c4ad3afda6eed250
    public static final String ACCOUNT_SID = "ACa66026076f140e134043b8ffa3ef9362";
    public static final String AUTH_TOKEN = "6702ef4d174a1813c4ad3afda6eed250";

    String voiceWebhook = "https://demo.twilio.com/welcome/voice/";
    String smsWebhook = "https://demo.twilio.com/welcome/sms/reply/";

    static final String from = "+12058512636";
    static final String to   = "+16509544335";

    public static void smsAlert(String target, String ambient){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String msg = "Temperature alert, Target:"+target+ " ambient:"+ambient;
        Message message = Message.creator(new PhoneNumber(to),
                new PhoneNumber(from),
                msg).create();

        System.out.println(message.getSid());
    }

    public static void callOutAlert(String target, String ambient) {
        try{
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String msg = "Alert, Alert, the target Temperature is "+target+ " and the ambient temperature is "+ambient;

            String helloTwiml = new VoiceResponse.Builder()
                    .say(new Say.Builder(msg)
                            .voice(Say.Voice.POLLY_AMY).build())
                    .build().toXml();

            Call call = Call.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(from),
                    new Twiml(helloTwiml))
                    .create();

            System.out.println(call.getSid());
        }catch (Exception ex){

        }
    }
}
