package com.codingallday.services;

import com.codingallday.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

public interface UserService {

  Optional<User> login(User user);
  Optional<User> createUser(ObjectNode nodes);
  boolean deleteUser(ObjectNode node);
}
