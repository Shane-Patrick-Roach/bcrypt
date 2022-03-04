package com.bcrypt.bcrypt.repository;

import com.bcrypt.bcrypt.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
