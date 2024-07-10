package com.Nolercoster.certification.bo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Nolercoster.certification.domain.Mail;

@Service
public class MailBO {
	@Autowired
	private JavaMailSender javaMailSender;
	

	
	//난수 생성
	public static String getCertificationCode() {
		Random rand = new Random();
		String str = "";
		for(int i = 0; i < 8; i++) {
			str += rand.nextInt(10);
		}
		return str;
	}
	
	public Mail createMailAndChangePassword(String email, String str) {
		Mail mail = new Mail();
		mail.setAddress(email);
		mail.setTitle("[놀러코스터] 비밀번호 재설정 인증 코드입니다.");
		mail.setMessage("안녕하세요 놀러코스터 비밀번호 재설정 인즈 코드 관련 이메일입니다. 인증코드는 " +
		str + " 입니다.");
		
		return mail;
	}
	
	public void mailSend(Mail mail) {
		System.out.println("전송 완료!");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setSubject(mail.getTitle());
		message.setText(mail.getMessage());
		message.setFrom("chaeykery@naver.com");
		message.setReplyTo("chaeykery@naver.com");
		System.out.println("message" + message);
		javaMailSender.send(message);
	}
	
}
