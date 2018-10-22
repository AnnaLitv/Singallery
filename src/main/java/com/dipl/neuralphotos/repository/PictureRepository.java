package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
@PersistenceContext
public interface PictureRepository extends JpaRepository<Picture,Long> {
}
