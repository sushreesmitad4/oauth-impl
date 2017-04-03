package org.omnypay.email.service;

import javax.annotation.Resource;





public class MailThread extends Thread{

	@Resource(name = "emailService")
	private EmailService emailService;

	private MailVO mailVO;
	
	boolean stopFlag = false;
	
	public MailThread(EmailService emailService, MailVO mailVO) {
		this.emailService = emailService;
		this.mailVO = mailVO;
	}
	
	@Override
	public void run() {

		try {
			String [] recipients = mailVO.getRecipients();
			String subject = mailVO.getSubject();
			String message = mailVO.getMessage();
			String language = mailVO.getLanguage();
			if(null != language){
				emailService.postMail(recipients, subject, message, language);
			}else{
				emailService.postMail(recipients, subject, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
