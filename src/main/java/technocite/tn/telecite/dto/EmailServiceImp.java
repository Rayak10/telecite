package technocite.tn.telecite.dto;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceImp implements IMail{
	@Resource
	public JavaMailSender javaMailSender;
	
	@Override
	public String sendEmail(String to ,String subject ,String text) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
		return "successfully sent email";
	}

}
