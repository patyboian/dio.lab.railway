package me.dio.controller;

import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService; // as informações estão na camada de serviço, por isso chamamos ela

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}") // O Spring entende que queremos expor este medodo como um GET e vamos receber um ID
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) { // ele recebe o requestbody pq é o corpo da requisição
        var userCreated = userService.create(userToCreate);
        // o URI location retorna a localização
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}
