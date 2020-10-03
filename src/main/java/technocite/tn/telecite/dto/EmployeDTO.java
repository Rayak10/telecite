package technocite.tn.telecite.dto;

import java.util.Date;

import javax.persistence.Lob;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString

public class EmployeDTO {
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
	private Boolean isChecked;
	@Lob
	private Byte[] photo;

}
