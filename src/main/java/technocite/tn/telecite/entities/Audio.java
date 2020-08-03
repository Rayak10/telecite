package technocite.tn.telecite.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Audio")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
@DiscriminatorValue("A")
public class Audio extends Message{
	@Lob
	private Byte[] vocale;

	public Audio(Byte[] vocale) {
		super();
		this.vocale = vocale;
	}

	public Audio() {
		super();
	}

	public Audio(Long idMessage, Date dateEnvoi, Date dateLecture, Employe employe, Conversation conversation,
			Set<Employe> employes,Byte[] vocale) {
		super(idMessage, dateEnvoi, dateLecture, employe, conversation, employes);
		this.vocale=vocale;
	}

	public Byte[] getVocale() {
		return vocale;
	}

	public void setVocale(Byte[] vocale) {
		this.vocale = vocale;
	}
	

	
}
