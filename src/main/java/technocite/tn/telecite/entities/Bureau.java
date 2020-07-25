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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="BUREAU")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Bureau {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idBureau;
	private String nomBureau;
	@OneToMany(targetEntity = Employe.class,mappedBy = "bureau",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Employe>employes;
	public Bureau() {
		super();
	}
	public Bureau(Long idBureau, String nomBureau, List<Employe> employes) {
		super();
		this.idBureau = idBureau;
		this.nomBureau = nomBureau;
		this.employes = employes;
	}
	public Long getIdBureau() {
		return idBureau;
	}
	public void setIdBureau(Long idBureau) {
		this.idBureau = idBureau;
	}
	public String getNomBureau() {
		return nomBureau;
	}
	public void setNomBureau(String nomBureau) {
		this.nomBureau = nomBureau;
	}
	public List<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	
}
