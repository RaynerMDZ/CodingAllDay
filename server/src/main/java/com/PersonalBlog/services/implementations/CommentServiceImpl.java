package com.PersonalBlog.services.implementations;

import com.PersonalBlog.models.Comment;
import com.PersonalBlog.repositories.CommentRepository;
import com.PersonalBlog.repositories.PostRepository;
import com.PersonalBlog.repositories.UserRepository;
import com.PersonalBlog.services.CommentService;
import jdk.nashorn.internal.ir.ObjectNode;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Comment> createComment(ObjectNode node) {
        return Optional.empty();
    }

    @Override
    public Optional<Comment> deleteComment(ObjectNode node) {
        return Optional.empty();
    }
}
