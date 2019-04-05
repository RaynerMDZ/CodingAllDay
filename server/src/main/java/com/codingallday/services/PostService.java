package com.codingallday.services;

import com.codingallday.models.Post;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

public interface PostService {

  Optional<Post> createPost(ObjectNode node);
  Optional<Post> editPost(ObjectNode node);
  boolean deletePost(ObjectNode node);
  Optional<Post> getPostById(Long id);
  Iterable<Post> getAllPosts(int limit, int page);
}
