package com.example.empresasjava.service;

import com.example.empresasjava.models.MessagesCommunication;
import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.MessagesCommunicationRequest;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.ResponseEntity.MessagesCommunicationResponse;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface MessagesCommunicationService {

    MessagesCommunicationResponse create (MessagesCommunicationRequest messagesCommunicationRequest) throws NonUniqueResultException, NotFoundException;

    MessagesCommunicationResponse editMessage(MessagesCommunicationRequest messagesCommunicationRequest)throws  NotFoundException;

    MessagesCommunicationResponse deleteMessage(Long id)throws  NotFoundException;

    Page<MessagesCommunication> listMessagesByPage(Pageable pages)throws  NotFoundException;

    Page<MessagesCommunication> listSpecificMessageByPage(Pageable pages, String title)throws  NotFoundException;

    MessagesCommunicationResponse getMessageByMessageId(Long adminMessageId)throws  NotFoundException;

    MessagesCommunicationResponse getMessageByUserId(Long userId)throws  NotFoundException;


    MessagesCommunicationResponse getMessageByTitle(String title)throws  NotFoundException;
}
