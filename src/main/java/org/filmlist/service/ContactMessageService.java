package org.filmlist.service;

import org.filmlist.domain.ContactMessage;
import org.filmlist.dto.ContactMessageDTO;
import org.filmlist.dto.request.ContactMessageRequest;
import org.filmlist.exception.message.ErrorMessage;
import org.filmlist.mapper.ContactMessageMapper;
import org.filmlist.repository.ContactMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageMapper contactMessageMapper;

    public ContactMessageService(ContactMessageRepository contactMessageRepository, ContactMessageMapper contactMessageMapper) {
        this.contactMessageRepository = contactMessageRepository;
        this.contactMessageMapper = contactMessageMapper;
    }

    public void saveMessage(ContactMessageRequest contactMessageRequest) {
       ContactMessage contactMessage = contactMessageMapper.
               contactMessageRequestToContactMessage(contactMessageRequest);
       contactMessageRepository.save(contactMessage);
    }

    public List<ContactMessageDTO> getAllMessages() {
        return contactMessageMapper.map(contactMessageRepository.findAll());
    }

    public Page<ContactMessageDTO> getAllPageable(Pageable pageable) {
        Page<ContactMessage> pageableContactMessages = contactMessageRepository.findAll(pageable);
        return convertCMPagesToDTOs(pageableContactMessages);
    }

    private Page<ContactMessageDTO> convertCMPagesToDTOs (Page<ContactMessage> pageableContactMessages){
       return pageableContactMessages.map(contactMessageMapper::contactMessageToContactMessageDTO);
    }

    public ContactMessageDTO getByID(Long id) {
        ContactMessage contactMessage = contactMessageRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION, id)));
        return contactMessageMapper.contactMessageToContactMessageDTO(contactMessage);
    }
}
