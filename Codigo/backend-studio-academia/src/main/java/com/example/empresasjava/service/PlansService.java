package com.example.empresasjava.service;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.models.dto.ExerciseDto;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface PlansService {

    PlansResponse create (PlansRequest plansRequest) throws NonUniqueResultException, NotFoundException;

    PlansResponse editPlan(PlansRequest plansRequest)throws  NotFoundException;

    PlansResponse deletePlan(Long id)throws  NotFoundException;

    Page<Plans> listPlansByPage(Pageable pages)throws  NotFoundException;

    Page<Plans> listSpecificPlanByPage(Pageable pages, String searchName)throws  NotFoundException;

    PlansResponse getPlanByPlanId(Long planId)throws  NotFoundException;

    PlansResponse getPlanByName(String name )throws  NotFoundException;

    PlansResponse getPlanByPlanCode(String planCode)throws  NotFoundException;

    List<Plans> getPlansForDropDown()throws  NotFoundException;

}
