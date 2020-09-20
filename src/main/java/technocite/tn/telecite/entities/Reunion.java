package technocite.tn.telecite.entities;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import technocite.tn.telecite.enums.ReunionType;

@Entity
@Table(name="REUNION")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Reunion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReunion;
	private String nomReunion;
	private String descriptionReunion;
	private Date dateDebut;
	private Date dateFin;
	private LocalTime heurDeb; 
	private LocalTime heurFin; 

    @Enumerated(EnumType.STRING)
    private ReunionType type;	
    @OneToOne
	@JoinColumn(name="FK_Notif_Rsc_ID")
	private Notification notification;
	@ManyToOne
	@JoinColumn(name="FK_Eq_Rsc_ID")
	private Equipe equipe;
	@OneToOne
	@JoinColumn(name="FK_Conv_Rsc_ID")
	private Conversation conversation;
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL,mappedBy = "reunionsadministratif" )
	private List<Employe> employes;
	public Reunion() {
		super();
	}
	
	

	public Reunion(Long idReunion, String nomReunion, String descriptionReunion, Date dateDebut,
			Date dateFin, ReunionType type, Notification notification, Equipe equipe, Conversation conversation,LocalTime heurDeb,LocalTime heurFin) {
		super();
		this.idReunion = idReunion;
		this.nomReunion = nomReunion;
		this.descriptionReunion = descriptionReunion;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.notification = notification;
		this.equipe = equipe;
		this.conversation = conversation;
		this.heurDeb=heurDeb;
		this.heurFin=heurFin;
	}
	public Long getIdReunion() {
		return idReunion;
	}
	public void setIdReunion(Long idReunion) {
		this.idReunion = idReunion;
	}
	public String getNomReunion() {
		return nomReunion;
	}
	public void setNomReunion(String nomReunion) {
		this.nomReunion = nomReunion;
	}
	public String getDescriptionReunion() {
		return descriptionReunion;
	}
	public void setDescriptionReunion(String descriptionReunion) {
		this.descriptionReunion = descriptionReunion;
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
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public ReunionType getType() {
		return type;
	}

	public void setType(ReunionType type) {
		this.type = type;
	}
	public LocalTime getHeurDeb() {
		return heurDeb;
	}
	public void setHeurDeb(LocalTime heurDeb) {
		this.heurDeb = heurDeb;
	}
	public LocalTime getHeurFin() {
		return heurFin;
	}
	public void setHeurFin(LocalTime heurFin) {
		this.heurFin = heurFin;
	}



	@Override
	public String toString() {
		return "Reunion [idReunion=" + idReunion + ", nomReunion=" + nomReunion + ", descriptionReunion="
				+ descriptionReunion + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", heurDeb=" + heurDeb
				+ ", heurFin=" + heurFin + ", type=" + type + ", notification=" + notification + ", equipe=" + equipe
				+ ", conversation=" + conversation + ", employes=" + employes + "]";
	}



	
	
	
	
}
