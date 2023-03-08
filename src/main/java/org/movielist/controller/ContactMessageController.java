package org.movielist.controller;

import org.movielist.dto.ContactMessageDTO;
import org.movielist.dto.request.ContactMessageRequest;
import org.movielist.dto.response.ResponseMessage;
import org.movielist.service.ContactMessageService;
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
        List<ContactMessageDTO> contactMessageDTOS = contactMessageService.getAllMessages();
        return ResponseEntity.ok(contactMessageDTOS);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<ContactMessageDTO>> getAllPageable(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction",
                          required = false,
                          defaultValue = "DESC")
                                   Sort.Direction direction){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<ContactMessageDTO> pageableContactMessageDTOS = contactMessageService.getAllPageable(pageable);
        return ResponseEntity.ok(pageableContactMessageDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactMessageDTO> getMessageWithPath(@PathVariable("id") Long id){
        ContactMessageDTO contactMessageDTO = contactMessageService.getByID(id);
        return ResponseEntity.ok(contactMessageDTO);
    }

    @GetMapping("/request")
    public ResponseEntity<ContactMessageDTO> getMessageWithRequestParam(@RequestParam("id") Long id){
        ContactMessageDTO contactMessageDTO = contactMessageService.getByID(id);
        return ResponseEntity.ok(contactMessageDTO);
    }

}
