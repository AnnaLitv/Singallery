package com.dipl.neuralphotos.repository;

import com.dipl.neuralphotos.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Long,Notification> {
}
