package com.example.empresasjava.service.impl;

import com.example.empresasjava.enums.MonthlyPaymentStatusEnum;
import com.example.empresasjava.models.MonthlyPayment;
import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.repository.MonthlyPaymentRepository;
import com.example.empresasjava.repository.UserRepository;
import com.example.empresasjava.service.MonthlyPaymentService;
import com.sun.xml.bind.v2.TODO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NonUniqueResultException;
import javax.validation.Path;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/*TODO : IMPLEMENTATIONS
     */

@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {

    @Autowired
    private MonthlyPaymentRepository monthlyPaymentRepository;

    @Autowired
    private UserRepository userRepository;




    //TODO: 04/10/2022 //create
    @Override
    public MonthlyPaymentResponse create(){
        return null;
    }

    @Override
    public MonthlyPayment create(User user) {

        MonthlyPayment monthlyPayment = new MonthlyPayment(
                user.getDueDate(),
                null,
                null,
                user.getIdUser(),
                null,
                null,
                MonthlyPaymentStatusEnum.AGUARDANDO_PAGAMENTO.getCode()
        );

        return this.monthlyPaymentRepository.save(monthlyPayment);
    }


    @Override
    public void createNextPayment() {
        List<User> allByCurrentDay = this.userRepository.findAllByCurrentDay();

        List<MonthlyPayment> monthlyPayments = allByCurrentDay.stream().map(MonthlyPayment::fromUser
        ).collect(Collectors.toList());

        this.monthlyPaymentRepository.saveAll(monthlyPayments);

    }

    @Override
    public MonthlyPaymentResponse createRequestForApprove(MonthlyPaymentRequest request, MultipartFile paymentVoucherImage) throws NonUniqueResultException, NotFoundException,IOException {
        Calendar c = Calendar.getInstance();
        c.setTime(request.getDueDate());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        String resp = savePaymentVoucher(paymentVoucherImage, request.getUserId());

        if (!resp.contains("fail")) {

            MonthlyPayment monthlyPayment = this.monthlyPaymentRepository.findByUserIdAndDueDate(request.getUserId(), c.getTime());
            monthlyPayment.setPaymentStatus(MonthlyPaymentStatusEnum.EM_ANALISE.getCode());
            monthlyPayment.setPaymentVoucher(resp);
            monthlyPayment.setPaymentDate(new Date());
            return MonthlyPaymentResponse.fromMonthlyPayment(this.monthlyPaymentRepository.save(monthlyPayment));
        }else {
            //throw new NonUniqueResultException();
            //throw new NotFoundException();
            throw new IOException();
        }
    }

    private String savePaymentVoucher(MultipartFile image, Long idUser){

        try {
            Date saveDate = new Date();

            String path = "C:/studioImages/" + saveDate.toString() + "_" + idUser + ".jpg"; // lugar pra salvar a imagem

            //private final Path rootLocation = Paths.get("path");
            //Files.copy(image.getInputStream(), this.rootLocation.resolve(""+saveDate.toString()+"_"+idUser+".jpg"));



             File dest = new File(path);
             image.transferTo(dest);
            return path;
        }catch (Exception e){e.printStackTrace();return "fail";}

    }

    //TODO: 04/10/2022 //delete
    @Override
    public MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //edit ( usuario pode editar sua propria requisição, admin nao altera requisição do usuario, apenas aprova ou deleta)
    @Override
    public MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //listar todas para administrador
    @Override
    public Page<MonthlyPaymentResponse> listRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022  //listar todas as pendentes para administrador
    @Override
    public Page<MonthlyPaymentResponse> listPendencyRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //listar todas ja aprovadas para administrador
    @Override
    public Page<MonthlyPaymentResponse> listApprovedRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //listar todas para usuario
    @Override
    public Page<MonthlyPaymentResponse> listUserRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022   //listar pendentes para aluno  ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
    @Override
    public Page<MonthlyPaymentResponse> listUserPendencyRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022  //listar todas as ja aprovadas para aluno ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
    @Override
    public Page<MonthlyPaymentResponse> listUserApprovedRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: aprova pagamento (e dispara para o usuario uma notificação de pagamento aprovado)
    @Override
    public MonthlyPaymentResponse approveMonthlyRequest(MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //reprova pagamento (e dispara para o usuario uma notificação de pagamento reprovado)
    @Override
    public MonthlyPaymentResponse reproveMonthlyRequest(MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }


}
