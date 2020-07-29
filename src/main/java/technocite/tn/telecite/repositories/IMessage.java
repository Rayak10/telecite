package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Message;

public interface IMessage extends JpaRepository<Message, Long>{

}
