package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Long,Comment> {
}
