package com.PersonalBlog.controllers;

import com.PersonalBlog.models.Comment;
import com.PersonalBlog.models.Post;
import com.PersonalBlog.services.CommentService;
import com.PersonalBlog.services.PostService;
import com.PersonalBlog.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static com.PersonalBlog.utils.Util.checkUser;
import static com.PersonalBlog.utils.Util.customMessage;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;
  private final PostService postService;
  private final UserService userService;

  public CommentController(CommentService commentService, PostService postService, UserService userService) {
    this.commentService = commentService;
    this.postService = postService;
    this.userService = userService;
  }

  /**
   * Creates a new comment based on the post id. It takes a name and the content for the comment that is been created.
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @PostMapping(value="/create-comment")
  public ResponseEntity createComment(@Valid @RequestBody ObjectNode objectNode) {

    Date date = new Date();
    String name = objectNode.get("name").asText();
    String body = objectNode.get("body").asText();
    long id = objectNode.get("id").asLong();

    Post post = postRepository.findById(id).orElse(null);

    if (post != null) {
      Comment comment = new Comment();

      comment.setBody(body);
      comment.setDate(date);
      comment.setName(name);
      comment.setPost(post);

      try {
        commentRepository.save(comment);
        return new ResponseEntity<>(customMessage("Comment created", 200), HttpStatus.OK);

      } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(customMessage("This post does not exist.", 404), HttpStatus.NOT_FOUND);
  }

  /**
   * Deletes a comment based on the id of that comment.
   * @param objectNode
   * @return ResponseEntity
   * @author RaynerMDZ
   */
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping(value="/deleteComment")
  public ResponseEntity deleteComment(@Valid @RequestBody ObjectNode objectNode) {

    // Id of the comment coming from the frontend api.
    long commentId = objectNode.get("id").asLong();

    if (checkUser(objectNode, userRepository) != -1) {

      try {
        commentRepository.deleteById(commentId);
        return new ResponseEntity<>(customMessage("Comment deleted", 200), HttpStatus.OK);

      } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(customMessage("This comment does not exist.", 404), HttpStatus.NOT_FOUND);
  }
}
