package com.PersonalBlog.repositories;

import com.PersonalBlog.models.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p")
    List<Post> getAllPosts(Pageable page);
}
