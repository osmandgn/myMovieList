package org.filmlist.service;

import org.filmlist.dto.request.ContactMessageRequest;
import org.filmlist.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public void saveMessage(ContactMessageRequest contactMessageRequest) {

    }
}
