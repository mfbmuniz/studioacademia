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
    public PlansResponse editPlan(StudioCalendarRequest studioCalendarRequest) throws  NotFoundException{

        Plans plan = Optional.of(this.plansRepository.findOneByPlanId(plansRequest.getPlanId())).orElseThrow(()-> new NonUniqueResultException("Plano inexistente"));

        plan.setPlanCode(plansRequest.getPlanCode());
        plan.setDescription(plansRequest.getDescription());
        plan.setName(plansRequest.getName());
        plan.setPrice(plansRequest.getPrice());
        plan.setContractedDays(plansRequest.getContractedDays());



        return PlansResponse.fromPlans(this.plansRepository.save(plan));

    }

    @Override
    public PlansResponse deletePlan(Long id) throws  NotFoundException{
        Plans plan = Optional.of(this.plansRepository.findOneByPlanId(id)).orElseThrow(()-> new NonUniqueResultException("Exercicio inexistente"));
        plan.setDeletedAt(new Date());
        return PlansResponse.fromPlans(this.plansRepository.save(plan));

    }

    @Override
    public Page<Plans> listPlansByPage(Pageable pages) throws  NotFoundException{
        return this.plansRepository.findAllByDeletedAtIsNullOrderByName(pages);
    }

    @Override
    public Page<Plans> listSpecificPlanByPage(Pageable pages, String searchName){
        return this.plansRepository.findAllByNameContainingIgnoreCaseOrderByName(searchName,pages);
    }

    @Override
    public PlansResponse getPlanByPlanId(Long planId)throws  NotFoundException {
        return PlansResponse.fromPlans(this.plansRepository.findOneByPlanId(planId));
    }
    @Override
    public PlansResponse getPlanByPlanCode(String planCode)throws  NotFoundException{
        return PlansResponse.fromPlans(this.plansRepository.findOneByPlanCode(planCode));
    }
    @Override
    public PlansResponse getPlanByName(String name)throws  NotFoundException{
        return PlansResponse.fromPlans(this.plansRepository.findOneByName(name));
    }

    @Override
    public List<Plans> getPlansForDropDown()throws  NotFoundException{
        return (List<Plans>) this.plansRepository.findAllByDeletedAtIsNullOrderByName();
    }


}
