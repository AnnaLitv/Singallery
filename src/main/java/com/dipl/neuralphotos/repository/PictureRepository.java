package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Picture;
import com.dipl.neuralphotos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@PersistenceContext
public interface PictureRepository extends JpaRepository<Picture,Long> {
    List<Picture> findAllByUser(User user);
}
