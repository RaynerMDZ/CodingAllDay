package com.PersonalBlog.controllers;

import com.PersonalBlog.models.Comment;
import com.PersonalBlog.services.CommentService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.PersonalBlog.utils.Util.customMessage;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  /**
   * Creates a new comment based on the post id. It takes a name and the content for the comment that is been created.
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value="/create-comment")
  public ResponseEntity createComment(@Valid @RequestBody ObjectNode objectNode) {

    try {
      Optional<Comment> comment =commentService.createComment(objectNode);

      if (comment.isPresent()) {
        return new ResponseEntity<>(customMessage("Comment created", 200), HttpStatus.OK);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(customMessage("This post does not exist.", 404), HttpStatus.NOT_FOUND);
  }

  /**
   * Deletes a comment based on the id of that comment.
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value="/delete-comment")
  public ResponseEntity deleteComment(@Valid @RequestBody ObjectNode objectNode) {

      try {
        boolean isPresent = commentService.deleteComment(objectNode);

        if (isPresent) {
          return new ResponseEntity<>(customMessage("Comment deleted", 200), HttpStatus.OK);
        }
      } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>(customMessage("This comment does not exist.", 404), HttpStatus.NOT_FOUND);
    }
}
