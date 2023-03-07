package org.filmlist.service;

import org.filmlist.domain.ContactMessage;
import org.filmlist.dto.request.ContactMessageRequest;
import org.filmlist.mapper.ContactMessageMapper;
import org.filmlist.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

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
}
