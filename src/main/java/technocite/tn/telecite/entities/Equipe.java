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
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEquipe;
	private String nomEquipe;
	private String specialite;
	@OneToMany(targetEntity = Employe.class,mappedBy = "equipe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Employe>employes;
	@OneToMany(targetEntity = Projet.class,mappedBy = "equipe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Projet>projets;
	@OneToMany(targetEntity = Notification.class,mappedBy = "equipe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Notification>notifications;
	@OneToMany(targetEntity = Reunion.class,mappedBy = "equipe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Reunion>reunions;
	
	
	public Equipe() {
		super();
	}




	public Equipe(Long idEquipe, String nomEquipe, String specialite, List<Employe> employes, List<Projet> projets,
			List<Notification> notifications, List<Reunion> reunions) {
		super();
		this.idEquipe = idEquipe;
		this.nomEquipe = nomEquipe;
		this.specialite = specialite;
		this.employes = employes;
		this.projets = projets;
		this.notifications = notifications;
		this.reunions= reunions;
	}




	public Long getIdEquipe() {
		return idEquipe;
	}


	public void setIdEquipe(Long idEquipe) {
		this.idEquipe = idEquipe;
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
	@JsonManagedReference
	@JsonIgnore
	public List<Employe> getEmployes() {
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	
	
@JsonManagedReference
	@JsonIgnore
	public List<Projet> getProjets() {
		return projets;
	}




	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
	@JsonManagedReference
	@JsonIgnore
	public List<Notification> getNotifications() {
		return notifications;
	}

	




	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	@JsonManagedReference
	@JsonIgnore
	public List<Reunion> getReunions() {
		return reunions;
	}


	public void setReunions(List<Reunion> reunions) {
		this.reunions = reunions;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEquipe == null) ? 0 : idEquipe.hashCode());
		result = prime * result + ((nomEquipe == null) ? 0 : nomEquipe.hashCode());
		result = prime * result + ((specialite == null) ? 0 : specialite.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipe other = (Equipe) obj;
		if (idEquipe == null) {
			if (other.idEquipe != null)
				return false;
		} else if (!idEquipe.equals(other.idEquipe))
			return false;
		if (nomEquipe == null) {
			if (other.nomEquipe != null)
				return false;
		} else if (!nomEquipe.equals(other.nomEquipe))
			return false;
		if (specialite == null) {
			if (other.specialite != null)
				return false;
		} else if (!specialite.equals(other.specialite))
			return false;
		return true;
	}
	
	
	
	
}