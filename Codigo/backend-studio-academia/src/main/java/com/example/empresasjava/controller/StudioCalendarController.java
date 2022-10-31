package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.StudioCalendarRequest;
import com.example.empresasjava.models.ResponseEntity.StudioCalendarResponse;
import com.example.empresasjava.models.StudioCalendar;
import com.example.empresasjava.service.StudioCalendarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/calendar")
public class StudioCalendarController {



    @Autowired
    private StudioCalendarService studioCalendarService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar novo evento na data passada no body")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<StudioCalendarResponse> createCalendarEvent(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody StudioCalendarRequest request) throws NotFoundException {

        StudioCalendarResponse studioCalendarResponse = this.studioCalendarService.createCalendarDate(request);
        return ResponseEntity.ok().body(
                studioCalendarResponse
        );
    }

    @PostMapping(path = "/edit")
    @ApiOperation(value = "Editar evento em data existente")
    public ResponseEntity<StudioCalendarResponse> editCalendarEvent(
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody StudioCalendarRequest request) throws NotFoundException {

        return ResponseEntity.ok().body(
                this.studioCalendarService.editCalendarDate(request)
        );
    }

    @DeleteMapping(path = "/delete/calendarid/{calendarid}")
    @ApiOperation(value = "Desativa um evento em data existente")
    public ResponseEntity<StudioCalendarResponse> deleteUser(@PathVariable(value="calendarid") Long calendarid)
            throws NotFoundException {

        return ResponseEntity.ok().body(
                this.studioCalendarService.deleteCalendarDate(calendarid)
        );
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<StudioCalendar> listCalendarDateByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);
        return this.studioCalendarService.listCalendarDateByPage(pages);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "page/{page}/size/{size}/searchTitle/{searchTitle}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<StudioCalendar> listSpecificCalendarDateByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
                    int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
                    int size,
            @PathVariable(value="searchTitle")
                    String searchTitle
    ) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.studioCalendarService.listSpecificCalendarDateByPage(pages, searchTitle);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getcalendardatebyid/studioCalendarId/{studioCalendarId}")
    @ResponseBody
    @ApiOperation(value = "Lista retorna uma evento pela id da data")
    public ResponseEntity<StudioCalendarResponse> getCalendarDateByStudioCalendarId(
            @PathVariable(value="studioCalendarId")
            Long studioCalendarId)
            throws NotFoundException{

        return ResponseEntity.ok().body(
                this.studioCalendarService.getCalendarDateByStudioCalendarId(studioCalendarId)
        );

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getcalendardatebytitle/title/{title}")
    @ResponseBody
    @ApiOperation(value = "Lista retorna uma evento pela id da data")
    public ResponseEntity<StudioCalendarResponse> getCalendarDateByTitle(
            @PathVariable(value="title")
            String title)
            throws NotFoundException{

        return ResponseEntity.ok().body(
                this.studioCalendarService.getCalendarDateByTitle(title)
        );

    }


    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getcalendardatebydate/dateEvent/{dateEvent}")
    @ResponseBody
    @ApiOperation(value = "lista de eventos daquela data ")
    public ResponseEntity<List<StudioCalendar>> getCalendarDateByDateEvent(
            @PathVariable(value="title")
            Date dateEvent)
            throws NotFoundException{

        return ResponseEntity.ok().body(
                this.studioCalendarService.getCalendarDateByDateEvent(dateEvent)
        );

    }

}
