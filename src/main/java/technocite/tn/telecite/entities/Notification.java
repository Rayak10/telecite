package technocite.tn.telecite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="NOTIFICATION")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idNotification;
	private String nomNotification;
	private String libelleNotification;

	@ManyToOne
	@JoinColumn(name="FK_Eq_Notif_ID")
	private Equipe equipe;
	@OneToOne(mappedBy="notification")
	private Reunion reunion;
	
	public Notification() {
		super();
	}

	
	public Notification(Long idNotification, String nomNotification, String libelleNotification, Equipe equipe,
			Reunion reunion) {
		super();
		this.idNotification = idNotification;
		this.nomNotification = nomNotification;
		this.libelleNotification = libelleNotification;
		this.equipe = equipe;
		this.reunion = reunion;
	}


	public Long getIdNotification() {
		return idNotification;
	}


	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
	}


	public String getNomNotification() {
		return nomNotification;
	}


	public void setNomNotification(String nomNotification) {
		this.nomNotification = nomNotification;
	}


	public String getLibelleNotification() {
		return libelleNotification;
	}


	public void setLibelleNotification(String libelleNotification) {
		this.libelleNotification = libelleNotification;
	}


	public Equipe getEquipe() {
		return equipe;
	}


	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}


	public Reunion getReunion() {
		return reunion;
	}


	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	
}
