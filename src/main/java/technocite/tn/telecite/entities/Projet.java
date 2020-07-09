package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PROJET")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Projet {
	@Id
	@GeneratedValue
	private Long  idProjet;
	private String nomProjet;
	private String theme;
	private String description ;
	private String descriptionTechnique;
	private Date dateDebut;
	private Date dateFin ;
	
	
	
	
	public Projet() {
		super();
	}

	public Projet(Long idProjet, String nomProjet, String theme, String description, String descriptionTechnique,
			Date dateDebut, Date dateFin) {
		super();
		this.idProjet = idProjet;
		this.nomProjet = nomProjet;
		this.theme = theme;
		this.description = description;
		this.descriptionTechnique = descriptionTechnique;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionTechnique() {
		return descriptionTechnique;
	}

	public void setDescriptionTechnique(String descriptionTechnique) {
		this.descriptionTechnique = descriptionTechnique;
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

	
	
}
