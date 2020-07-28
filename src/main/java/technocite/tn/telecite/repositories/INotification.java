package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Notification;

public interface INotification extends JpaRepository<Notification, Long> {

}
