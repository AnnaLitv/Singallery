package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
@PersistenceContext
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
