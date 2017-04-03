package com.omnypay.common.services;

import java.util.ArrayList;
import java.util.List;









import org.omnypay.email.service.APCConstants;
import org.omnypay.email.service.EmailService;
import org.omnypay.email.service.MailThread;
import org.omnypay.email.service.MailVO;
import org.omnypay.email.service.PropertiesUtil;

import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.dao.bo.CloudSvrPasswordsHistory;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;

/**
 * 
 * @author iliyasm
 *
 */
public class PasswordHelper {

	
	/***
	 * check Mandatory Fields for password
	 * @param passwordDTO PasswordDTO object having all details
	 * 
	 * 
	 * @return  true/false
	 */
	public static boolean checkMandatoryFields(PasswordDTO passwordDTO) {

		if (passwordDTO.getAnswer1().trim().isEmpty()
				|| passwordDTO.getAnswer2().trim().isEmpty()) {

			return false;
		}
		return true;
	}

	
	/***
	 * for checking old password
	 * @param passwordDTO PasswordDTO object having all details
	 * 
	 * 
	 * @return  true/false
	 */
	public static boolean isOldPasscode(String oldPassword, String OldPasscode) {

		if (oldPassword.equals(OldPasscode)) {

			return true;
		}
		return false;
	}

	
	
	

	/***
	 * for generating random number
	 * 
	 * @param len length of the random number ging to generate
	 * 
	 * @return String  represent random string
	 */
	public static String PickingRandomNumbers(long len) {

		long tLen = (long) Math.pow(10, len - 1) * 9;

		long number = (long) (Math.random() * tLen)
				+ (long) Math.pow(10, len - 1) * 1;

		String tVal = number + "";
		return tVal;
	}
	

	// -----------------------conversions---------------------
	

	/***
	 * convert From DTO to BO
	 * 
	 * @param passwordDTO PasswordDTO  object having all details
	 * 
	 * @return user  CloudSvrUser object having the details
	 */
	public static CloudSvrUser convertFromDTOtoBO(PasswordDTO passwordDTO) {

		CloudSvrUser user = new CloudSvrUser();
		
		
		if (passwordDTO.getAnswer1()!=null && passwordDTO.getAnswer2()!=null){

		// user.setEmailId(passwordDTO.getEmailId());

		if (passwordDTO.getPhoneNumber() != null
				&& passwordDTO.getPhoneNumber().length() > 0) {

			user.setMobileNo((passwordDTO.getPhoneNumber()));

		}
		user.setImeiNo(passwordDTO.getImeiNo());

		// Security Question
		List<CloudSvrUsersSecQuestion> secQuestions = new ArrayList<CloudSvrUsersSecQuestion>();

		CloudSvrUsersSecQuestion secQuestion1 = new CloudSvrUsersSecQuestion();
		secQuestion1.setSecQuesAnswer(WebServiceUtil
				.URLDecoderField(passwordDTO.getAnswer1()));
		secQuestion1.setSecQuesId(Long.parseLong(passwordDTO
				.getSecurityQuestion1()));

		CloudSvrUsersSecQuestion secQuestion2 = new CloudSvrUsersSecQuestion();
		secQuestion2.setSecQuesAnswer(WebServiceUtil
				.URLDecoderField(passwordDTO.getAnswer2()));
		secQuestion2.setSecQuesId(Long.parseLong(passwordDTO
				.getSecurityQuestion2()));

		secQuestions.add(secQuestion1);
		secQuestions.add(secQuestion2);

		user.setListSecQuestion(secQuestions);
		
		} else if (passwordDTO.getNewPassword() != null
				&& passwordDTO.getOldPassword() != null){
			
			
			
			if(passwordDTO.getEmailId() != null && passwordDTO.getEmailId().length()>0){
				user.setEmailId(passwordDTO.getEmailId());
			}
			if(passwordDTO.getPhoneNumber() != null && passwordDTO.getPhoneNumber().length() > 0){
				user.setMobileNo((passwordDTO.getPhoneNumber()));
			}
			if(passwordDTO.getImeiNo() != null && passwordDTO.getImeiNo().length() > 0){
				user.setImeiNo(passwordDTO.getImeiNo());
			}
			
			
			
			
			
		}

		return user;
	}

	
	
	
	
	/***
	 * convert From DTO to BO For Reset Password Validation string
	 * 
	 * 
	 * 
	 * @return accBO  CloudSvrUser represent password val string
	 */
	public static CloudSvrUser convertFromDTOtoBOForResetPassValString() {

		long valStringlength = 15;

		CloudSvrUser accBO = new CloudSvrUser();

		
		accBO.setResetPassValString(PickingRandomNumbers(valStringlength));
		return accBO;
	}

	
	
	
	/***
	 * convert From DTO to BO for ChngePwd
	 * 
	 * @param object having all details
	 * 
	 * @return CloudSvrPasswordsHistory
	 */
	public static CloudSvrPasswordsHistory convertFromDTOtoBOchangePwd(
			Object object) {
		CloudSvrPasswordsHistory pwdchngeBo = commonUse(object);

		if (object instanceof PasswordDTO) {
			PasswordDTO passwordDTO = (PasswordDTO) object;
			
		}
		return pwdchngeBo;
	}
	
	
	
	/***
	 * for sending mail to the user mail id containing a link to reset password
	 * 
	 * @param email user mail
	 * @resetPassValString a string append to the link
	 * @return user  CloudSvrUser object having the details
	 */
	public static void forgotPassword(String email, String resetPassValString) {

		

		MailVO mailVO = null;
		
		EmailService emailService = new EmailService();

		
		try {
			

			String language = "en";

			String[] recipients = new String[] { email };

			
			String programName = PropertiesUtil
					.getMailProperty(APCConstants.PROGRAM_NAME);
			String programSupportEmail = PropertiesUtil
					.getMailProperty(APCConstants.PROGRAM_SUPPORT_EMAIL);
			String programSupportPhone = PropertiesUtil
					.getMailProperty(APCConstants.PROGRAM_SUPPORT_PHONE);
			String signature = PropertiesUtil
					.getMailProperty(APCConstants.SIGNATURE);
			String programWebsite = PropertiesUtil
					.getMailProperty(APCConstants.PROGRAM_WEBSITE);
			String url = PropertiesUtil.getMailProperty(APCConstants.MAIL_URL);
			String resetPasswordUrl = PropertiesUtil
					.getMailProperty(APCConstants.RESET_PASS_URL);
			
			String userNameFull = "Omnypay" + " " + "User";
			

			
			String messageValue[] = { userNameFull, programName, url, url,
					programSupportEmail, programSupportEmail,
					programSupportPhone, signature, programWebsite };
			
			String subject = "Password Reset Request";
			String message = "Hi"
					+ "<br/><br/>"
					+ "Dear Recipient"
					+ ",<br/>"
					+ "We have received your request to reset password."
					+ "<br/>"
					+ "To reset, please click the link below:"
					+ "<br/><br/>"
					+ "<a href=" + resetPasswordUrl + "?resetPassValString="
					+ resetPassValString + ">" + resetPasswordUrl
					+ "?resetPassValString=" + resetPassValString + "</a>"
					+ "<br/><br/>" + "<br/><br/>" + "Regards,"
					+ "<br/>Omnypay Team";
			
			mailVO = new MailVO(recipients, subject, message, language);
			new MailThread(emailService, mailVO).start();

			

		}
		catch (Exception ex) {
			StringBuilder errorBuffer = new StringBuilder();
			
		}
		
	}

	
	/***
	 * Check mandatory fields for ChngePwd
	 * 
	 * @param object having all details
	 * 
	 * @return boolean  true/false
	 */
	public static boolean checkMandatoryFieldsChangePwd(Object object) {
		if (object instanceof PasswordDTO) {
			PasswordDTO passwordDTO = (PasswordDTO) object;

			if (passwordDTO.getOldPassword().trim().isEmpty()
					&& passwordDTO.getNewPassword().trim().isEmpty()) {
				return false;
			}

		}

		return true;

	}

	

	/***
	 * common use method
	 * 
	 * @param object having all details
	 * 
	 * @return CloudSvrPasswordsHistory
	 */
	private static CloudSvrPasswordsHistory commonUse(Object object) {
		CloudSvrPasswordsHistory changePwdBo = null;

		if (object instanceof PasswordDTO) {

			PasswordDTO passwordDTO = (PasswordDTO) object;

			changePwdBo = new CloudSvrPasswordsHistory();

			CloudSvrUser userBO = new CloudSvrUser();

			if (passwordDTO.getPhoneNumber() != null
					&& passwordDTO.getPhoneNumber().length() > 0) {
				userBO.setMobileNo((passwordDTO.getPhoneNumber()));
			} else {
				userBO.setEmailId(passwordDTO.getEmailId());
			}
			userBO.setImeiNo(passwordDTO.getImeiNo());

			// update profile
			changePwdBo.setUser(userBO);
		}
		return changePwdBo;
	}

}