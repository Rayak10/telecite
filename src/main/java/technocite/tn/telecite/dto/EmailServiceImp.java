package technocite.tn.telecite.dto;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IEquipe;
import technocite.tn.telecite.repositories.IReunion;
@Service
public class EmailServiceImp implements IMail{
	@Resource
	public JavaMailSender javaMailSender;
	@Autowired
	private ReunionMailRepository mailRepository;
	@Autowired
	private IMail emailrepository;
	@Autowired
	private IEmploye employeRepository;
	@Override
	public String sendEmail(String to ,String subject ,String text) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
		return "successfully sent email";
	}
	
	@Scheduled(fixedDelay = 24*60*1000)
	public void sendMailConfirmation() {
		String Newligne=System.getProperty("line.separator");
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");

//		
		System.out.println("dddddddddddddddd");
	List<MailReunionDTO> mailReunionDTOs = mailRepository.getAdministrativeReunionMailInfo();
	mailReunionDTOs.forEach(
				mailInfo -> {
					System.out.println("mail to: "+ mailInfo);
						emailrepository.sendEmail(mailInfo.getMail()," "+
								mailInfo.getTypeReunion(),"Bonjour, Nous avons l’honneur de vous convier à une réunion d’information le "+
								formater.format(mailInfo.getStartDate())+" à "+(mailInfo.getHeureDeb().getHour()-1)+"h"+
								(mailInfo.getHeureDeb().getMinute())+"mnt."+Newligne+" Cette rencontre sera l’occasion d’aborder "+
								mailInfo.getNomReunion()+"."+" Espérant vous compter parmi les membres présents, nous vous prions d’agréer,"+
	                             mailInfo.getNameEmploye()+"."+Newligne+
				                  " l’expression de nos sentiments les meilleurs."+Newligne+"lien de reunion: http://localhost:5000/"+Newligne+"clé de session :Session"+ mailInfo.getIdReunion());	
				});
		//Reunion reunion = aloooo 
		//send mail 
	 

}
	@Scheduled(fixedDelay = 24*60*1000)
	public void sendScrumMail() {
		String Newligne=System.getProperty("line.separator");
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");

//		
	List<MailReunionDTO> mailReunionDTOs = mailRepository.getSrumReunionMailInfo();
	mailReunionDTOs.forEach(
				mailInfo -> {
						emailrepository.sendEmail(mailInfo.getMail()," "+
								mailInfo.getTypeReunion(),"Bonjour Nous avons l’honneur de vous convier à une réunion d’information le "+
								formater.format(mailInfo.getStartDate())+" à "+(mailInfo.getHeureDeb().getHour()-1)+"h"+
								(mailInfo.getHeureDeb().getMinute())+"mnt."+Newligne+" Cette rencontre sera l’occasion d’aborder "+
								mailInfo.getNomReunion()+"."+" Espérant vous compter parmi les membres présents, nous vous prions d’agréer,"+
	                             mailInfo.getNameEmploye()+"."+Newligne+
				                  " l’expression de nos sentiments les meilleurs."+Newligne+"lien de reunion: http://localhost:5000/"+Newligne+"clé de session :Session"+ mailInfo.getIdReunion());	
				});
		//Reunion reunion = aloooo 
		//send mail 
	 

}
}
