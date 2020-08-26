package cl.com.nisum.challenge.model.mapper;


import cl.com.nisum.challenge.model.dto.PhonesDTO;
import cl.com.nisum.challenge.model.entity.Phones;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhonesMapper extends DataCyclerMapper<PhonesDTO, Phones>{

    PhonesMapper MAPPER = Mappers.getMapper(PhonesMapper.class);
}
