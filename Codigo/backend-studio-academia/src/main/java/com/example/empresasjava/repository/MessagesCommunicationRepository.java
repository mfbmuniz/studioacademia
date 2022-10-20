package com.example.empresasjava.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesCommunication extends JpaRepository<MessagesCommunication,Long> {


    MessagesCommunication findOneByTitle(String title);

    MessagesCommunication findOneById(Long Id);

    Page<MessagesCommunication> findAllByDeletedAtIsNullOrderByTitle(Pageable pages);

    Page<MessagesCommunication> findAllByTitleContainingIgnoreCaseOrderByName(String title, Pageable pages);

}
