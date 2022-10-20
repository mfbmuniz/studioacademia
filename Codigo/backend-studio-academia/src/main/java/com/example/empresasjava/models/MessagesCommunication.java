package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "admin_message_service")

public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_message_id")
    private Long adminMessageId;

    //@ManyToOne
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "message_content")
    private String messageContent ;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public Messages() {
    }

    public Messages(Long adminMessageId, Long userId, String messageContent, String title, Date createdAt, Date deletedAt) {
        this.adminMessageId = adminMessageId;
        this.userId = userId;
        this.messageContent = messageContent;
        this.title = title;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public Messages(Long adminMessageId, String messageContent, String title, Date createdAt, Date deletedAt) {
        this.adminMessageId = adminMessageId;
        this.messageContent = messageContent;
        this.title = title;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public Messages(String messageContent, String title, Date createdAt, Date deletedAt) {
        this.messageContent = messageContent;
        this.title = title;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
}
