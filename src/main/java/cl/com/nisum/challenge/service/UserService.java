package cl.com.nisum.challenge.service;

import cl.com.nisum.challenge.components.BusinessLogicExceptionComponent;
import cl.com.nisum.challenge.model.dto.UserDTO;
import cl.com.nisum.challenge.model.entity.Phone;
import cl.com.nisum.challenge.model.entity.User;
import cl.com.nisum.challenge.model.mapper.CycleAvoidingMappingContext;
import cl.com.nisum.challenge.model.mapper.UserMapper;
import cl.com.nisum.challenge.model.repository.PhoneRepository;
import cl.com.nisum.challenge.model.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("UserService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CycleAvoidingMappingContext context;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    private UserMapper userMapper = UserMapper.MAPPER;


    private String doGenerateToken(String subject) {
        final Date createdDate = new Date();
        String base64EncodedSecret = "bXktc2VjcmV0LXNlZWQ=";
        byte[] base64EncodedSecretKey = Base64.getDecoder().decode(base64EncodedSecret);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.HS512, base64EncodedSecretKey)
                .compact();
    }

    @Transactional
    public UserDTO save(UserDTO dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw logicExceptionComponent.getExceptionEmailUserAlreadyExists(user.getEmail());
                });

        String jwtToken = doGenerateToken(dto.getEmail());
        dto.setToken(jwtToken);

        dto.getPhones()
                .stream()
                .forEach(phoneDTO -> phoneDTO.setUser(dto));

        User userToSave = userMapper.toEntity(dto, context);
        User userSaved = userRepository.save(userToSave);
        return userMapper.toDto(userSaved, context);
    }

    public List<UserDTO> findAll(){
        List<User> user = userRepository.findAll();
        List<UserDTO> userDTOList = userMapper.toDto(user, context);
        return userDTOList;
    }

    public void delete(UUID id) {
        User userToDelete = userRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("User", id));
        userRepository.delete(userToDelete);
    }

    public UserDTO update(UserDTO dto, UUID id){
        Optional<User> byIdOptional = userRepository.findById(id);
        UserDTO user = null;
        if (byIdOptional.isPresent()){
            User userById = byIdOptional.get();
            user.setId(userById.getId());
            User userToUpdate = userMapper.toEntity(dto, context);
            User userUpdated = userRepository.save(userToUpdate);
            user = userMapper.toDto(userUpdated,context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("User", id);
        }
        return user;
    }

}
