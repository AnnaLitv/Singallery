package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long,User> {
}
