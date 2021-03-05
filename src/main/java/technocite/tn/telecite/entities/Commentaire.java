package technocite.tn.telecite.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="COMMENTAIRE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Commentaire {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCommentaire;
	private String libelleCommentaire;
	private Date dateCommentaire;
	@ManyToOne
	@JoinColumn(name="FK_Sprint_Cmt_ID")
	private Sprint sprint;
	@ManyToOne
	@JoinColumn(name="FK_Emp_Cmt_ID")
	private Employe employe;
	
	

	public Commentaire() {
		super();
	}

	public Commentaire(Long idCommentaire, String libelleCommentaire, Date dateCommentaire, Sprint sprint,
			Employe employe) {
		super();
		this.idCommentaire = idCommentaire;
		this.libelleCommentaire = libelleCommentaire;
		this.dateCommentaire = dateCommentaire;
		this.sprint = sprint;
		this.employe = employe;
	}

	public Long getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(Long idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getLibelleCommentaire() {
		return libelleCommentaire;
	}

	public void setLibelleCommentaire(String libelleCommentaire) {
		this.libelleCommentaire = libelleCommentaire;
	}
	

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}
	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	
}
