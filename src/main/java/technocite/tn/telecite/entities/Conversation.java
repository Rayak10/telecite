package technocite.tn.telecite.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	
	public Conversation() {
		super();
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

	
	

}
