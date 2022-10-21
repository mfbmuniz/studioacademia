package com.example.empresasjava.repository;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends JpaRepository<Plans,Long> {

    Plans findByName (String name);

    Plans findOneByName(String name);
    Plans findOneByPlanCode(String planCode);

    Plans findOneByPlanId(Long id);

    Page<Plans> findAllByDeletedAtIsNullOrderByName(Pageable pages);

    Page<Plans> findAllByNameContainingIgnoreCaseOrderByName(String name, Pageable pages);

    List<Plans> findAllByPlanIdIn(List<Long> planIds);

    List<Plans> findAllByDeletedAtIsNullOrderByName();
}
