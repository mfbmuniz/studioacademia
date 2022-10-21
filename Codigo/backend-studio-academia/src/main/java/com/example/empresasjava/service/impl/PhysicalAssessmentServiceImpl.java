package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.PhysicalAssessment;
import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.PhysicalAssessmentRequest;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.ResponseEntity.PhysicalAssessmentResponse;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.repository.PhysicalAssessmentRepository;
import com.example.empresasjava.repository.PlansRepository;
import com.example.empresasjava.service.PhysicalAssessmentService;
import com.example.empresasjava.service.PlansService;
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
public class PhysicalAssessmentServiceImpl implements PhysicalAssessmentService {

    @Autowired
    PhysicalAssessmentRepository physicalAssessmentRepository;

    @Override
    public PhysicalAssessmentResponse createPhysicalAssessment(PhysicalAssessmentRequest physicalAssessmentRequest)
            throws NonUniqueResultException, NotFoundException{

        Optional<PhysicalAssessment> physicalAssessment = Optional.ofNullable(this.physicalAssessmentRepository.
                findOneByPhysicalAssessmentId(physicalAssessmentRequest.getPhysicalAssessment_id()));

        if(!physicalAssessment .isPresent()){
            return PhysicalAssessmentResponse.fromPhysicalAssessment(this.physicalAssessmentRepository.save(PhysicalAssessmentRequest.toPhysicalAssessment(physicalAssessmentRequest)));
        }else{
            throw new NonUniqueResultException("Avaliação ja cadastrada ja cadastrado!");
        }
    }

    @Override
    public PhysicalAssessmentResponse editPhysicalAssessment(PhysicalAssessmentRequest physicalAssessmentRequest)throws  NotFoundException{

        PhysicalAssessment physicalAssessmentTemp = Optional.of(this.physicalAssessmentRepository.findOneByPhysicalAssessmentId(physicalAssessmentRequest.getPhysicalAssessment_id())).
                orElseThrow(()-> new NonUniqueResultException("Avaliação Inexistente"));

        PhysicalAssessment physicalAssessment = PhysicalAssessmentRequest.toPhysicalAssessment(physicalAssessmentRequest);

        physicalAssessment.setPhysicalAssessmentId(physicalAssessmentTemp.getPhysicalAssessmentId());
        physicalAssessment.setCreatedAt(physicalAssessmentTemp.getCreatedAt());
        physicalAssessment.setDeletedAt(physicalAssessmentTemp.getDeletedAt());

        return PhysicalAssessmentResponse.fromPhysicalAssessment(this.physicalAssessmentRepository.save(physicalAssessment));

    }

    @Override
    public PhysicalAssessmentResponse deletePhysicalAssessment(Long physicalAssessmentId)throws  NotFoundException{
        PhysicalAssessment physicalAssessment = Optional.of(this.physicalAssessmentRepository.findOneByPhysicalAssessmentId(physicalAssessmentId)).
                orElseThrow(()-> new NonUniqueResultException("Avaliação Inexistente"));
        physicalAssessment.setDeletedAt(new Date());
        return PhysicalAssessmentResponse.fromPhysicalAssessment(this.physicalAssessmentRepository.save(physicalAssessment));

    }

    @Override
    public Page<PhysicalAssessment> listPhysicalAssessmentsByPage(Pageable pages)throws  NotFoundException{
        return this.physicalAssessmentRepository.findAllByDeletedAtIsNullOrderByPhysicalAssessmentId(pages);
    }

    @Override
    public Page<PhysicalAssessment> listSpecificUserPhysicalAssessmentsByPage(Pageable pages, Long idUser)throws  NotFoundException{
        return this.physicalAssessmentRepository.findAllByUserIdAndDeletedAtIsNullOrderByPhysicalAssessmentId(idUser,pages);
    }

    @Override
    public Page<PhysicalAssessment> listSpecificProfessionalPhysicalAssessmentsByPage(Pageable pages, Long professionalId)throws  NotFoundException{
        return this.physicalAssessmentRepository.findAllByProfessionalIdAndDeletedAtIsNullOrderByPhysicalAssessmentId(professionalId,pages);
    }

    @Override
    public PhysicalAssessmentResponse getPhysicalAssessmentById(Long physicalAssessmentId)throws  NotFoundException {
        return PhysicalAssessmentResponse.fromPhysicalAssessment(this.physicalAssessmentRepository.findOneByPhysicalAssessmentId(physicalAssessmentId));
    }
    @Override
    public List<PhysicalAssessment> getSpecificUserPhysicalAssessments(Long idUser)throws  NotFoundException{
        return this.physicalAssessmentRepository.findAllByUserIdAndDeletedAtIsNullOrderByPhysicalAssessmentId(idUser);
    }
    @Override
    public List<PhysicalAssessment> getSpecificProfessionalPhysicalAssessments(Long professionalId)throws  NotFoundException{
        return this.physicalAssessmentRepository.findAllByProfessionalIdAndDeletedAtIsNullOrderByPhysicalAssessmentId(professionalId);
    }


}
