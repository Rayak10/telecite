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
@Table(name="SPRINT")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Sprint {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSprint;
	private String nomSprint;
	private Integer numeroSprint;
	private Date dateDebut;
	private Date dateFin;
	private String etatSprint;
	
	@ManyToOne
	@JoinColumn(name="FK_Prj_Sprint_ID")
	private Projet projet;
	@OneToMany(targetEntity = UserStory.class,mappedBy = "sprint",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<UserStory>userstorys;

	@OneToMany(targetEntity = Remarque.class,mappedBy = "sprint",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Remarque>remarques;

	public Sprint() {
		super();
	}

	public Sprint(Long idSprint, String nomSprint, Integer numeroSprint, Date dateDebut, Date dateFin,
			String etatSprint, Projet projet, List<UserStory> userstorys, List<Remarque> remarques) {
		super();
		this.idSprint = idSprint;
		this.nomSprint = nomSprint;
		this.numeroSprint = numeroSprint;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etatSprint = etatSprint;
		this.projet = projet;
		this.userstorys = userstorys;
		this.remarques = remarques;
	}

	public Long getIdSprint() {
		return idSprint;
	}

	public void setIdSprint(Long idSprint) {
		this.idSprint = idSprint;
	}

	public String getNomSprint() {
		return nomSprint;
	}

	public void setNomSprint(String nomSprint) {
		this.nomSprint = nomSprint;
	}

	public Integer getNumeroSprint() {
		return numeroSprint;
	}

	public void setNumeroSprint(Integer numeroSprint) {
		this.numeroSprint = numeroSprint;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getEtatSprint() {
		return etatSprint;
	}

	public void setEtatSprint(String etatSprint) {
		this.etatSprint = etatSprint;
	}
	@JsonIgnore
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public List<UserStory> getUserstorys() {
		return userstorys;
	}

	public void setUserstorys(List<UserStory> userstorys) {
		this.userstorys = userstorys;
	}

	public List<Remarque> getRemarques() {
		return remarques;
	}

	public void setRemarques(List<Remarque> remarques) {
		this.remarques = remarques;
	}


	
}
