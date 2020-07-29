package technocite.tn.telecite.entities;



import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name="TEXT")
@DiscriminatorValue("T")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Text extends Message {
	
	private String text;

	public Text() {
		super();
	}

	

	public Text(String text) {
		super();
		this.text = text;
	}
	
	

	


	public Text(Long idMessage, Date dateEnvoi, Date dateLecture, Employe employe, Conversation conversation,String text) {
		super(idMessage, dateEnvoi, dateLecture, employe, conversation);
this.text=text;	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
