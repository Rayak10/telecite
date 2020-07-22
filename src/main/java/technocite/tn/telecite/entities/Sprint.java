package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SPRINT")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Sprint {
	@Id
	@GeneratedValue
	private Long idSprint;
	private String nomSprint;
	private Integer numeroSprint;
	private Date dateDebut;
	private Date dateFin;
	private String etat;
	
	@ManyToOne
	@JoinColumn(name="FK_PS_ID")
	private Projet projet;

	public Sprint() {
		super();
	}


	





	public Sprint(Long idSprint, String nomSprint, Integer numeroSprint, Date dateDebut, Date dateFin, String etat,
			 Projet projet  ) {
		super();
		this.idSprint = idSprint;
		this.nomSprint = nomSprint;
		this.numeroSprint = numeroSprint;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	
		this.projet = projet;
	
	}








	public Long getIdSprint() {
		return idSprint;
	}


	public void setIdSprint(Long idSprint) {
		this.idSprint = idSprint;
	}


	public String getNomSprint() {
		return nomSprint;
	}


	public void setNomSprint(String nomSprint) {
		this.nomSprint = nomSprint;
	}


	public Integer getNumeroSprint() {
		return numeroSprint;
	}


	public void setNumeroSprint(Integer numeroSprint) {
		this.numeroSprint = numeroSprint;
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


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}

@JsonIgnore
	public Projet getProjet() {
		return projet;
	}




	public void setProjet(Projet projet) {
		this.projet = projet;
	}




	
	
	
}
