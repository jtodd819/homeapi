package com.api.homeapi.controller;
import com.api.homeapi.model.User;
import com.api.homeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired private UserRepository userRepository;

    @GetMapping(value="/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws ResponseStatusException {
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found:: " + userId));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(value="/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody User user) throws ResponseStatusException {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found:: " + userId));
        currentUser.setUserName(user.getUserName());
        currentUser.setPassword(user.getPassword());
        userRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws ResponseStatusException {
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found:: " + userId));
        userRepository.delete(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
