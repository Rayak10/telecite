package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="MESSAGE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString


@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="message_type")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMessage;
	private Date dateEnvoi;
	private Date dateLecture;
	@ManyToOne
	@JoinColumn(name="FK_EM_ID")
	private Employe employe;
	@ManyToOne
	@JoinColumn(name="FK_CM_ID")
	private Conversation conversation;
	
	public Message() {
		super();
	}
	public Message(Long idMessage, Date dateEnvoi, Date dateLecture, Employe employe, Conversation conversation) {
		super();
		this.idMessage = idMessage;
		this.dateEnvoi = dateEnvoi;
		this.dateLecture = dateLecture;
		this.employe = employe;
		this.conversation = conversation;
	}
	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public Date getDateEnvoi() {
		return dateEnvoi;
	}
	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	public Date getDateLecture() {
		return dateLecture;
	}
	public void setDateLecture(Date dateLecture) {
		this.dateLecture = dateLecture;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
}
