package com.codingallday.services;

import com.codingallday.models.Profile;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

public interface ProfileService {

  Optional<Profile> createProfile(ObjectNode node);
  Optional<Profile> getProfileById(Long id);
}
