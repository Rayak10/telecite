package technocite.tn.telecite.entities;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="VIDEO")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
@DiscriminatorValue("V")
public class Video extends Message  {
	
	@Lob
	private Byte[] video;

	public Video() {
		super();
	}

	



	public Video( Byte[] video) {
		super();
		this.video = video;
	}


	public Video(Long idMessage, Date dateEnvoi, Date dateLecture, Employe employe, ReunionScrum reunionScrum,Byte[] video) {
		super(idMessage, dateEnvoi, dateLecture, employe, reunionScrum);
this.video=video;
}





	public Byte[] getVideo() {
		return video;
	}

	public void setVideo(Byte[] video) {
		this.video = video;
	}
	
	
}
