package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Long,Picture> {
}
