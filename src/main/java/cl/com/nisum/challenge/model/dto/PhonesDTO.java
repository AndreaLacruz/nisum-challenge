package cl.com.nisum.challenge.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "number", "cityCode", "countryCode"})
public class PhonesDTO implements Serializable {

    private Long id;

    @NotNull(message = "name is required")
    private Integer number;

    @NotNull(message = "name is required")
    private Integer cityCode;

    @NotNull(message = "name is required")
    private Integer countryCode;

    private Set<UserDTO> user;

}
