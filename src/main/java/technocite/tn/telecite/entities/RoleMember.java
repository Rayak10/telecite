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
@Table(name = "role_member")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class RoleMember {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRole;
	private String nomRole;
	@OneToMany(targetEntity = Employe.class,mappedBy = "roleMember",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Employe>employes;
	
	public RoleMember() {
		super();
	}

	public RoleMember(Long idRole, String nomRole) {
		super();
		this.idRole = idRole;
		this.nomRole = nomRole;
	}
	

	public RoleMember(Long idRole, String nomRole, List<Employe> employes) {
		super();
		this.idRole = idRole;
		this.nomRole = nomRole;
		this.employes = employes;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	
	
}
