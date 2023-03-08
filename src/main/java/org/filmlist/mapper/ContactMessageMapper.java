package org.filmlist.mapper;

import org.filmlist.domain.ContactMessage;
import org.filmlist.dto.ContactMessageDTO;
import org.filmlist.dto.request.ContactMessageRequest;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMessageMapper {

    ContactMessageDTO contactMessageToContactMessageDTO(ContactMessage contactMessage);

    @Mapping(target = "id", ignore = true)
    ContactMessage contactMessageRequestToContactMessage(ContactMessageRequest contactMessageRequest);

    List<ContactMessageDTO> map(List<ContactMessage> contactMessageList);
}
