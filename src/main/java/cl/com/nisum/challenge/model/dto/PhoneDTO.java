package cl.com.nisum.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ "id", "number", "city_code", "country_code" })
public class PhoneDTO {
    private Long id;

    @NotNull(message = "name is required")
    private Integer number;

    @NotNull(message = "name is required")
    private Integer cityCode;

    @NotNull(message = "name is required")
    private Integer countryCode;

    private UserDTO user;
}
