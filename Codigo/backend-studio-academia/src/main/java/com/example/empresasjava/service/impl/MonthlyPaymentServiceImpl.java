package com.example.empresasjava.service.impl;

import com.example.empresasjava.enums.MonthlyPaymentStatusEnum;
import com.example.empresasjava.models.MonthlyPayment;
import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.repository.MonthlyPaymentRepository;
import com.example.empresasjava.repository.UserRepository;
import com.example.empresasjava.service.MonthlyPaymentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {

    @Autowired
    private MonthlyPaymentRepository monthlyPaymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;







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
    public String uploadImage(MultipartFile paymentVoucherImage) throws NonUniqueResultException, NotFoundException, IOException {
        return savePaymentVoucher(paymentVoucherImage);
    }

    @Override
    public Page<MonthlyPayment> listUserPendencyRequestsByPageWithSize(Pageable pages, Long idUser) throws NonUniqueResultException, NotFoundException, IOException {
        return this.monthlyPaymentRepository.findAllByPaymentStatusNotAndUserIdAndDeletedAtIsNullOrderByDueDateDesc(MonthlyPaymentStatusEnum.PAGO.getCode(),idUser,pages);
    }

    @Override
    public Page<MonthlyPayment> listAllPendencyRequestsByPageWithSize(Pageable pages) throws NonUniqueResultException, NotFoundException, IOException {
        return this.monthlyPaymentRepository.findAllByPaymentStatusNotAndDeletedAtIsNullOrderByDueDateDesc("PAGO",pages);
    }

    @Override
    public MonthlyPaymentResponse createRequestForApprove(MonthlyPaymentRequest request) throws NonUniqueResultException, NotFoundException,IOException {

        Calendar c = Calendar.getInstance();
        c.setTime(request.getDueDate());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

            MonthlyPayment monthlyPayment = this.monthlyPaymentRepository.findByUserIdAndDueDate(request.getUserId(), c.getTime());
            monthlyPayment.setPaymentStatus(MonthlyPaymentStatusEnum.EM_ANALISE.getCode());
            monthlyPayment.setPaymentVoucher(request.getPaymentVoucher());
            monthlyPayment.setMessage(request.getMessage());
            monthlyPayment.setPaymentDate(new Date());
            return MonthlyPaymentResponse.fromMonthlyPayment(this.monthlyPaymentRepository.save(monthlyPayment));

    }

    private String savePaymentVoucher(MultipartFile image){

        try {
            Date saveDate = new Date();

            User loggedUser = userServiceImpl.getUserByPrincipal();





            String path = "c:/studioImages/" +saveDate.getTime() +"_"+loggedUser.getIdUser()+"_.jpg"; // lugar pra salvar a imagem

            //private final Path rootLocation = Paths.get("path");
            //Files.copy(image.getInputStream(), this.rootLocation.resolve(""+saveDate.toString()+"_"+idUser+".jpg"));



             File dest = new File(path);
             image.transferTo(dest);
            return path;
        }catch (Exception e){e.printStackTrace();return "fail";}

    }

     @Override
    public MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {

        MonthlyPayment monthlyPayment = Optional.of(this.monthlyPaymentRepository.findOneByMonthlyPaymentId(id)).orElseThrow(()-> new NonUniqueResultException("Exercicio inexistente"));
        monthlyPayment.setDeletedAt(new Date());
        return MonthlyPaymentResponse.fromMonthlyPayment(this.monthlyPaymentRepository.save(monthlyPayment));
    }
    @Override
    public MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser) throws NonUniqueResultException, NotFoundException {

        Calendar c = Calendar.getInstance();
        c.setTime(request.getDueDate());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        MonthlyPayment monthlyPayment = this.monthlyPaymentRepository.findByUserIdAndDueDate(request.getUserId(), c.getTime());
        monthlyPayment.setPaymentStatus(monthlyPayment.getPaymentStatus());
        monthlyPayment.setPaymentVoucher(request.getPaymentVoucher());
        monthlyPayment.setMessage(request.getMessage());
        monthlyPayment.setPaymentDate(new Date());
        return MonthlyPaymentResponse.fromMonthlyPayment(this.monthlyPaymentRepository.save(monthlyPayment));

    }


    @Override
    public Page<MonthlyPayment> listRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return this.monthlyPaymentRepository.findAllByDeletedAtIsNullOrderByDueDateDesc(pages);

    }

    @Override
    public Page<MonthlyPayment> listSpecificRequestsByPage(Pageable pages, String paymentStatusRequest) throws NonUniqueResultException, NotFoundException {


        return this.monthlyPaymentRepository.findAllByPaymentStatusAndDeletedAtIsNullOrderByDueDateDesc(pages, paymentStatusRequest);

    }


    @Override
    public Page<MonthlyPayment> listUserRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
       return this.monthlyPaymentRepository.findAllByUserIdAndDeletedAtIsNullOrderByDueDateDesc(pages,id);


    }

    @Override
    public Page<MonthlyPayment> listUserSpecificRequestsByPage(Pageable pages, Long id, String paymentStatusRequest) throws NonUniqueResultException, NotFoundException {

       String keysearch = MonthlyPaymentStatusEnum.getByCd(paymentStatusRequest).getCode();

        return this.monthlyPaymentRepository.findAllByUserIdAndPaymentStatusAndDeletedAtIsNullOrderByDueDateDesc(pages,id, keysearch);

    }


    @Override
    public MonthlyPaymentResponse approveMonthlyRequest(MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {

        Calendar c = Calendar.getInstance();
        c.setTime(request.getDueDate());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        MonthlyPayment monthlyPayment = this.monthlyPaymentRepository.findByUserIdAndDueDate(request.getUserId(), c.getTime());
        monthlyPayment.setPaymentStatus((MonthlyPaymentStatusEnum.PAGO.getCode()));
        monthlyPayment.setPaymentVoucher(monthlyPayment.getPaymentVoucher());
        monthlyPayment.setMessage(monthlyPayment.getMessage());
        monthlyPayment.setPaymentDate(monthlyPayment.getPaymentDate());
        monthlyPayment.setApprovedDate(new Date());
        return MonthlyPaymentResponse.fromMonthlyPayment(this.monthlyPaymentRepository.save(monthlyPayment));
    }

    @Override
    public MonthlyPaymentResponse reproveMonthlyRequest(MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        Calendar c = Calendar.getInstance();
        c.setTime(request.getDueDate());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        MonthlyPayment monthlyPayment = this.monthlyPaymentRepository.findByUserIdAndDueDate(request.getUserId(), c.getTime());
        monthlyPayment.setPaymentStatus((MonthlyPaymentStatusEnum.NAO_RECEBIDO.getCode()));
        monthlyPayment.setPaymentVoucher(monthlyPayment.getPaymentVoucher());
        monthlyPayment.setMessage(monthlyPayment.getMessage());
        monthlyPayment.setPaymentDate(monthlyPayment.getPaymentDate());
        return MonthlyPaymentResponse.fromMonthlyPayment(this.monthlyPaymentRepository.save(monthlyPayment));
    }

    @Override
    public @ResponseBody byte[] getImages(Long id) throws NonUniqueResultException, NotFoundException, IOException {

        MonthlyPayment monthlyPayment = Optional.of(this.monthlyPaymentRepository.findOneByMonthlyPaymentId(id))
                .orElseThrow(()-> new NonUniqueResultException("Requisição inexistente"));

        Path imagePath = Paths.get(monthlyPayment.getPaymentVoucher());

        return Files.readAllBytes(imagePath);
    }
}
