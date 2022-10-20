package com.example.empresasjava.controller;

import com.example.empresasjava.models.MessagesCommunication;
import com.example.empresasjava.models.RequestEntity.MessagesCommunicationRequest;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.MessagesCommunicationResponse;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.dto.UserDto;
import com.example.empresasjava.service.MessagesCommunicationService;
import com.example.empresasjava.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/message-service")
public class MessagesCommunicationController {




    @Autowired
    private MessagesCommunicationService messagesCommunicationService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar nova mensagem ")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<MessagesCommunicationResponse> createMessage(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody MessagesCommunicationRequest request) throws NotFoundException {

        MessagesCommunicationResponse messagesCommunicationResponse = this.messagesCommunicationService.create(request);
        return ResponseEntity.ok().body(
                messagesCommunicationResponse
        );
    }
    @PostMapping(path = "/edit")
    @ApiOperation(value = "Editar mensagem existente")
    public ResponseEntity<MessagesCommunicationResponse> editUser(
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody MessagesCommunicationRequest request) throws NotFoundException {

        return ResponseEntity.ok().body(
                this.messagesCommunicationService.editMessage(request)
        );
    }


    @DeleteMapping(path = "/delete/idMessage/{idMessage}")
    @ApiOperation(value = "Desativa usuário existente")
    public ResponseEntity<MessagesCommunicationResponse> deleteUser(@PathVariable(value="idMessage") Long idMessage) throws NotFoundException {
        return ResponseEntity.ok().body(
                this.messagesCommunicationService.deleteMessage(idMessage)
        );
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<MessagesCommunication> listMessagesByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);
        return this.messagesCommunicationService.listMessagesByPage(pages);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/page/{page}/size/{size}/title/{title}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<MessagesCommunication> listSpecificMessageByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
                    int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
                    int size,
            @PathVariable(value="title")
                    String title
    ) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.messagesCommunicationService.listSpecificMessageByPage(pages, title);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getmessagebyid/messageid/{messageid}")
    @ResponseBody
    @ApiOperation(value = "Lista planos por página quantidade")
    public ResponseEntity<MessagesCommunicationResponse> getMessageByMessageId(
            @PathVariable(value="messageid")
            Long messageid)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.messagesCommunicationService.getMessageByMessageId(messageid)
        );

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getmessagebyuserId/userId/{userId}")
    @ResponseBody
    @ApiOperation(value = "Lista planos por página quantidade")
    public ResponseEntity<MessagesCommunicationResponse> getMessageByUserId(
            @PathVariable(value="userId")
            Long userId)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.messagesCommunicationService.getMessageByUserId(userId)
        );

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getmessagebytitle/title/{title}")
    @ResponseBody
    @ApiOperation(value = "Lista planos por página quantidade")
    public ResponseEntity<MessagesCommunicationResponse> getMessageByTitle(
            @PathVariable(value="title")
            String title)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.messagesCommunicationService.getMessageByTitle(title)
        );

    }

}