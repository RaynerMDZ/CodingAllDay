package com.PersonalBlog.utils;

import com.PersonalBlog.models.User;
import com.PersonalBlog.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This class contains all reusable methods for the other classes.
 * @author RaynerMDZ
 */
public class Util {

    /**
     *  Looks for an user inside the database. If that user exist then the method returns its id.
     * @param username
     * @param password
     * @param userRepository
     * @return long
     * @author RaynerMDZ
     */
    public static long findUser(String username, String password, UserRepository userRepository) {

        // Iterates over the database looking for the user.
        for (User user: userRepository.findAll()) {
            // If the username and password matches with the ones inside the database then goes into the if.
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                // Returns the id of the found user.
                return user.getId();
            }
        }
        return -1;
    }

    /**
     *  Creates a Http message.
     * @param message
     * @param status
     * @return ObjectNode
     * @author RaynerMDZ
     */
    public static ObjectNode customMessage(String message, int status) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode object = mapper.createObjectNode();

        object.put("status", status);
        object.put("message", message);

        return object;
    }

    /**
     *
     * @param objectNode
     * @param userRepository
     * @return long
     * @author RaynerMDZ
     */
    public static long checkUser(ObjectNode objectNode, UserRepository userRepository) {

        String username = objectNode.get("username").asText();
        String password = objectNode.get("password").asText();

        return findUser(username, password, userRepository);
    }

}
