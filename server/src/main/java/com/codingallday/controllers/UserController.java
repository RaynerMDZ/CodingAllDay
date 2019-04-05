package com.codingallday.controllers;

import com.codingallday.enums.Roles;
import com.codingallday.models.User;
import com.codingallday.repositories.UserRepository;
import com.codingallday.services.UserService;
import com.codingallday.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

  private final UserService service;
  private final UserRepository userRepository;

  public UserController(UserService service, UserRepository userRepository) {
    this.service = service;
    this.userRepository = userRepository;
  }

  /**
   * This method checks if a user exists in the database. If that user exits and the credentials sent in the frontend
   * matches with the data in the database, then this method perform a login in the website.
   * @param user
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value="/login")
  public ResponseEntity login(@Valid @RequestBody User user) {

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode objectNode = mapper.createObjectNode();

    if (Util.findUser(user.getUsername(), user.getPassword(), userRepository) != -1) {

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
//  @PostMapping(value="/create-user")
//  public ResponseEntity createUser(@Valid @RequestBody ObjectNode objectNode) {
//
//    String newUsername = objectNode.get("newUsername").asText();
//    String newPassword = objectNode.get("newPassword").asText();
//    int admin = objectNode.get("admin").asInt();
//
//    long userId = Util.checkUser(objectNode, userRepository);
//
//    User user = userRepository.findById(userId).orElse(null);
//
//    if (user != null) {
//      if (userId != -1) {
//        //Change this logic to use as a Admin
//        if (admin == 1) {
//
//          User user1 = new User();
//          user1.setUsername(newUsername);
//          user1.setPassword(newPassword);
//          user1.setRole(Roles.ROLE_USER);
//
//          try {
//            userRepository.save(user1);
//            return new ResponseEntity<>(Util.customMessage("User created", 200), HttpStatus.OK);
//          } catch (Exception e) {
//            return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
//          }
//        }
//        return new ResponseEntity<>(Util.customMessage("This user is not an admin", 401), HttpStatus.UNAUTHORIZED);
//      }
//      return new ResponseEntity<>(Util.customMessage("Cannot create user.", 401), HttpStatus.UNAUTHORIZED);
//    }
//    return new ResponseEntity<>(Util.customMessage("Cannot create user.", 404), HttpStatus.NOT_FOUND);
//  }
//
//  /**
//   * This method deletes a user if the user that is trying to delete it is an "admin" user.
//   * @param objectNode
//   * @return ResponseEntity
//   * @author RaynerMDZ
//   */
//  @DeleteMapping(value="/delete-user")
//  public ResponseEntity deleteUser(@Valid @RequestBody ObjectNode objectNode) {
//
//    long id = objectNode.get("id").asLong();
//
//    long userId = Util.checkUser(objectNode, userRepository);
//
//    User user = userRepository.findById(userId).orElse(null);
//
//    if (user != null) {
//      if (userId != -1) {
//        //Change this logic to use as a Admin
//        if (userId == 1) {
//
//          try {
//            userRepository.deleteById(id);
//            return new ResponseEntity<>(Util.customMessage("User deleted", 200), HttpStatus.OK);
//
//          } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
//          }
//        }
//        return new ResponseEntity<>(Util.customMessage("This user is not an admin", 401), HttpStatus.UNAUTHORIZED);
//      }
//      return new ResponseEntity<>(Util.customMessage("Cannot delete user.", 401), HttpStatus.UNAUTHORIZED);
//    }
//    return new ResponseEntity<>(Util.customMessage("Cannot delete user.", 404), HttpStatus.NOT_FOUND);
//  }
}
