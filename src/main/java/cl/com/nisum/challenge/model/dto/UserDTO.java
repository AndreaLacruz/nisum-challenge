package cl.com.nisum.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ "id", "name", "email", "password", "phones" })
public class UserDTO {

    private UUID id;

    @NotBlank(message = "name is required")
    private String name;

    @Email()
    @NotBlank(message = "name is required")
    private String email;

    @Pattern(regexp = "^(?=.*\\d{2,})(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{5,}$")
    @NotBlank(message = "name is required")
    private String password;

    @NotNull(message = "phones_id is required")
    private Long phonesId;

    private List<PhoneDTO> phone;

}
