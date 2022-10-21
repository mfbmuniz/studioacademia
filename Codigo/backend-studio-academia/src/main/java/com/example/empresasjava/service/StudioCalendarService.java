package com.example.empresasjava.service;

import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.RequestEntity.StudioCalendarRequest;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.models.ResponseEntity.StudioCalendarResponse;
import com.example.empresasjava.models.StudioCalendar;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.NonUniqueResultException;
import java.util.Date;
import java.util.List;

public interface StudioCalendarService {

    StudioCalendarResponse createCalendarDate (StudioCalendarRequest studioCalendarRequest) throws NonUniqueResultException, NotFoundException;

    StudioCalendarResponse editCalendarDate(StudioCalendarRequest studioCalendarRequest)throws  NotFoundException;

    StudioCalendarResponse deleteCalendarDate(Long id)throws  NotFoundException;

    Page<StudioCalendar> listCalendarDateByPage(Pageable pages)throws  NotFoundException;

    Page<StudioCalendar> listSpecificCalendarDateByPage(Pageable pages, String searchTitle)throws  NotFoundException;

    StudioCalendarResponse getCalendarDateByStudioCalendarId(Long planId)throws  NotFoundException;

    StudioCalendarResponse getCalendarDateByTitle(String title )throws  NotFoundException;

    List <StudioCalendar> getCalendarDateByDateEvent(Date dateEvent)throws  NotFoundException;


}
