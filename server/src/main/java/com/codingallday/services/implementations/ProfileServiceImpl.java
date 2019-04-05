package com.codingallday.services.implementations;

import com.codingallday.models.Profile;
import com.codingallday.repositories.ProfileRepository;
import com.codingallday.services.ProfileService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<Profile> createProfile(ObjectNode objectNode) {

        String avatarImg = objectNode.get("avatarImg").asText();
        String bio = objectNode.get("bio").asText();
        String country = objectNode.get("country").asText();
        String dateOfBirth = objectNode.get("dateOfBirth").asText();
        String firstName = objectNode.get("firstName").asText();
        String lastName = objectNode.get("lastName").asText();

        Profile profile = new Profile();

        try {
            profile.setProfilePicture(avatarImg);
            profile.setBio(bio);
            profile.setCountry(country);
            profile.setDateOfBirth(dateOfBirth);
            profile.setFirstName(firstName);
            profile.setLastName(lastName);

            try {
                Profile savedProfile = profileRepository.save(profile);
                return Optional.of(savedProfile);

            } catch (Exception e) {
                e.printStackTrace();
                Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * This method gets a profile based on its id.
     * @param id
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    public Optional<Profile> getProfileById(Long id) {

        try {
            Optional<Profile> optionalProfile = profileRepository.findById(id);

            if (optionalProfile.isPresent()) {
                return optionalProfile;
            }
            return Optional.empty();

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
