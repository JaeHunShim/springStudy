package com.overware.spring;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mail")
@Controller
public class MailController {
	
	@Inject
	private JavaMailSender mailSender;
	
	@RequestMapping("/mailForm")
	public String mailForm() {
		
		return "mail";
	}
	@RequestMapping("/mailSending")
	public String mailSending(HttpServletRequest request) {
		
		String setfrom ="jaehuniya@gmail.com";
		String tomail = request.getParameter("tomail");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		try {
		      MimeMessage message = mailSender.createMimeMessage();
		      MimeMessageHelper messageHelper 
		                        = new MimeMessageHelper(message, true, "UTF-8");
		 
		      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
		      messageHelper.setTo(tomail);     // 받는사람 이메일
		      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
		      messageHelper.setText(content);  // 메일 내용
		     
		      mailSender.send(message);
		    } catch(Exception e){
		      System.out.println(e);
		    }
		   
		    return "redirect:/mail/mailForm";
	}
}
