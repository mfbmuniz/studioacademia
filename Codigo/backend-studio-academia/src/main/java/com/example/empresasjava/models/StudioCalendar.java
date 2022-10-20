package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "StudioCalendar")

public class StudioCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studiocalendar_id")
    private Long studioCalendarId;


    @Column(name = "dateEvent")
    private Date dateEvent;

    @Column(name = "dateDescription")
    private String dateDescription ;

    @Column(name = "title")
    private String title;


    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public StudioCalendar() {

    }

    public StudioCalendar(Long studioCalendarId, Date dateEvent, String dateDescription, String title) {
        this.studioCalendarId = studioCalendarId;
        this.dateEvent = dateEvent;
        this.dateDescription = dateDescription;
        this.title = title;
    }

    public StudioCalendar(Date dateEvent, String dateDescription, String title) {
        this.dateEvent = dateEvent;
        this.dateDescription = dateDescription;
        this.title = title;
    }
}
