package cl.com.nisum.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ "id", "name", "email", "password", "phones", "token", "create_at", "update_at", "last_login"})
public class UserDTO {

    private UUID id;

    @NotBlank(message = "name is required")
    private String name;

    @Email()
    @NotBlank(message = "email is required")
    private String email;

    @Pattern(regexp = "^(?=.*\\d{2,})(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{5,}$",
    message = "password must be at least 5 characters long, contain a capital letter and a 2-digit number")
    @NotBlank(message = "password is required")
    private String password;

    @Valid
    @NotEmpty(message = "at least one phone number is required")
    @JsonIgnoreProperties({ "user" })
    private List<PhoneDTO> phones;

    private String token;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private LocalDateTime lastLogin;

}

/*
{
	"name": "Andrea Lacruz",
	"email": "andrea@hotmail.cl",
	"password": "Password22",
	"phones": [
		{
			"city_code": 1,
			"country_code": 54,
			"number": 1234567
		}
	]
}

 */
