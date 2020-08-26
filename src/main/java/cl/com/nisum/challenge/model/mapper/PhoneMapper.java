package cl.com.nisum.challenge.model.mapper;

import cl.com.nisum.challenge.model.dto.PhoneDTO;
import cl.com.nisum.challenge.model.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneMapper extends DataCyclerMapper<PhoneDTO, Phone> {
    PhoneMapper MAPPER = Mappers.getMapper(PhoneMapper.class);
}
