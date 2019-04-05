package com.codingallday.services.implementations;

import com.codingallday.models.Post;
import com.codingallday.repositories.PostRepository;
import com.codingallday.repositories.UserRepository;
import com.codingallday.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
    public Optional<Post> createPost(ObjectNode objectNode) {

      Date date = new Date();
      String title = objectNode.get("title").asText();
      String body = objectNode.get("body").asText();
      String featurePhoto = objectNode.get("featurePhoto").asText();

      Post post = new Post();
      post.setTitle(title);
      post.setBody(body);
      post.setDate(date);
      post.setFeaturedPicture(featurePhoto);

      try {
        Post optionalPost = postRepository.save(post);
        return Optional.of(optionalPost);

      } catch (Exception e) {
        e.printStackTrace();
        return Optional.empty();
      }
    }

    /**
     * This method edits a post based on the data provided on the frontend.
     * @param objectNode
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    public Optional<Post> editPost(ObjectNode objectNode) {

        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.convertValue(objectNode.get("post"), Post.class);

        if (post != null) {
          try {
            Post savedPost = postRepository.save(post);
            return Optional.of(savedPost);
          } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
          }
        }
        return Optional.empty();
    }


    /**
     * This parameter deletes a post based on its id.
     * @param node
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    public boolean deletePost(ObjectNode node) {

      // Id of the comment coming from the frontend api.
      long postId = node.get("id").asLong();

      Optional<Post> optionalPost = getPostById(postId);

      if (optionalPost.isPresent()) {
        try {
          postRepository.deleteById(postId);
          return true;

        } catch (NoClassDefFoundError e) {
          e.printStackTrace();
          return false;
        }
      }
      return false;
    }

    /**
     * This method get a post based on its id from the database.
     * @param id
     * @return ResponseEntity
     * @author RaynerMDZ
     */
    public Optional<Post> getPostById(Long id) {
      Optional<Post> optionalPost = postRepository.findById(id);

      if (optionalPost.isPresent()) {
        return optionalPost;
      }

      return Optional.empty();
    }

    /**
     * This method takes a number "limit" and a number "pages" to get all post that will be displayed per page.
     * @param limit
     * @param page
     * @return Iterable<Post>
     * @author RaynerMDZ
     */
    public Iterable<Post> getAllPosts(int limit, int page) {
      try {
        return postRepository.findAll();
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
}
