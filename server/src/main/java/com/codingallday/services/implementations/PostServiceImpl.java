package com.codingallday.services.implementations;

import com.codingallday.models.Post;
import com.codingallday.repositories.PostRepository;
import com.codingallday.repositories.UserRepository;
import com.codingallday.services.PostService;
import com.codingallday.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * This class contains all restful services Posts.
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method creates a post based on the data sent through the frontend.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @PostMapping(value="/create-post")
    public ResponseEntity createPost(@Valid @RequestBody ObjectNode objectNode) {

        Date date = new Date();

        String title = objectNode.get("title").asText();
        String body = objectNode.get("body").asText();
        String featurePhoto = objectNode.get("featurePhoto").asText();

        if (Util.checkUser(objectNode, userRepository) != -1) {
            Post post = new Post();

            post.setTitle(title);
            post.setBody(body);
            post.setDate(date);
            post.setFeaturedPicture(featurePhoto);

            try {
                postRepository.save(post);
                return new ResponseEntity<>(Util.customMessage("post created", 200), HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(Util.customMessage("You don't have permission to create a post.", 401), HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method edits a post based on the data provided on the frontend.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/editPost")
    public ResponseEntity editPost(@Valid @RequestBody ObjectNode objectNode) {

        ObjectMapper mapper = new ObjectMapper();
        Post myPost = mapper.convertValue(objectNode.get("post"), Post.class);

        if (Util.checkUser(objectNode, userRepository) != -1) {

            try {
                postRepository.save(myPost);
                return new ResponseEntity<>(Util.customMessage("post updated", 200), HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(Util.customMessage("You don't have permission to create a post.", 401), HttpStatus.UNAUTHORIZED);
    }


    /**
     * This parameter deletes a post based on its id.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/deletePost")
    public ResponseEntity deletePost(@Valid @RequestBody ObjectNode objectNode) {

        long postId = objectNode.get("postId").asLong();

        if (Util.checkUser(objectNode, userRepository) != -1) {

            try {
                postRepository.deleteById(postId);
                return new ResponseEntity<>(Util.customMessage("post deleted", 200), HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(Util.customMessage("You don't have permission to delete a post.", 401), HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method get a post based on its id from the database.
     * @param id
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getPost/{id}")
    public ResponseEntity getPostById(@PathVariable Long id) {

        Post post;

        try {
            post = postRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (post == null) {
            return new ResponseEntity<>(Util.customMessage("This post cannot be found", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * This method takes a number "limit" and a number "pages" to get all post that will be displayed per page.
     * @param limit
     * @param page
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getPosts")
    public ResponseEntity getAllPosts(@RequestParam("limit") int limit, @RequestParam("page") int page) {

        Iterable<Post> posts;

        try {
            posts = postRepository.getAllPosts(new PageRequest(page - 1,limit, Sort.Direction.DESC, "date"));
        } catch (Exception e) {
            return new ResponseEntity<>(Util.customMessage(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
