package com.example.empresasjava.models.RequestEntity;


import com.example.empresasjava.models.StudioCalendar;
import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class StudioCalendarRequest {




    private Long studioCalendarId;


    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private Date dateEvent;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String dateDescription ;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String title;



    private Date createdAt;

    private Date deletedAt;

    public StudioCalendarRequest(Long studioCalendarId, Date dateEvent, String dateDescription, String title) {
        this.studioCalendarId = studioCalendarId;
        this.dateEvent = dateEvent;
        this.dateDescription = dateDescription;
        this.title = title;
    }

    public StudioCalendarRequest(Date dateEvent, String dateDescription, String title) {
        this.dateEvent = dateEvent;
        this.dateDescription = dateDescription;
        this.title = title;
    }

    public static StudioCalendar toStudioCalendar (StudioCalendarRequest studioCalendarRequest) {
        return new StudioCalendar(
                studioCalendarRequest.getDateEvent(),
                studioCalendarRequest.getDateDescription(),
                studioCalendarRequest.getTitle()
        );
    }

}
