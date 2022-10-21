package com.example.empresasjava.repository;


import com.example.empresasjava.models.PhysicalAssessment;
import com.example.empresasjava.models.Plans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicalAssessmentRepository extends JpaRepository<PhysicalAssessment,Long> {

    PhysicalAssessment findOneByPhysicalAssessmentId (Long physicalAssessmentId);

    Page<PhysicalAssessment> findAllByDeletedAtIsNullOrderByPhysicalAssessmentId(Pageable pages);

    Page<PhysicalAssessment> findAllByUserIdAndDeletedAtIsNullOrderByPhysicalAssessmentId(Long idUser, Pageable pages);

    Page<PhysicalAssessment> findAllByProfessionalIdAndDeletedAtIsNullOrderByPhysicalAssessmentId(Long professionalId, Pageable pages);

    List<PhysicalAssessment> findAllByUserIdAndDeletedAtIsNullOrderByPhysicalAssessmentId (Long idUser);

    List<PhysicalAssessment> findAllByProfessionalIdAndDeletedAtIsNullOrderByPhysicalAssessmentId (Long professionalId);
}
