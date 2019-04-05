package com.codingallday.services.implementations;

import com.codingallday.models.Profile;
import com.codingallday.repositories.ProfileRepository;
import com.codingallday.services.ProfileService;
import com.codingallday.utils.Util;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class contains all restful services for Profile.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * This method creates a profile based on the data sent through the frontend.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @PostMapping(value="/create-profile")
    public ResponseEntity createProfile(@Valid @RequestBody ObjectNode objectNode) {

        String avatarImg = objectNode.get("avatarImg").asText();
        String bio = objectNode.get("bio").asText();
        String country = objectNode.get("country").asText();
        String dateOfBirth = objectNode.get("dateOfBirth").asText();
        String firstName = objectNode.get("firstName").asText();
        String lastName = objectNode.get("lastName").asText();

        Profile profile = new Profile();

        profile.setProfilePicture(avatarImg);
        profile.setBio(bio);
        profile.setCountry(country);
        profile.setDateOfBirth(dateOfBirth);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);

        try {
            profileRepository.save(profile);
            return new ResponseEntity<>(Util.customMessage("Profile created", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method gets a profile based on its id.
     * @param id
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @GetMapping(value="/get-profile/{id}")
    public ResponseEntity getProfileById(@PathVariable Long id) {

        Profile profile;

        try {

            profile = profileRepository.findById(id).orElse(null);

        } catch (Exception e) {
            return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (profile == null) {
            return new ResponseEntity<>(Util.customMessage("This post cannot be found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
