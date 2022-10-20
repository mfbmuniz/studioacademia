package com.example.empresasjava.models.ResponseEntity;


import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.StudioCalendar;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class StudioCalendarResponse {

    private Long studioCalendarId;

    private Date dateEvent;

    private String dateDescription ;

    private String title;

    public StudioCalendarResponse() {
    }

    public StudioCalendarResponse(Long studioCalendarId, Date dateEvent, String dateDescription, String title) {
        this.studioCalendarId = studioCalendarId;
        this.dateEvent = dateEvent;
        this.dateDescription = dateDescription;
        this.title = title;
    }

    public static StudioCalendarResponse fromStudioCalendar (StudioCalendar studioCalendar){

        return new StudioCalendarResponse(

                studioCalendar.getStudioCalendarId(),
                studioCalendar.getDateEvent(),
                studioCalendar.getDateDescription(),
                studioCalendar.getTitle()

        );
    }
}
