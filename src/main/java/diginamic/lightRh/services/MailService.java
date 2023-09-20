package diginamic.lightRh.services;

import java.util.UUID;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import diginamic.lightRh.exceptions.treatmentExceptions.SetTokenException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MailService {

    private final JavaMailSender mailSender;
    private final EmployeeService employeeService;
    
    public void sendRawMail(String emailTo, String subject, String content
	    ) throws MailSendException{
	try {
	    MimeMessage mail = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mail);
	    helper.setTo(emailTo);
	    helper.setSubject(subject);
	    helper.setText(content, true);
	    mailSender.send(mail);
	}
	catch (Exception ex){
	    new MailSendException("mail couldn't be sent to"+emailTo);
	}
    }
    
    @Transactional(rollbackOn = Exception.class)
    public void sendPasswordChangeMail(String emailTo) throws Exception{
	UUID generatedUuid = UUID.randomUUID();
	String htmlContent = 
		"<h1>Your adress asked a Password change</h1>" +
		"<p>To change your password follow this link and set your <b>new password</b></p>" +
		"<a href='http://localhost:4200/setnewpassword?uuid="+generatedUuid+"'>Password change site</a>"    ;
	try {
	    this.sendRawMail(emailTo, "Password reset request", htmlContent);
	    employeeService.setTokenCheck(emailTo, generatedUuid);
	}catch (MailSendException ex) {
	    throw new Exception("Couldn't send mail");
	}catch (SetTokenException ex) {
	    throw new Exception("Couldn't save token to bd");
	}
	
    }
}
