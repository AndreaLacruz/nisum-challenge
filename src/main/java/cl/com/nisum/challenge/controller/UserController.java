package cl.com.nisum.challenge.controller;

import cl.com.nisum.challenge.model.dto.UserDTO;
import cl.com.nisum.challenge.service.PhoneService;
import cl.com.nisum.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userServices;

    @Autowired
    private PhoneService phonesServices;

    //USERS

    @PostMapping({"/users", "/users/"}) // http://localhost:8080/users
    public ResponseEntity addNewUser(@RequestBody @Valid UserDTO dto) throws URISyntaxException {
        UserDTO saved = userServices.save(dto);
        return ResponseEntity.created(new URI("/users/" + saved.getId())).body(saved);
    }

    @GetMapping({"/users", "/users/"}) // http://localhost:8080/users
    public ResponseEntity getAllUsers(){
        List<UserDTO> all = userServices.findAll();
        return ResponseEntity.ok(all);
    }


    @DeleteMapping({"/users/{id}", "/users/{id}/"}) // http://localhost:8080/users/1
    public ResponseEntity deleteUser(UUID id){
        userServices.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping({"/users/{id}", "/users/{id}/"}) // http://localhost:8080/users/1
    public ResponseEntity updateUser(@Valid @RequestBody UserDTO userDTO, UUID id){
        UserDTO userUpdate = userServices.update(userDTO, id);
        return ResponseEntity.ok(userUpdate);
    }


    //PHONES


}
