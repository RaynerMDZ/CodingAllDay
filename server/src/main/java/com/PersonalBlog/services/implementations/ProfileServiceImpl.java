package com.PersonalBlog.services.implementations;

import com.PersonalBlog.models.Profile;
import com.PersonalBlog.repositories.ProfileRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.PersonalBlog.utils.Util.customMessage;

/**
 * This class contains all restful services for Profile.
 */
@RestController
public class ProfileServiceImpl {

    @Autowired
    ProfileRepository profileRepository;

    /**
     * This method creates a profile based on the data sent through the frontend.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/createProfile")
    public ResponseEntity createProfile(@Valid @RequestBody ObjectNode objectNode) {

        String avatarImg = objectNode.get("avatarImg").asText();
        String bio = objectNode.get("bio").asText();
        String country = objectNode.get("country").asText();
        String dateOfBirth = objectNode.get("dateOfBirth").asText();
        String firstName = objectNode.get("firstName").asText();
        String lastName = objectNode.get("lastName").asText();

        Profile profile = new Profile();

        profile.setAvatarImg(avatarImg);
        profile.setBio(bio);
        profile.setCountry(country);
        profile.setDateOfBirth(dateOfBirth);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);

        try {
            profileRepository.save(profile);
            return new ResponseEntity<>(customMessage("Profile created", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method gets a profile based on its id.
     * @param id
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getProfile/{id}")
    public ResponseEntity getProfileById(@PathVariable Long id) {

        Profile profile;

        try {

            profile = profileRepository.findById(id).orElse(null);

        } catch (Exception e) {
            return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (profile == null) {
            return new ResponseEntity<>(customMessage("This post cannot be found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
