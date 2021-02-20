package technocite.tn.telecite.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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
import javax.persistence.Transient;

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
	private Boolean active;
	private Boolean isChecked;
	private String fileName;
	private String token;
	
	private byte[] photo;
	
	@OneToMany(targetEntity = Remarque.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Remarque>remarques;
	
	@ManyToOne
	@JoinColumn(name="FK_Eq_Emp_ID")
	private Equipe equipe;
	@ManyToOne
	@JoinColumn(name="FK_role_Emp_ID")
	private RoleMember role;
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
	
	public Employe(String matricule, String nomEmploye, String prenomEmploye, Date dateNaissance, String email,
			String password, Date dateEmbauche, Float salaire, String post, Boolean active, String fileName,
			byte[] photo) {
		super();
		this.matricule = matricule;
		this.nomEmploye = nomEmploye;
		this.prenomEmploye = prenomEmploye;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
		this.post = post;
		this.active = active;
		this.fileName = fileName;
		this.photo = photo;
	}








	@OneToMany(targetEntity = Message.class,mappedBy = "employe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Message>envoimessages;
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL,mappedBy = "employes" )
	private Set<Conversation> conversations;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="employe_messages",
	joinColumns  ={@JoinColumn(name = "employe_id")},
			inverseJoinColumns={@JoinColumn(name="message_id")})
	private Set<Message> recevoirMessages;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "employes")
    @JsonIgnore
    private Set<Reunion> reunions=new HashSet<>();
	
	public Employe() {
		super();
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

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	
	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	@JsonIgnore
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
	@JsonIgnore
	public List<Postit> getPostits() {
		return postits;
	}
	public void setPostits(List<Postit> postits) {
		this.postits = postits;
	}
	@JsonIgnore
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
	@JsonIgnore
	public List<Message> getEnvoimessages() {
		return envoimessages;
	}
	public void setEnvoimessages(List<Message> envoimessages) {
		this.envoimessages = envoimessages;
	}
	@JsonIgnore
	public Set<Conversation> getConversations() {
		return conversations;
	}
	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}
	@JsonIgnore
	public Set<Message> getRecevoirMessages() {
		return recevoirMessages;
	}
	public void setRecevoirMessages(Set<Message> recevoirMessages) {
		this.recevoirMessages = recevoirMessages;
	}
	@JsonIgnore
	public Set<Reunion> getReunions() {
		return reunions;
	}

	public void setReunions(Set<Reunion> reunions) {
		this.reunions = reunions;
	}

	public RoleMember getRole() {
		return role;
	}

	public void setRole(RoleMember role) {
		this.role = role;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Employe [idEmploye=" + idEmploye + ", matricule=" + matricule + ", nomEmploye=" + nomEmploye
				+ ", prenomEmploye=" + prenomEmploye + ", dateNaissance=" + dateNaissance + ", email=" + email
				+ ", password=" + password + ", dateEmbauche=" + dateEmbauche + ", salaire=" + salaire + ", post="
				+ post + ", active=" + active + ", isChecked=" + isChecked + "]";
	}


	public Employe(Long idEmploye, String matricule, String nomEmploye, String prenomEmploye, Date dateNaissance,
			String email, String password, Date dateEmbauche, Float salaire, String post, Boolean active,
			Boolean isChecked, String fileName, byte[] photo, List<Remarque> remarques, Equipe equipe, RoleMember role,
			Bureau bureau, List<Postit> postits, List<Tache> taches, Departement departement,
			List<Message> envoimessages, Set<Conversation> conversations, Set<Message> recevoirMessages,
			Set<Reunion> reunions) {
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
		this.active = active;
		this.isChecked = isChecked;
		this.fileName = fileName;
		this.photo = photo;
		this.remarques = remarques;
		this.equipe = equipe;
		this.role = role;
		this.bureau = bureau;
		this.postits = postits;
		this.taches = taches;
		this.departement = departement;
		this.envoimessages = envoimessages;
		this.conversations = conversations;
		this.recevoirMessages = recevoirMessages;
		this.reunions = reunions;
	}








	public void addReunion(Reunion reunion) {
		this.reunions.add(reunion);	
	}
	
	@Transient
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
