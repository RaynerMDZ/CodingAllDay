package com.codingallday.controllers;

import com.codingallday.models.Profile;
import com.codingallday.services.ProfileService;
import com.codingallday.utils.Util;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/profile")
public class ProfileController {

  private final ProfileService service;

  public ProfileController(ProfileService service) {
    this.service = service;
  }

  /**
   * This method creates a profile based on the data sent through the frontend.
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value="/create-profile")
  public ResponseEntity createProfile(@Valid @RequestBody ObjectNode objectNode) {

    try {
      Optional<Profile> optionalProfile = service.createProfile(objectNode);

      if (optionalProfile.isPresent()) {
        return new ResponseEntity<>(Util.customMessage("Profile created", 200), HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // implement admin validation.
    return new ResponseEntity<>(Util.customMessage("You don't have permission to create a profile.", 401), HttpStatus.UNAUTHORIZED);
  }

  /**
   * This method gets a profile based on its id.
   * @param id
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @GetMapping(value="/get-profile/{id}")
  public ResponseEntity getProfileById(@PathVariable Long id) {
    try {

      Optional<Profile> optionalProfile = service.getProfileById(id);

      if (optionalProfile.isPresent()) {
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(Util.customMessage("This post cannot be found", 404), HttpStatus.NOT_FOUND);

    } catch (Exception e) {
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
