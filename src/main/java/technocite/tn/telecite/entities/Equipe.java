package technocite.tn.telecite.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="EQUIPE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Equipe {
	@Id
	@GeneratedValue
	private Long idTache;
	private String nomEquipe;
	private String specialite;
	@OneToMany(targetEntity = Employe.class,mappedBy = "equipe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Employe>employes;
	@OneToOne
	@JoinColumn(name="FK_PE_ID")
	private Projet projet;
	
	public Equipe() {
		super();
	}

	public Equipe(Long idTache, String nomEquipe, String specialite, List<Employe> employes, Projet projet) {
		super();
		this.idTache = idTache;
		this.nomEquipe = nomEquipe;
		this.specialite = specialite;
		this.employes = employes;
		this.projet = projet;
	}

	public Long getIdTache() {
		return idTache;
	}

	public void setIdTache(Long idTache) {
		this.idTache = idTache;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
@JsonIgnore
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	


}

