package technocite.tn.telecite.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="DEPARTEMENT")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Departement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDepartement;
	private String nomDepartement;
	@OneToMany(targetEntity = Employe.class,mappedBy = "departement",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Employe>employes;
	public Departement() {
		super();
	}
	public Departement(Long idDepartement, String nomDepartement, List<Employe> employes) {
		super();
		this.idDepartement = idDepartement;
		this.nomDepartement = nomDepartement;
		this.employes = employes;
	}
	public Long getIdDepartement() {
		return idDepartement;
	}
	public void setIdDepartement(Long idDepartement) {
		this.idDepartement = idDepartement;
	}
	public String getNomDepartement() {
		return nomDepartement;
	}
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	@JsonIgnore
	public List<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	
	
}
