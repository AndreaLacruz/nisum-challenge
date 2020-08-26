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

    @Autowired
    private PhoneRepository phoneRepository;

    private String doGenerateToken(String subject) {
        final Date createdDate = new Date();
        String base64EncodedSecret = "my-secret-seed";
        byte[] base64EncodedSecretKey = Base64.getDecoder().decode(base64EncodedSecret);

        return Jwts.builder().setSubject(subject).setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.ES512, base64EncodedSecretKey).compact();
    }

    @Transactional
    public UserDTO save(UserDTO dto){
        UUID phoneId = dto.getPhonesId();
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("TypeCategoryCompany", phoneId));
        //Revisar
        User createToken = doGenerateToken(dto.getToken())
        User userToSave = userMapper.toEntity(dto, context);
        userToSave.setPhones((List<Phone>) phone);
        User userSaved = userRepository.save(userToSave);
        UserDTO userDTOSaved = userMapper.toDto(userSaved, context);
        return userDTOSaved;
    }

    public List<UserDTO> findAll(){
        List<User> user = userRepository.findAll();
        List<UserDTO> userDTOList = userMapper.toDto(user, context);
        return userDTOList;
    }

    public void delete(UUID id){
        Optional<User> byIdOptional = userRepository.findById(id);
        if (byIdOptional.isPresent()){
            User userToDelete = byIdOptional.get();
            userRepository.delete(userToDelete);
        }else {
            logicExceptionComponent.throwExceptionEntityNotFound("User", id);
        }
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
