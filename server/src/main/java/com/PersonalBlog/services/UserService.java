package com.PersonalBlog.services;

import com.PersonalBlog.models.User;
import com.PersonalBlog.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.PersonalBlog.utils.Util.*;
import static com.PersonalBlog.utils.Util.customMessage;

/**
 * This class contains all restful services for users.
 */
@RestController
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * This method checks if a user exists in the database. If that user exits and the credentials sent in the frontend
     * matches with the data in the database, then this method perform a login in the website.
     * @param user
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/login")
    public ResponseEntity login(@Valid @RequestBody User user) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        if (findUser(user.getUsername(), user.getPassword(), userRepository) != -1) {

            objectNode.put("status", "200");
            objectNode.put("message", true);
            return new ResponseEntity<>(objectNode, HttpStatus.OK);
        }

        objectNode.put("status", "401");
        objectNode.put("message", false);

        return new ResponseEntity<>(objectNode, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method creates a user if the user that is creating a new user is a "admin" user.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/createUser")
    public ResponseEntity createUser(@Valid @RequestBody ObjectNode objectNode) {

        String newUsername = objectNode.get("newUsername").asText();
        String newPassword = objectNode.get("newPassword").asText();
        int admin = objectNode.get("admin").asInt();

        long userId = checkUser(objectNode, userRepository);

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            if (userId != -1) {
                if (user.getAdmin() == 1) {

                    User user1 = new User();
                    user1.setUsername(newUsername);
                    user1.setPassword(newPassword);
                    user1.setAdmin((byte) admin);

                    try {
                        userRepository.save(user1);
                        return new ResponseEntity<>(customMessage("User created", 200), HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<>(customMessage("This user is not an admin", 401), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(customMessage("Cannot create user.", 401), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(customMessage("Cannot create user.", 404), HttpStatus.NOT_FOUND);
    }

    /**
     * This method deletes a user if the user that is trying to delete it is an "admin" user.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value="/deleteUser")
    public ResponseEntity deleteUser(@Valid @RequestBody ObjectNode objectNode) {

        long id = objectNode.get("id").asLong();

        long userId = checkUser(objectNode, userRepository);

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            if (userId != -1) {
                if (user.getAdmin() == 1) {

                    try {
                        userRepository.deleteById(id);
                        return new ResponseEntity<>(customMessage("User deleted", 200), HttpStatus.OK);

                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<>(customMessage("This user is not an admin", 401), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(customMessage("Cannot delete user.", 401), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(customMessage("Cannot delete user.", 404), HttpStatus.NOT_FOUND);
    }
}
