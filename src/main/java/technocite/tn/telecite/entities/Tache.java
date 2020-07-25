package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="TACHE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Tache {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTache;
	private String descriptionTache;
	private String etatTache;
	private Integer dureeTache;
	private Date dateDebut;
	private Date dateFin;
	@ManyToOne
	@JoinColumn(name="FK_UT_ID")
	private UserStory userStory;
	@ManyToOne
	@JoinColumn(name="FK_ET_ID")
	private Employe employe;
	
	
	public Tache() {
		super();
	}


	public Tache(Long idTache, String descriptionTache, String etatTache, Integer dureeTache, Date dateDebut,
			Date dateFin, UserStory userStory, Employe employe) {
		super();
		this.idTache = idTache;
		this.descriptionTache = descriptionTache;
		this.etatTache = etatTache;
		this.dureeTache = dureeTache;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.userStory = userStory;
		this.employe = employe;
	}


	public Long getIdTache() {
		return idTache;
	}


	public void setIdTache(Long idTache) {
		this.idTache = idTache;
	}


	public String getDescriptionTache() {
		return descriptionTache;
	}


	public void setDescriptionTache(String descriptionTache) {
		this.descriptionTache = descriptionTache;
	}


	public String getEtatTache() {
		return etatTache;
	}


	public void setEtatTache(String etatTache) {
		this.etatTache = etatTache;
	}


	public Integer getDureeTache() {
		return dureeTache;
	}


	public void setDureeTache(Integer dureeTache) {
		this.dureeTache = dureeTache;
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
	public UserStory getUserStory() {
		return userStory;
	}


	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}

	@JsonIgnore
	public Employe getEmploye() {
		return employe;
	}


	public void setEmploye(Employe employe) {
		this.employe = employe;
	}



}
