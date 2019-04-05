package com.PersonalBlog.services;

import com.PersonalBlog.models.Comment;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommentService {

  Optional<Comment> createComment(ObjectNode node);
  Optional<Comment> deleteComment(ObjectNode node);
}
