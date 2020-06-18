package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EMPLOYE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Employe {
	@Id
	@GeneratedValue
	private Long idEmploye;
	private String matricule;
	private String nomEmploye;
	private String PrenomEmploye;
	private Date dateNaissance;
	private String email;
	private String password;
	private Date dateEmbauche;
	private Float salaire;
	private String post;
	private String role;
	private Boolean etatEmploye;
	@Lob
	private Byte[] photo;
	
	
	
	public Employe() {
		super();
	}
	public Employe(Long idEmploye, String matricule, String nomEmploye, String prenomEmploye, Date dateNaissance,
			String email, String password, Date dateEmbauche, Float salaire, String post, String role,
			Boolean etatEmploye, Byte[] photo) {
		super();
		this.idEmploye = idEmploye;
		this.matricule = matricule;
		this.nomEmploye = nomEmploye;
		PrenomEmploye = prenomEmploye;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
		this.post = post;
		this.role = role;
		this.etatEmploye = etatEmploye;
		this.photo = photo;
	}
	public Long getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNomEmploye() {
		return nomEmploye;
	}
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}
	public String getPrenomEmploye() {
		return PrenomEmploye;
	}
	public void setPrenomEmploye(String prenomEmploye) {
		PrenomEmploye = prenomEmploye;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateEmbauche() {
		return dateEmbauche;
	}
	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	public Float getSalaire() {
		return salaire;
	}
	public void setSalaire(Float salaire) {
		this.salaire = salaire;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getEtatEmploye() {
		return etatEmploye;
	}
	public void setEtatEmploye(Boolean etatEmploye) {
		this.etatEmploye = etatEmploye;
	}
	public Byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}
	

}
