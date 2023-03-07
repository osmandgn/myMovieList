package org.filmlist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageRequest {

    @Size(min = 3, max = 25, message = "Your name ${validatedValue} must be between {min} and {max} chars long")
    @NotBlank(message = "Please provide a name")
    private String name;

    @Email
    private String email;

    @Size(min = 3, max = 50, message = "Subject must be between {min} and {max} chars long")
    @NotBlank
    private String subject;

    @Size(min = 20, max = 255, message = "Your message must be between {min} and {max} chars long")
    @NotBlank
    private String body;
}
