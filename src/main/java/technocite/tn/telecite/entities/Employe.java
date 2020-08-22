package technocite.tn.telecite.entities;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	private Date  dateNaissance;
	private String email;
	private String password;
	private Date  dateEmbauche;
	private Float salaire;
	private String post;
	private String role;
	private Boolean active;
	@Lob
	private Byte[] photo;
	@OneToMany(targetEntity = Remarque.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Remarque>remarques;
	@ManyToOne
	@JoinColumn(name="FK_Eq_Emp_ID")
	private Equipe equipe;
	@ManyToOne
	@JoinColumn(name="FK_Bur_Emp_ID")
	private Bureau bureau;
	@OneToMany(targetEntity = Postit.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Postit>postits;
	@OneToMany(targetEntity = Tache.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Tache>taches;
	@ManyToOne
	@JoinColumn(name="FK_Dep_Emp_ID")
	private Departement departement;
	@OneToMany(targetEntity = Message.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Message>envoimessages;
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL,mappedBy = "employes" )
	private Set<Conversation> conversations;
	@Override
	public String toString() {
		return "Employe [idEmploye=" + idEmploye + ", matricule=" + matricule + ", nomEmploye=" + nomEmploye
				+ ", prenomEmploye=" + prenomEmploye + ", dateNaissance=" + dateNaissance + ", email=" + email
				+ ", password=" + password + ", dateEmbauche=" + dateEmbauche + ", salaire=" + salaire + ", post="
				+ post + ", role=" + role + ", active=" + active + ", photo=" + Arrays.toString(photo) + ", remarques="
				+ remarques + ", equipe=" + equipe + ", bureau=" + bureau + ", postits=" + postits + ", taches="
				+ taches + ", departement=" + departement + ", envoimessages=" + envoimessages + ", conversations="
				+ conversations + ", recevoirMessages=" + recevoirMessages + "]";
	}
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="employe_messages",
	joinColumns  ={@JoinColumn(name = "employe_id")},
			inverseJoinColumns={@JoinColumn(name="message_id")})
	private Set<Message> recevoirMessages;
	public Employe() {
		super();
	}
	public Employe(Long idEmploye, String matricule, String nomEmploye, String prenomEmploye, Date dateNaissance,
			String email, String password, Date dateEmbauche, Float salaire, String post, String role, Boolean active,
			Byte[] photo, List<Remarque> remarques, Equipe equipe, Bureau bureau, List<Postit> postits,
			List<Tache> taches, Departement departement, List<Message> envoimessages, Set<Conversation> conversations,
			Set<Message> recevoirMessages) {
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
		this.taches = taches;
		this.departement = departement;
		this.envoimessages = envoimessages;
		this.conversations = conversations;
		this.recevoirMessages = recevoirMessages;
	}
	
	public Employe(Long idEmploye, String matricule, String nomEmploye, String prenomEmploye, Date dateNaissance,
			String email, String password, Date dateEmbauche, Float salaire, String post, String role, Boolean active,
			Byte[] photo, Equipe equipe, Bureau bureau, Departement departement) {
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
		this.equipe = equipe;
		this.bureau = bureau;
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
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
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
	
	public List<Tache> getTaches() {
		return taches;
	}
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public List<Message> getEnvoimessages() {
		return envoimessages;
	}
	public void setEnvoimessages(List<Message> envoimessages) {
		this.envoimessages = envoimessages;
	}
	
	public Set<Conversation> getConversations() {
		return conversations;
	}
	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}
	
	public Set<Message> getRecevoirMessages() {
		return recevoirMessages;
	}
	public void setRecevoirMessages(Set<Message> recevoirMessages) {
		this.recevoirMessages = recevoirMessages;
	}

	

	

}
