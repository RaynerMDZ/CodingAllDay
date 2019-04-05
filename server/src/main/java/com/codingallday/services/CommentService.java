package com.codingallday.services;

import com.codingallday.models.Comment;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

public interface CommentService {

  Optional<Comment> createComment(ObjectNode node);
  boolean deleteComment(ObjectNode node);
}
