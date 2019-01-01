package com.PersonalBlog.repositories;

import com.PersonalBlog.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
