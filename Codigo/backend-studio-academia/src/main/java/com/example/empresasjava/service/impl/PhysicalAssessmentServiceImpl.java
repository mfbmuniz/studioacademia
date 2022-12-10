package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.PhysicalAssessment;
import com.example.empresasjava.models.RequestEntity.PhysicalAssessmentRequest;
import com.example.empresasjava.models.ResponseEntity.PhysicalAssessmentResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.repository.PhysicalAssessmentRepository;
import com.example.empresasjava.service.PhysicalAssessmentService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NonUniqueResultException;
import javax.xml.xpath.XPath;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.System.out;


@Service
public class PhysicalAssessmentServiceImpl implements PhysicalAssessmentService {

    @Autowired
    PhysicalAssessmentRepository physicalAssessmentRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public PhysicalAssessmentResponse createPhysicalAssessment(PhysicalAssessmentRequest physicalAssessmentRequest)
            throws NonUniqueResultException, NotFoundException{

        Optional<PhysicalAssessment> physicalAssessment = Optional.ofNullable(this.physicalAssessmentRepository.
                findOneByPhysicalAssessmentId(physicalAssessmentRequest.getPhysicalAssessmentId()));

        if(!physicalAssessment .isPresent()){
            return PhysicalAssessmentResponse.fromPhysicalAssessment(this.physicalAssessmentRepository.save(PhysicalAssessmentRequest.toPhysicalAssessment(physicalAssessmentRequest)));
        }else{
            throw new NonUniqueResultException("Avaliação ja cadastrada ja cadastrado!");
        }
    }

    @Override
    public PhysicalAssessmentResponse editPhysicalAssessment(PhysicalAssessmentRequest physicalAssessmentRequest)throws  NotFoundException{

        PhysicalAssessment physicalAssessmentTemp = Optional.of(this.physicalAssessmentRepository.findOneByPhysicalAssessmentId
                        (physicalAssessmentRequest.getPhysicalAssessmentId())).
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
    @Override
    public String uploadPdf(MultipartFile pdfPhysicalAssessment) throws NonUniqueResultException, NotFoundException, IOException {
        return savePdf(pdfPhysicalAssessment);
    }

    @Override
    public byte[] getPdf(Long physicalAssessmentId) throws NonUniqueResultException, NotFoundException, IOException {

        PhysicalAssessment physicalAssessment = Optional.of(this.physicalAssessmentRepository.findOneByPhysicalAssessmentId(physicalAssessmentId))
                .orElseThrow(()-> new NonUniqueResultException("Avaliação inexistente"));

        String fileNamePdf = physicalAssessment.getFilePath(); //"C:\\studioImages\\1670689850714_4_.pdf";

        File file = new File(fileNamePdf);


        // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
        byte[] contents = Files.readAllBytes(Paths.get(fileNamePdf));

        return contents;
    }

    @Override
    public ByteArrayInputStream getPdf2(Long physicalAssessmentId) throws NonUniqueResultException, NotFoundException, IOException, DocumentException {
         return null;
    }





    private String savePdf(MultipartFile pdf){

        try {
            Date saveDate = new Date();

            User loggedUser = userServiceImpl.getUserByPrincipal();


            String path = "C:\\studioImages\\" +saveDate.getTime() +"_"+loggedUser.getIdUser()+"_.pdf"; // lugar pra salvar a imagem

            //private final Path rootLocation = Paths.get("path");
            //Files.copy(image.getInputStream(), this.rootLocation.resolve(""+saveDate.toString()+"_"+idUser+".jpg"));



            File dest = new File(path);
            pdf.transferTo(dest);
            return path;
        }catch (Exception e){e.printStackTrace();return "fail";}

    }
}
