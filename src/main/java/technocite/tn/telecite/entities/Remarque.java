package technocite.tn.telecite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name="REMARQUE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Remarque {
	@Id
	@GeneratedValue
	private Long idRemarque;
	private String libelleRemarque;
	@ManyToOne
	@JoinColumn(name="FK_SR_ID")
	private Sprint sprint;
	@ManyToOne
	@JoinColumn(name="FK_ER_ID")
	private Employe employe;

	public Remarque() {
		super();
	}

	public Remarque(Long idRemarque, String libelleRemarque, Sprint sprint, Employe employe) {
		super();
		this.idRemarque = idRemarque;
		this.libelleRemarque = libelleRemarque;
		this.sprint = sprint;
		this.employe = employe;
	}

	public Long getIdRemarque() {
		return idRemarque;
	}

	public void setIdRemarque(Long idRemarque) {
		this.idRemarque = idRemarque;
	}

	public String getLibelleRemarque() {
		return libelleRemarque;
	}

	public void setLibelleRemarque(String libelleRemarque) {
		this.libelleRemarque = libelleRemarque;
	}
	
	@JsonIgnore
	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
@JsonIgnore
	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	

}
