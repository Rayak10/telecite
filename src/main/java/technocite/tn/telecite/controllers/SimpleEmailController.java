package technocite.tn.telecite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/email")
public class SimpleEmailController {
  
	@Autowired
	public JavaMailSender javaMailSender;
	
	@GetMapping(value = "/sendEmail")
	public String sendEmail() {
		
		SimpleMailMessage message=new SimpleMailMessage();
	message.setTo("radhwen.yak@gmail.com,testradhwen@gmail.com");
	message.setSubject("SpringBootApplication");
	message.setText("Hi How are you");
	javaMailSender.send(message);
	return "successfully sent email";
	}
	
	
	
}
