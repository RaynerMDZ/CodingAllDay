package com.PersonalBlog.services.implementations;

import com.PersonalBlog.models.Comment;
import com.PersonalBlog.models.Post;
import com.PersonalBlog.repositories.CommentRepository;
import com.PersonalBlog.repositories.PostRepository;
import com.PersonalBlog.repositories.UserRepository;
import com.PersonalBlog.services.CommentService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.PersonalBlog.utils.Util.checkUser;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public Optional<Comment> createComment(ObjectNode node) {

        Date date = new Date();
        String name = node.get("name").asText();
        String body = node.get("body").asText();
        long id = node.get("id").asLong();

        Optional<Post> post = postRepository.findById(id);

        Comment comment = null;

        if (post.isPresent()) {
            comment = new Comment();
            comment.setBody(body);
            comment.setDate(date);
            comment.setName(name);
            comment.setPost(post.get());
        }

        try {
            Comment savedComment = commentRepository.save(comment);
            return Optional.of(savedComment);

        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteComment(ObjectNode node) {

        // Id of the comment coming from the frontend api.
        long commentId = node.get("id").asLong();

        if (checkUser(node, userRepository) != -1) {

            try {
                commentRepository.deleteById(commentId);
                return true;
            } catch (NoClassDefFoundError e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
