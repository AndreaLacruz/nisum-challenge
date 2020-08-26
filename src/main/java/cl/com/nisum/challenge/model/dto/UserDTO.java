package cl.com.nisum.challenge.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "name", "email", "password", "phones"})
public class UserDTO implements Serializable{

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @Size(min = 1, max = 100)
    @Email
    @NotBlank(message = "name is required")
    private String email;

    @Pattern(regexp = "[A-Za-z\\d$@$!%*?&]{8,15}")
    @NotBlank(message = "name is required")
    private String password;

    @NotNull(message = "phones_id is required")
    private Long phonesId;

    private List<PhonesDTO> phone;

}
