package com.api.homeapi.controller;
import com.api.homeapi.model.UserAccount;
import com.api.homeapi.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/users")
public class UserAccountController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping()
    public ResponseEntity<Void> signUp(@RequestBody UserAccount user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userAccountRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public UserAccountController(UserAccountRepository userAccountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PutMapping(value="/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserAccount user) throws ResponseStatusException {
        UserAccount currentUser = userAccountRepository.findById(userId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found:: " + userId));
        currentUser.setEmailAddress(user.getEmailAddress());
        currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userAccountRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping(value="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws ResponseStatusException {
        UserAccount user = userAccountRepository.findById(userId).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found:: " + userId));
        userAccountRepository.delete(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
