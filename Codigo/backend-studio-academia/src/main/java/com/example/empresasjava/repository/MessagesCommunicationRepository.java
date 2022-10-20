package com.example.empresasjava.repository;


import com.example.empresasjava.models.MessagesCommunication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesCommunicationRepository extends JpaRepository<MessagesCommunication,Long> {


    MessagesCommunication findOneByTitle(String title);

    MessagesCommunication findOneByAdminMessageId(Long Id);
    MessagesCommunication findOneByUserId(Long Id);

    Page<MessagesCommunication> findAllByDeletedAtIsNullOrderByTitle(Pageable pages);

    Page<MessagesCommunication> findAllByTitleContainingIgnoreCaseOrderByTitle(String title, Pageable pages);

}
