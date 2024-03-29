/**
 * Repository for persistence of Notification entities
 */

package com.notification.system.persistence;

import com.notification.system.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
