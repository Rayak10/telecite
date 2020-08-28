package technocite.tn.telecite.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="PROJET")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Projet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  idProjet;
	private String nomProjet;
	private String theme;
	private String description ;
	private String descriptionTechnique;
	private Date dateDebut;
	private Date dateFin ;
	@OneToMany(targetEntity = Sprint.class,mappedBy = "projet",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Sprint>sprints;
	@ManyToOne
	@JoinColumn(name="FK_Equ_Projet_ID")
	private Equipe equipe;
	
	

	public Projet() {
		super();
	}



	



	public Projet(Long idProjet, String nomProjet, String theme, String description, String descriptionTechnique,
			Date dateDebut, Date dateFin, List<Sprint> sprints, Equipe equipe) {
		super();
		this.idProjet = idProjet;
		this.nomProjet = nomProjet;
		this.theme = theme;
		this.description = description;
		this.descriptionTechnique = descriptionTechnique;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.sprints = sprints;
		this.equipe = equipe;
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


	@JsonIgnore
	public List<Sprint> getSprints() {
		return sprints;
	}



	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}



	public Equipe getEquipe() {
		return equipe;
	}



	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	

	
	
}
