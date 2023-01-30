package com.agniva.blog.repositories;

import com.agniva.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface CommentRepo extends JpaRepository<Comment, Integer> {


}
