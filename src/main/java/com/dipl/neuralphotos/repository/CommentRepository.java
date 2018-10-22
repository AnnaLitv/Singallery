package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
@PersistenceContext
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
