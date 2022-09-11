package fuad.hajiyev.abc_app.controllers;

import fuad.hajiyev.abc_app.entities.User;
import fuad.hajiyev.abc_app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getUsersFomService();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id) {

        User user = userService.getUserByIdFromService(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUSer) {

        return new ResponseEntity<>(userService.createUserFomService(newUSer), HttpStatus.CREATED);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateOneUser(@PathVariable Long userId, @RequestBody User updateUser) {
        User user = userService.updateUserFromService(userId, updateUser);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteOneUser(@PathVariable Long userId) {
        User user = userService.deleteOneUserFromService(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
