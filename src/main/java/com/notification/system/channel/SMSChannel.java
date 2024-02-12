package com.notification.system.channel;

import com.notification.system.model.Notification;
import com.notification.system.utils.Constants;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;

import java.io.IOException;

@Service
@Getter
public class SMSChannel implements Channel {
    private final String name = "SMS";

    @Override
    public void sendNotification(Notification notification) throws Exception {
        // The code is written to use against the smpp.org SMSC simulator
        // In production environment it would be configured to use actual SMPP server

        SMPPSession session = new SMPPSession();
        try {
            session.connectAndBind(
                "smscsim.smpp.org",
                2775,
                new BindParameter(
                    BindType.BIND_TX,
                    "SYSTEMID",
                    "PASSWORD",
                    "",
                    TypeOfNumber.UNKNOWN,
                    NumberingPlanIndicator.UNKNOWN,
                    null
                )
            );

            for (String recipient : notification.getRecipients()) {
                try {
                    session.submitShortMessage(
                            "",
                            TypeOfNumber.UNKNOWN,
                            NumberingPlanIndicator.UNKNOWN,
                            Constants.NOTIFICATION_SYSTEM_SOURCE_ADDRESS,
                            TypeOfNumber.INTERNATIONAL,
                            NumberingPlanIndicator.UNKNOWN,
                            recipient,
                            new ESMClass(),
                            (byte) 0,
                            (byte) 1,
                            null,
                            null,
                            new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                            (byte) 0,
                            new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false),
                            (byte) 0,
                            (notification.getSubject() + "\n\n" + notification.getContent()).getBytes()
                    );
                } catch (Exception e) {
                    throw new Exception("SMS channel error: " + e.getMessage());
                }
            }

            session.unbindAndClose();
        } catch (IOException e) {
            throw new Exception("SMS channel error: " + e.getMessage());
        }
    }

}
