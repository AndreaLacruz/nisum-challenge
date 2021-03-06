package cl.com.nisum.challenge.model.mapper;

import cl.com.nisum.challenge.model.dto.UserDTO;
import cl.com.nisum.challenge.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends DataCyclerMapper<UserDTO, User> {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
}