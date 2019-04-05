package com.PersonalBlog.services;

import com.PersonalBlog.models.Comment;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

public interface CommentService {

  Optional<Comment> createComment(ObjectNode node);
  boolean deleteComment(ObjectNode node);
}
