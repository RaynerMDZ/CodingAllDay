package com.codingallday.controllers;

import com.codingallday.models.Post;
import com.codingallday.services.PostService;
import com.codingallday.utils.Util;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/posts")
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  /**
   * This method creates a post based on the data sent through the frontend.
   *
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value = "/create-post")
  public ResponseEntity createPost(@Valid @RequestBody ObjectNode objectNode) {

    try {
      Optional<Post> post = service.createPost(objectNode);

      if (post.isPresent()) {
        return new ResponseEntity<>(Util.customMessage("post created", 200), HttpStatus.OK);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // implement admin validation.
    return new ResponseEntity<>(Util.customMessage("You don't have permission to create a post.", 401), HttpStatus.UNAUTHORIZED);
  }

  /**
   * This method edits a post based on the data provided on the frontend.
   *
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value = "/edit-post")
  public ResponseEntity editPost(@Valid @RequestBody ObjectNode objectNode) {

    try {
      Optional<Post> optionalPost = service.editPost(objectNode);

      if (optionalPost.isPresent()) {
        return new ResponseEntity<>(Util.customMessage("post updated", 200), HttpStatus.OK);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Implement admin validation.
    return new ResponseEntity<>(Util.customMessage("You don't have permission to create a post.", 401), HttpStatus.UNAUTHORIZED);
  }


  /**
   * This parameter deletes a post based on its id.
   *
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value = "/delete-post")
  public ResponseEntity deletePost(@Valid @RequestBody ObjectNode objectNode) {

    try {
      boolean deletedPost = service.deletePost(objectNode);

      if (deletedPost) {
        return new ResponseEntity<>(Util.customMessage("post deleted", 200), HttpStatus.OK);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Implement admin validation
    return new ResponseEntity<>(Util.customMessage("You don't have permission to delete a post.", 401), HttpStatus.UNAUTHORIZED);
  }

  /**
   * This method get a post based on its id from the database.
   *
   * @param id
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @GetMapping(value = "/get-post/{id}")
  public ResponseEntity getPostById(@PathVariable Long id) {

    try {
      Optional<Post> optionalPost = service.getPostById(id);

      if (optionalPost.isPresent()) {
        return new ResponseEntity<>(optionalPost.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(Util.customMessage("This post cannot be found", 404), HttpStatus.NOT_FOUND);

    } catch (Exception e) {
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * This method takes a number "limit" and a number "pages" to get all post that will be displayed per page.
   *
   * @param limit
   * @param page
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @GetMapping(value = "/get-posts")
  public ResponseEntity getAllPosts(@RequestParam("limit") int limit, @RequestParam("page") int page) {

    try {
      Iterable<Post> posts = service.getAllPosts(limit, page);
      if (posts != null) {
        return new ResponseEntity<>(posts, HttpStatus.OK);
      }
      return new ResponseEntity<>(Util.customMessage("This list is null", 404), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
