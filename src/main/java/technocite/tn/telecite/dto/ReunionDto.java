package technocite.tn.telecite.dto;

import java.util.Date;

import javax.management.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.enums.ReunionType;

@Builder
@ToString
public class ReunionDto {
	private int  idReunion;
	private String nomReunion;
	private String descriptionReunion;
	private Date dateDebut= new Date();
	private Date dateFin= new Date();
	private TimeDTO heureDeb;
	private TimeDTO heureFin;
	private Notification notification;
	private Equipe equipe;
	private ReunionType type;
	public int getIdReunion() {
		return idReunion;
	}
	public void setIdReunion(int idReunion) {
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
	@Override
	public String toString() {
		return "ReunionDto [idReunion=" + idReunion + ", nomReunion=" + nomReunion + ", descriptionReunion="
				+ descriptionReunion + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", heureDeb=" + heureDeb
				+ ", heureFin=" + heureFin + ", type=" + type + "]";
	}
	

}
