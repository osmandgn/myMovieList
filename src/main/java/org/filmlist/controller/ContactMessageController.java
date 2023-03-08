package org.filmlist.controller;

import org.filmlist.dto.ContactMessageDTO;
import org.filmlist.dto.request.ContactMessageRequest;
import org.filmlist.dto.response.ResponseMessage;
import org.filmlist.service.ContactMessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllMessages(){
        List<ContactMessageDTO> messageDTOS = contactMessageService.getAllMessages();
        return ResponseEntity.ok(messageDTOS);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ContactMessageDTO>> getAllPageable(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("prop") String prop,
            @RequestParam(value = "direction",
                          required = false,
                          defaultValue = "DESC")
                                   Sort.Direction direction){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<ContactMessageDTO> pageableContactMessageDTOS = contactMessageService.getAllPageable(pageable);
        return ResponseEntity.ok(pageableContactMessageDTOS);
    }

}
