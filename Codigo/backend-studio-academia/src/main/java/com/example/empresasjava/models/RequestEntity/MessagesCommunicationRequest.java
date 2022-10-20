package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.MessagesCommunication;
import com.example.empresasjava.models.Plans;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MessagesCommunicationRequest {



    private Long adminMessageId;

    private Long userId;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String messageContent ;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String title;


    public MessagesCommunicationRequest() {
    }

    public MessagesCommunicationRequest(String messageContent, String title) {
        this.messageContent = messageContent;
        this.title = title;
    }

    public MessagesCommunicationRequest(Long adminMessageId, Long userId, String messageContent, String title) {
        this.adminMessageId = adminMessageId;
        this.userId = userId;
        this.messageContent = messageContent;
        this.title = title;
    }

    public MessagesCommunicationRequest(Long userId, String messageContent, String title) {
        this.userId = userId;
        this.messageContent = messageContent;
        this.title = title;
    }

    public static MessagesCommunication toMessageCommunication(MessagesCommunicationRequest request) {
        return new MessagesCommunication(

                request.getUserId(),
                request.getMessageContent(),
                request.getTitle()
        );
    }

}
