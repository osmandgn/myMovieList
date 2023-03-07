package org.filmlist.mapper;

import org.filmlist.domain.ContactMessage;
import org.filmlist.dto.ContactMessageDTO;
import org.filmlist.dto.request.ContactMessageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ContactMessageMapper {

    @Mapping(target = "id", ignore = false)
    ContactMessage contactMessageRequestToContactMessage(ContactMessageRequest contactMessageRequest);

    ContactMessageDTO contactMessageToContactMessageDTO(ContactMessage contactMessage);

    List<ContactMessageDTO> map(List<ContactMessage> contactMessages);
}
