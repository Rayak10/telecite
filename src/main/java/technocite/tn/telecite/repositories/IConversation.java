package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Conversation;

public interface IConversation extends JpaRepository<Conversation, Long> {

}
