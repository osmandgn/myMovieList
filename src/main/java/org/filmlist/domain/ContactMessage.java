package org.filmlist.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "contact_message")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Size(min = 3, max = 25, message = "Your name ${validatedValue} must be between {min} and {max} chars long")
    @NotBlank(message = "Please provide a name")
    @Column(length = 25, nullable = false)
    private String name;

    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Size(min = 3, max = 50, message = "Subject must be between {min} and {max} chars long")
    @Column(length = 50, nullable = false)
    @NotBlank
    private String subject;

    @Size(min = 20, max = 255, message = "Your message must be between {min} and {max} chars long")
    @NotBlank
    @Column(nullable = false)
    private String body;

}
