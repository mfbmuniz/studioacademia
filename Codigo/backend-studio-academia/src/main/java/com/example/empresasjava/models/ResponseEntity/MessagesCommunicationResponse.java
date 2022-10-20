package com.example.empresasjava.models.ResponseEntity;


import com.example.empresasjava.models.MessagesCommunication;
import com.example.empresasjava.models.Plans;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MessagesCommunicationResponse {


    private Long adminMessageId;

    private Long userId;


    private String messageContent ;


    private String title;

    public MessagesCommunicationResponse(Long adminMessageId, Long userId, String messageContent, String title) {
        this.adminMessageId = adminMessageId;
        this.userId = userId;
        this.messageContent = messageContent;
        this.title = title;
    }

    public MessagesCommunicationResponse() {
    }

    public static MessagesCommunicationResponse fromMessagesCommunication (MessagesCommunication messagesCommunication){

        return new MessagesCommunicationResponse(
                messagesCommunication.getAdminMessageId(),
                messagesCommunication.getUserId(),
                messagesCommunication.getMessageContent(),
                messagesCommunication.getTitle()
        );
    }
}
