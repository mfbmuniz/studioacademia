package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.MessagesCommunication;
import com.example.empresasjava.models.RequestEntity.MessagesCommunicationRequest;
import com.example.empresasjava.models.ResponseEntity.MessagesCommunicationResponse;
import com.example.empresasjava.repository.MessagesCommunicationRepository;
import com.example.empresasjava.service.MessagesCommunicationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.Date;
import java.util.Optional;


@Service
public class MessagesCommunicationServiceImpl implements MessagesCommunicationService {

    @Autowired
    MessagesCommunicationRepository messagesCommunicationRepository;

    @Override
    public MessagesCommunicationResponse create (MessagesCommunicationRequest messagesCommunicationRequest) throws NonUniqueResultException, NotFoundException {

            return MessagesCommunicationResponse.fromMessagesCommunication(this.messagesCommunicationRepository.save(MessagesCommunicationRequest.toMessageCommunication(messagesCommunicationRequest)));

    }

    @Override
    public MessagesCommunicationResponse editMessage(MessagesCommunicationRequest messagesCommunicationRequest)throws  NotFoundException{

        MessagesCommunication messagesCommunication = Optional.of(this.messagesCommunicationRepository.findOneByAdminMessageId(messagesCommunicationRequest.getAdminMessageId()))
                .orElseThrow(()-> new NonUniqueResultException("Mensagem inexistente"));

        messagesCommunication.setTitle(messagesCommunicationRequest.getTitle());
        messagesCommunication.setMessageContent(messagesCommunicationRequest.getMessageContent());



        return MessagesCommunicationResponse.fromMessagesCommunication(this.messagesCommunicationRepository.save(messagesCommunication));

    }

    @Override
    public MessagesCommunicationResponse deleteMessage(Long id)throws  NotFoundException{

        MessagesCommunication messagesCommunication =  Optional.of(this.messagesCommunicationRepository.findOneByAdminMessageId(id)).
                orElseThrow(()-> new NonUniqueResultException("Mensagem inexistente"));

        messagesCommunication.setDeletedAt(new Date());

        return MessagesCommunicationResponse.fromMessagesCommunication(this.messagesCommunicationRepository.save(messagesCommunication));

    }

    @Override
    public Page<MessagesCommunication> listMessagesByPage(Pageable pages)throws  NotFoundException{
        return this.messagesCommunicationRepository.findAllByDeletedAtIsNullOrderByTitle(pages);
    }

    @Override
    public Page<MessagesCommunication> listSpecificMessageByPage(Pageable pages, String title)throws  NotFoundException{
        return this.messagesCommunicationRepository.findAllByTitleContainingIgnoreCaseOrderByTitle(title,pages);
    }

    @Override
    public MessagesCommunicationResponse getMessageByMessageId(Long adminMessageId)throws  NotFoundException{
        return MessagesCommunicationResponse.fromMessagesCommunication(this.messagesCommunicationRepository.findOneByAdminMessageId(adminMessageId));
    }
    @Override
    public MessagesCommunicationResponse getMessageByUserId(Long userId)throws  NotFoundException{

        return MessagesCommunicationResponse.fromMessagesCommunication(this.messagesCommunicationRepository.findOneByUserId(userId));
    }
    @Override
    public MessagesCommunicationResponse getMessageByTitle(String title)throws  NotFoundException{

        return MessagesCommunicationResponse.fromMessagesCommunication(this.messagesCommunicationRepository.findOneByTitle(title));
    }

}
