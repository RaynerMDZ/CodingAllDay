package com.PersonalBlog.repositories;

import com.PersonalBlog.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
