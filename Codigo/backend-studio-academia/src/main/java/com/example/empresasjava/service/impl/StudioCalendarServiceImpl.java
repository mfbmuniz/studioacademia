package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.RequestEntity.StudioCalendarRequest;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.models.ResponseEntity.StudioCalendarResponse;
import com.example.empresasjava.models.StudioCalendar;
import com.example.empresasjava.repository.PlansRepository;
import com.example.empresasjava.repository.StudioCalendarRepository;
import com.example.empresasjava.service.PlansService;
import com.example.empresasjava.service.StudioCalendarService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class StudioCalendarServiceImpl implements StudioCalendarService {

    @Autowired
    StudioCalendarRepository studioCalendarRepository;

    @Override
    public StudioCalendarResponse createCalendarDate (StudioCalendarRequest studioCalendarRequest) throws NonUniqueResultException, NotFoundException {

        Optional<StudioCalendar> studioCalendar = Optional.ofNullable(this.studioCalendarRepository.findOneByTitle(studioCalendarRequest.getTitle()));

        if(!studioCalendar.isPresent()){
            return StudioCalendarResponse.fromStudioCalendar(this.studioCalendarRepository.save(StudioCalendarRequest.toStudioCalendar(studioCalendarRequest)));
        }else{
            throw new NonUniqueResultException("Evento ja cadastrado para esta data");
        }
    }

    @Override
    public StudioCalendarResponse editCalendarDate(StudioCalendarRequest studioCalendarRequest)throws  NotFoundException{

        StudioCalendar studioCalendar = Optional.of(this.studioCalendarRepository.findOneByStudioCalendarId(studioCalendarRequest.getStudioCalendarId())).
                orElseThrow(()-> new NonUniqueResultException("Evento Inexistente"));

        studioCalendar.setDateEvent(studioCalendarRequest.getDateEvent());
        studioCalendar.setDateDescription(studioCalendarRequest.getDateDescription());
        studioCalendar.setTitle(studioCalendarRequest.getTitle());

        return StudioCalendarResponse.fromStudioCalendar(this.studioCalendarRepository.save(studioCalendar));

    }

    @Override
    public StudioCalendarResponse deleteCalendarDate(Long id)throws  NotFoundException{
        StudioCalendar studioCalendar = Optional.of(this.studioCalendarRepository.findOneByStudioCalendarId(id)).orElseThrow(()->
                new NonUniqueResultException("Exercicio inexistente"));

        studioCalendar.setDeletedAt(new Date());
        return StudioCalendarResponse.fromStudioCalendar(this.studioCalendarRepository.save(studioCalendar));

    }

    @Override
    public  Page<StudioCalendar> listCalendarDateByPage(Pageable pages)throws  NotFoundException{

        return this.studioCalendarRepository.findAllByDeletedAtIsNullOrderByTitle(pages);
    }

    @Override
    public  Page<StudioCalendar> listSpecificCalendarDateByPage(Pageable pages, String searchTitle)throws  NotFoundException{
        return this.studioCalendarRepository.findAllByTitleContainingIgnoreCaseOrderByTitle(searchTitle,pages);
    }

    @Override
    public StudioCalendarResponse getCalendarDateByStudioCalendarId(Long calendarId)throws  NotFoundException{
        return StudioCalendarResponse.fromStudioCalendar(this.studioCalendarRepository.findOneByStudioCalendarId(calendarId));
    }
    @Override
    public StudioCalendarResponse getCalendarDateByTitle(String title )throws  NotFoundException{
        return StudioCalendarResponse.fromStudioCalendar(this.studioCalendarRepository.findOneByTitle(title));
    }
    @Override
    public List<StudioCalendar> getCalendarDateByDateEvent(Date dateEvent)throws  NotFoundException{
        return this.studioCalendarRepository.findAllByDateEvent(dateEvent);
    }


}
