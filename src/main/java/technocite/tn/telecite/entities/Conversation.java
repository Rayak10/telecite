package technocite.tn.telecite.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="CONVERSATION")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Conversation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idConversation;
	private String nomConversation;
	@OneToMany(targetEntity = Message.class,mappedBy = "conversation",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List <Message>messages;

	@OneToOne(mappedBy="conversation")
	private ReunionScrum reunionScrum;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="conv_messages",
	joinColumns  ={@JoinColumn(name = "conversation_id")},
			inverseJoinColumns={@JoinColumn(name="message_id")})
	private Set<Employe> employes;
	public Conversation() {
		super();
	}
	public Conversation(Long idConversation, String nomConversation, List<Message> messages, ReunionScrum reunionScrum,
			Set<Employe> employes) {
		super();
		this.idConversation = idConversation;
		this.nomConversation = nomConversation;
		this.messages = messages;
		this.reunionScrum = reunionScrum;
		this.employes = employes;
	}
	public Long getIdConversation() {
		return idConversation;
	}
	public void setIdConversation(Long idConversation) {
		this.idConversation = idConversation;
	}
	public String getNomConversation() {
		return nomConversation;
	}
	public void setNomConversation(String nomConversation) {
		this.nomConversation = nomConversation;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public ReunionScrum getReunionScrum() {
		return reunionScrum;
	}
	public void setReunionScrum(ReunionScrum reunionScrum) {
		this.reunionScrum = reunionScrum;
	}
	public Set<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}

	

	

	

}
