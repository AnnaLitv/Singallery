package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
@PersistenceContext
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
