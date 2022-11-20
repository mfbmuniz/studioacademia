package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.models.dto.ExerciseDto;
import com.example.empresasjava.repository.ExerciseRepository;
import com.example.empresasjava.repository.PlansRepository;
import com.example.empresasjava.service.ExerciseService;
import com.example.empresasjava.service.PlansService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PlansServiceImpl implements PlansService {

    @Autowired
    PlansRepository plansRepository;

    @Override
    public PlansResponse create(PlansRequest plansRequest) throws NonUniqueResultException, NotFoundException {

        Optional<Plans> plan = Optional.ofNullable(this.plansRepository.findOneByName(plansRequest.getName()));

        if(!plan.isPresent()){
            return PlansResponse.fromPlans(this.plansRepository.save(PlansRequest.toPlans(plansRequest)));
        }else{
            throw new NonUniqueResultException("Plano ja cadastrado!");
        }
    }

    @Override
    public PlansResponse editPlan(PlansRequest plansRequest) throws  NotFoundException{

        Plans plan = Optional.of(this.plansRepository.findOneByPlanId(plansRequest.getPlanId())).orElseThrow(()-> new NonUniqueResultException("Plano inexistente"));

        plan.setPlanCode(plansRequest.getPlanCode());
        plan.setDescription(plansRequest.getDescription());
        plan.setName(plansRequest.getName());
        plan.setPrice(Double.parseDouble(plansRequest.getPrice()));
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
