package com.example.empresasjava.repository;

import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.StudioCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudioCalendarRepository extends JpaRepository<StudioCalendar,Long> {

    StudioCalendar findOneByDateEvent (Date dateEvent);

    StudioCalendar findOneByTitle (String title);

    StudioCalendar findOneByStudioCalendarId(Long id);

    Page<StudioCalendar> findAllByDeletedAtIsNullOrderByTitle(Pageable pages);

    Page<StudioCalendar> findAllByTitleContainingIgnoreCaseOrderByTitle(String title, Pageable pages);

    List<StudioCalendar> findAllByDeletedAtIsNullOrderByTitle();
}
