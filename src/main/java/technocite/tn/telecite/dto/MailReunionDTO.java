package technocite.tn.telecite.dto;

import java.time.LocalTime;
import java.util.Date;

public class MailReunionDTO {
	private int idReunion;
	private String mail;
	private String nomReunion;
	public String getNomReunion() {
		return nomReunion;
	}
	public void setNomReunion(String nomReunion) {
		this.nomReunion = nomReunion;
	}
	private String nameEmploye;
	private LocalTime heureDeb;
	private Date startDate;
	private String typeReunion;
	

	
	@Override
	public String toString() {
		return "MailReunionDTO [idReunion=" + idReunion + ", mail=" + mail + ", nomReunion=" + nomReunion
				+ ", nameEmploye=" + nameEmploye + ", heureDeb=" + heureDeb + ", startDate=" + startDate
				+ ", typeReunion=" + typeReunion + "]";
	}
	public int getIdReunion() {
		return idReunion;
	}
	public void setIdReunion(int idReunion) {
		this.idReunion = idReunion;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNameEmploye() {
		return nameEmploye;
	}
	public void setNameEmploye(String nameEmploye) {
		this.nameEmploye = nameEmploye;
	}
	public LocalTime getHeureDeb() {
		return heureDeb;
	}
	public void setHeureDeb(LocalTime heureDeb) {
		this.heureDeb = heureDeb;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getTypeReunion() {
		return typeReunion;
	}
	public void setTypeReunion(String typeReunion) {
		this.typeReunion = typeReunion;
	}
	
	
	
}
