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
@Table(name="POSTIT")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Postit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPostit;
	private String descriptionPostit;
	private Date datePostit;
	@ManyToOne
	@JoinColumn(name="FK_Emp_Post_ID")
	private Employe employe;
	public Postit() {
		super();
	}
	public Postit(Long idPostit, String descriptionPostit, Date datePostit, Employe employe) {
		super();
		this.idPostit = idPostit;
		this.descriptionPostit = descriptionPostit;
		this.datePostit = datePostit;
		this.employe = employe;
	}
	public Long getIdPostit() {
		return idPostit;
	}
	public void setIdPostit(Long idPostit) {
		this.idPostit = idPostit;
	}
	public String getDescriptionPostit() {
		return descriptionPostit;
	}
	public void setDescriptionPostit(String descriptionPostit) {
		this.descriptionPostit = descriptionPostit;
	}
	public Date getDatePostit() {
		return datePostit;
	}
	public void setDatePostit(Date datePostit) {
		this.datePostit = datePostit;
	}
	@JsonIgnore
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
	
	
}
