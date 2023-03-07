package org.filmlist.controller;

import org.filmlist.dto.request.ContactMessageRequest;
import org.filmlist.dto.response.ResponseMessage;
import org.filmlist.service.ContactMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contactmessage")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;


    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping("/visitors")
    public ResponseEntity<ResponseMessage> createMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest){

        contactMessageService.saveMessage(contactMessageRequest);

        ResponseMessage responseMessage = new ResponseMessage("ContactMessage successfully created", true);
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);

    }



}
