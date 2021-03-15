package technocite.tn.telecite.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.management.Notification;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.enums.ReunionType;

@Builder
@ToString
@Setter
@Getter
public class ReunionDto {
	private Long  idReunion;
	private String nomReunion;
	private String descriptionReunion;
	private Date dateDebut= new Date();
	private Date dateFin= new Date();
	private TimeDTO heureDeb;
	private TimeDTO heureFin;
	private LocalTime heurDeb; 
	private LocalTime heurFin; 
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



	private String session;
	private Notification notification;
	private Equipe equipe;
	private Set<Long> employes;
   



	
	public Set<Long> getEmployes() {
		return employes;
	}
	public void setEmployes(Set<Long> employes) {
		this.employes = employes;
	}



	private ReunionType type;
	
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
	public TimeDTO getHeureDeb() {
		return heureDeb;
	}
	public void setHeureDeb(TimeDTO heureDeb) {
		this.heureDeb = heureDeb;
	}
	public TimeDTO getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(TimeDTO heureFin) {
		this.heureFin = heureFin;
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
	public ReunionType getType() {
		return type;
	}
	public void setType(ReunionType type) {
		this.type = type;
	}
	
	
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	@Override
	public String toString() {
		return "ReunionDto [idReunion=" + idReunion + ", nomReunion=" + nomReunion + ", descriptionReunion="
				+ descriptionReunion + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", heureDeb=" + heureDeb
				+ ", heureFin=" + heureFin + ", notification=" + notification + ", equipe=" + equipe
				+ ", employesReunion=" + employes + ", type=" + type + "]";
	}
	
	

}
