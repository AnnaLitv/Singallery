package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Long,Avatar> {
}
