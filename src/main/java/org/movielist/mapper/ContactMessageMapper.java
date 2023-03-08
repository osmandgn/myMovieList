package org.movielist.mapper;

import org.movielist.domain.ContactMessage;
import org.movielist.dto.ContactMessageDTO;
import org.movielist.dto.request.ContactMessageRequest;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMessageMapper {

    ContactMessageDTO contactMessageToContactMessageDTO(ContactMessage contactMessage);

    @Mapping(target = "id", ignore = true)
    ContactMessage contactMessageRequestToContactMessage(ContactMessageRequest contactMessageRequest);

    List<ContactMessageDTO> map(List<ContactMessage> contactMessageList);
}
