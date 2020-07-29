package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="REUNIONSCRUM")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class ReunionScrum {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReunionScrum;
	private String nomReunionScrum;
	private String descriptionReunionScrum;
	private Date dateDebut;
	private Date dateFin;
	@OneToOne
	@JoinColumn(name="FK_NR_ID")
	private Notification notification;
	@ManyToOne
	@JoinColumn(name="FK_ER_ID")
	private Equipe equipe;
	@OneToOne
	@JoinColumn(name="FK_CR_ID")
	private Conversation conversation;
	
	public ReunionScrum() {
		super();
	}
	
	public ReunionScrum(Long idReunionScrum, String nomReunionScrum, String descriptionReunionScrum, Date dateDebut,
			Date dateFin, Notification notification, Equipe equipe,Conversation conversation) {
		super();
		this.idReunionScrum = idReunionScrum;
		this.nomReunionScrum = nomReunionScrum;
		this.descriptionReunionScrum = descriptionReunionScrum;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.notification = notification;
		this.equipe = equipe;
		this.conversation=conversation;
	}

	public Long getIdReunionScrum() {
		return idReunionScrum;
	}
	public void setIdReunionScrum(Long idReunionScrum) {
		this.idReunionScrum = idReunionScrum;
	}
	public String getNomReunionScrum() {
		return nomReunionScrum;
	}
	public void setNomReunionScrum(String nomReunionScrum) {
		this.nomReunionScrum = nomReunionScrum;
	}
	public String getDescriptionReunionScrum() {
		return descriptionReunionScrum;
	}
	public void setDescriptionReunionScrum(String descriptionReunionScrum) {
		this.descriptionReunionScrum = descriptionReunionScrum;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	@JsonIgnore
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	@JsonIgnore
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	@JsonIgnore
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}



	
	
	
	
}
