package technocite.tn.telecite.entities;

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
@Table(name="USER_STORY")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class UserStory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userStory;
	private String libelleUserStory;
	private Integer priorite;
	private Integer complexite;
	@ManyToOne
	@JoinColumn(name="FK_SU_ID")
	private Sprint sprint;
	@OneToMany(targetEntity = Tache.class,mappedBy = "userStory",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Tache>taches;
	
	public UserStory() {
		super();
	}

	public UserStory(Long userStory, String libelleUserStory, Integer priorite, Integer complexite, Sprint sprint,
			List<Tache> taches) {
		super();
		this.userStory = userStory;
		this.libelleUserStory = libelleUserStory;
		this.priorite = priorite;
		this.complexite = complexite;
		this.sprint = sprint;
		this.taches = taches;
	}

	public Long getUserStory() {
		return userStory;
	}

	public void setUserStory(Long userStory) {
		this.userStory = userStory;
	}

	public String getLibelleUserStory() {
		return libelleUserStory;
	}

	public void setLibelleUserStory(String libelleUserStory) {
		this.libelleUserStory = libelleUserStory;
	}

	public Integer getPriorite() {
		return priorite;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	public Integer getComplexite() {
		return complexite;
	}

	public void setComplexite(Integer complexite) {
		this.complexite = complexite;
	}
	@JsonIgnore
	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	
}
