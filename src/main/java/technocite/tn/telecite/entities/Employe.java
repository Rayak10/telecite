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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="EMPLOYE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Employe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmploye;
	private String matricule;
	private String nomEmploye;
	private String prenomEmploye;
	private Date dateNaissance;
	private String email;
	private String password;
	private Date dateEmbauche;
	private Float salaire;
	private String post;
	private String role;
	private Boolean active;
	@Lob
	private Byte[] photo;
	@OneToMany(targetEntity = Remarque.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Remarque>remarques;
	@ManyToOne
	@JoinColumn(name="FK_EE_ID")
	private Equipe equipe;
	@ManyToOne
	@JoinColumn(name="FK_BE_ID")
	private Bureau bureau;
	@OneToMany(targetEntity = Postit.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Postit>postits;
	@ManyToOne
	@JoinColumn(name="FK_DE_ID")
	private Departement departement;
	@OneToMany(targetEntity = Message.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Message>messages;
	
	public Employe() {
		super();
	}

	public Employe(Long idEmploye, String matricule, String nomEmploye, String prenomEmploye, Date dateNaissance,
			String email, String password, Date dateEmbauche, Float salaire, String post, String role, Boolean active,
			Byte[] photo, List<Remarque> remarques, Equipe equipe, Bureau bureau, List<Postit> postits,
			Departement departement) {
		super();
		this.idEmploye = idEmploye;
		this.matricule = matricule;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
		this.post = post;
		this.role = role;
		this.active = active;
		this.photo = photo;
		this.remarques = remarques;
		this.equipe = equipe;
		this.bureau = bureau;
		this.postits = postits;
		this.departement = departement;
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
		return prenomEmploye;
	}

	public void setPrenomEmploye(String prenomEmploye) {
		this.prenomEmploye = prenomEmploye;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}

	public List<Remarque> getRemarques() {
		return remarques;
	}

	public void setRemarques(List<Remarque> remarques) {
		this.remarques = remarques;
	}
	@JsonIgnore
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	@JsonIgnore
	public Bureau getBureau() {
		return bureau;
	}

	public void setBureau(Bureau bureau) {
		this.bureau = bureau;
	}

	public List<Postit> getPostits() {
		return postits;
	}

	public void setPostits(List<Postit> postits) {
		this.postits = postits;
	}
	@JsonIgnore
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	

	
	

}
