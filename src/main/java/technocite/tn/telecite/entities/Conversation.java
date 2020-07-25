package technocite.tn.telecite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
