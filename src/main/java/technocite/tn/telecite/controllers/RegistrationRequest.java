package technocite.tn.telecite.controllers;



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import technocite.tn.telecite.entities.Bureau;
import technocite.tn.telecite.entities.Conversation;
import technocite.tn.telecite.entities.Departement;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Message;
import technocite.tn.telecite.entities.Postit;
import technocite.tn.telecite.entities.Remarque;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.RoleMember;
import technocite.tn.telecite.entities.Tache;


	@Data
	public class RegistrationRequest {

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
		private Boolean isChecked;
		@Lob
		private Byte[] photo;
		
		private List<Remarque>remarques;
		
	
		private Equipe equipe;
		
		private RoleMember roleMember;
	
		private Bureau bureau;
		
		private List<Postit>postits;
		
		private List<Tache>taches;
	
		private Departement departement;
		
		private List<Message>envoimessages;
		private Set<Conversation> conversations;
	
		private Set<Message> recevoirMessages;
		
	    private Set<Reunion> reunions=new HashSet<>();

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

		public Boolean getIsChecked() {
			return isChecked;
		}

		public void setIsChecked(Boolean isChecked) {
			this.isChecked = isChecked;
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

		public RoleMember getRoleMember() {
			return roleMember;
		}

		public void setRoleMember(RoleMember roleMember) {
			this.roleMember = roleMember;
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

		public Set<Reunion> getReunions() {
			return reunions;
		}

		public void setReunions(Set<Reunion> reunions) {
			this.reunions = reunions;
		}
		
		
	    
	}


