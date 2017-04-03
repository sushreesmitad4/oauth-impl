package com.omnypay.common.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.omnypay.common.services.crypt.ShahHashEncoder;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.RespSubFieldsDTO;
import com.omnypay.common.services.dto.SecurityQuestionsDTO;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;
import com.omnypay.log.Log4jAdapter;

/**
 * 
 * @author Kirank
 *
 */
public class UserHelper {

	

	
	
	/***
	 * convert From DTO to BO for user 
	 * 
	 * @param userDTO UserDTO object having all details
	 * @return userBO  CloudSvrUser object
	 */
	public static CloudSvrUser convertFromDTOtoBO(UserDTO userDTO) {

		CloudSvrUser userBO = new CloudSvrUser();

		if (userDTO.getPhoneNumber() != null
				&& userDTO.getPhoneNumber().length() != 0) {
			userBO.setMobileNo((userDTO.getPhoneNumber()));
		}
		if (userDTO.getEmailId() != null && userDTO.getEmailId().length() != 0) {
			userBO.setEmailId(userDTO.getEmailId());
		}
		userBO.setImeiNo(userDTO.getImeiNo());

		try {
			userBO.setPassCode(WebServiceUtil.URLDecoderField(ShahHashEncoder
					.encodeShaHash(userDTO.getPasscode())));
		} catch (Exception e) {
			//log.info("Unable to encrypt password :: " + userDTO.getPasscode());
		}

		return userBO;
	}

	/***
	 * convert From DTO to BO for user Login
	 * 
	 * @param loginDTO LoginDTO object having all details
	 * @return userBO  CloudSvrUser object
	 */
	public static CloudSvrUser convertFromDTOtoBOuserLogin(LoginDTO loginDTO) {

		CloudSvrUser userBO = new CloudSvrUser();

		if (loginDTO.getPhoneNumber() != null
				&& loginDTO.getPhoneNumber().length() > 0) {

			userBO.setMobileNo((loginDTO.getPhoneNumber()));

		} else if (loginDTO.getEmailId() != null
				&& loginDTO.getEmailId().length() > 0) {

			userBO.setEmailId(loginDTO.getEmailId());
		}

		userBO.setImeiNo(loginDTO.getImeiNo());

		try {
			userBO.setPassCode(WebServiceUtil.URLDecoderField(ShahHashEncoder
					.encodeShaHash(loginDTO.getPasscode())));
		} catch (Exception e) {
			//log.info("Unable to encrypt password");
		}

		return userBO;
	}

	/***
	 * convert From DTO to BO for UserExist
	 * 
	 * @param loginDTO LoginDTO object having all details
	 * @return userBO  CloudSvrUser object
	 */
	public static CloudSvrUser convertFromDTOtoBOIsUserExist(LoginDTO loginDTO) {
		CloudSvrUser userBO = new CloudSvrUser();

		if (loginDTO.getPhoneNumber() != null
				&& loginDTO.getPhoneNumber().length() > 0) {

			userBO.setMobileNo((loginDTO.getPhoneNumber()));

		}
		if (loginDTO.getEmailId() != null && loginDTO.getEmailId().length() > 0) {

			userBO.setEmailId(loginDTO.getEmailId());
		}

		userBO.setImeiNo(loginDTO.getImeiNo());
		return userBO;
	}

	/***
	 * convert From DTO to BO for imei no
	 * 
	 * @param loginDTO LoginDTO object having all details
	 * @return userBO  CloudSvrUser object
	 */
	public static CloudSvrUser convertFromDTOtoBOForImieno(LoginDTO loginDTO) {

		CloudSvrUser userBO = new CloudSvrUser();

		userBO.setImeiNo(loginDTO.getImeiNo());

		return userBO;
	}

	
	/***
	 * convert From DTO to BO
	 * 
	 * @param baseDTO BaseDTO object having all details
	 * @return userBO  CloudSvrUser object
	 */
	public static CloudSvrUser convertFromDTOtoBO(BaseDTO baseDTO) {

		CloudSvrUser userBO = new CloudSvrUser();

		if (baseDTO.getEmailId() != null && baseDTO.getEmailId().length() > 0) {
			userBO.setEmailId(baseDTO.getEmailId());
		}
		if (baseDTO.getPhoneNumber() != null
				&& baseDTO.getPhoneNumber().length() > 0) {
			userBO.setMobileNo((baseDTO.getPhoneNumber()));
		}		
		userBO.setImeiNo(baseDTO.getImeiNo());

		return userBO;
	}
	/***
	 * converFrom BO List To DTO
	 * 
	 * @param boList List<CloudSvrSecQuesMaster> object
	 * @return respSubFieldsDTOs  List<RespSubFieldsDTO> object
	 */
	public static List<RespSubFieldsDTO> converFromBOListToDTOList(
			List<CloudSvrSecQuesMaster> boList) {

		List<RespSubFieldsDTO> respSubFieldsDTOs = new ArrayList<RespSubFieldsDTO>();

		List<CloudSvrSecQuesMaster> randomList = new ArrayList<CloudSvrSecQuesMaster>();

		if (!boList.isEmpty()) {

			List<Integer> l = UserHelper.nextInt();
			int tmp1 = l.get(0);
			int tmp2 = l.get(1);

			CloudSvrSecQuesMaster random1 = boList.get(tmp1);
			CloudSvrSecQuesMaster random2 = boList.get(tmp2);
			randomList.add(random1);
			randomList.add(random2);
		}

		for (CloudSvrSecQuesMaster secQuestionMaster : randomList) {

			RespSubFieldsDTO respSubFieldsDTO = new RespSubFieldsDTO();
			respSubFieldsDTO.setQuestionId(secQuestionMaster.getId());
			respSubFieldsDTO.setQuestionName(secQuestionMaster.getQuestion());
			respSubFieldsDTOs.add(respSubFieldsDTO);
		}

		return respSubFieldsDTOs;
	}

	/* ---------validations--------------- */
	/***
	 * mandatory field checking 
	 * 
	 * @param obj contain all mandatory parameters
	 * @return true/false
	 */
	public static boolean checkMandatoryFields(Object obj) {

		if (obj instanceof UserDTO) {

			UserDTO userDTO = (UserDTO) obj;

			if (userDTO.getPasscode().trim().isEmpty()) {

				return false;
			}
		}

		return true;
	}

	/***
	 * For registering checking mandatory field 
	 * 
	 * @param obj contain all mandatory parameters
	 * @return true/false
	 */
	public static boolean checkMandatoryFieldsRegistration(Object obj) {

		if (obj instanceof UserDTO) {

			UserDTO userDTO = (UserDTO) obj;

			if (userDTO.getPasscode().trim().isEmpty()) {

				return false;
			}
		}

		return true;
	}

	
	/***
	 * setSecurityQuestions check
	 * 
	 * @param obj contain all mandatory parameters
	 * @return true/false
	 */
	public static boolean checkMandatoryFieldsSecurityQuestions(Object obj) {

		boolean check = true;

		if (obj instanceof SecurityQuestionsDTO) {

			SecurityQuestionsDTO securityQuestionsDTO = (SecurityQuestionsDTO) obj;

			if ((securityQuestionsDTO.getQuestionId1()!=null && securityQuestionsDTO.getQuestionId1().trim().isEmpty())
					|| (securityQuestionsDTO.getQuestionId2()!=null && securityQuestionsDTO.getQuestionId2().trim().isEmpty())
					|| (securityQuestionsDTO.getQuestionId3()!=null&& securityQuestionsDTO.getQuestionId3().trim().isEmpty())
					|| (securityQuestionsDTO.getQuestionId4()!=null&& securityQuestionsDTO.getQuestionId4().trim().isEmpty())
					|| (securityQuestionsDTO.getQuestionId5()!=null && securityQuestionsDTO.getQuestionId5().trim().isEmpty())
					||(securityQuestionsDTO.getAnswer1()!=null && securityQuestionsDTO.getAnswer1().trim().isEmpty())
					|| (securityQuestionsDTO.getAnswer2()!=null && securityQuestionsDTO.getAnswer2().trim().isEmpty())
					|| (securityQuestionsDTO.getAnswer3()!=null && securityQuestionsDTO.getAnswer3().trim().isEmpty())
					|| (securityQuestionsDTO.getAnswer4()!=null && securityQuestionsDTO.getAnswer4().trim().isEmpty())
					|| (securityQuestionsDTO.getAnswer5()!=null && securityQuestionsDTO.getAnswer5().trim().isEmpty())) {

				check = false;
			}
		}

		return check;
	}

	/***
	 * converting dto to bo for security question
	 * 
	 * @param obj contain all mandatory parameters
	 * @return userBO  CloudSvrUser object
	 */
	public static CloudSvrUser convertFromDTOtoBOSecurityQuestions(
			SecurityQuestionsDTO securityQuestionsDTO) {

		List<CloudSvrUsersSecQuestion> listSecQuestion = new ArrayList<CloudSvrUsersSecQuestion>();

		CloudSvrUsersSecQuestion secQuestionBO1 = new CloudSvrUsersSecQuestion();
		CloudSvrUsersSecQuestion secQuestionBO2 = new CloudSvrUsersSecQuestion();
		CloudSvrUsersSecQuestion secQuestionBO3 = new CloudSvrUsersSecQuestion();
		CloudSvrUsersSecQuestion secQuestionBO4 = new CloudSvrUsersSecQuestion();
		CloudSvrUsersSecQuestion secQuestionBO5 = new CloudSvrUsersSecQuestion();

		CloudSvrUser userBO = new CloudSvrUser();

		if (securityQuestionsDTO.getEmailId() != null) {
			userBO.setEmailId(securityQuestionsDTO.getEmailId());
		}
		if (securityQuestionsDTO.getPhoneNumber() != null
				&& securityQuestionsDTO.getPhoneNumber().length() > 0) {

			userBO.setMobileNo((securityQuestionsDTO.getPhoneNumber()));
		}
		if (securityQuestionsDTO.getImeiNo() != null) {
			userBO.setImeiNo(securityQuestionsDTO.getImeiNo());
		}

		if (securityQuestionsDTO.getQuestionId1() != null
				&& securityQuestionsDTO.getQuestionId1().length() > 0) {
			secQuestionBO1.setSecQuesId(new Long(securityQuestionsDTO
					.getQuestionId1()));
		}
		if (securityQuestionsDTO.getAnswer1() != null
				&& securityQuestionsDTO.getAnswer1().length() > 0) {
			secQuestionBO1.setSecQuesAnswer(WebServiceUtil
					.URLDecoderField(securityQuestionsDTO.getAnswer1()));
		}
		if (securityQuestionsDTO.getQuestionId2() != null
				&& securityQuestionsDTO.getQuestionId2().length() > 0) {
			secQuestionBO2.setSecQuesId(new Long(securityQuestionsDTO
					.getQuestionId2()));
		}
		if (securityQuestionsDTO.getAnswer2() != null
				&& securityQuestionsDTO.getAnswer2().length() > 0) {
			secQuestionBO2.setSecQuesAnswer(WebServiceUtil
					.URLDecoderField(securityQuestionsDTO.getAnswer2()));
		}
		if (securityQuestionsDTO.getQuestionId3() != null
				&& securityQuestionsDTO.getQuestionId3().length() > 0) {
			secQuestionBO3.setSecQuesId(new Long(securityQuestionsDTO
					.getQuestionId3()));
		}
		if (securityQuestionsDTO.getAnswer3() != null
				&& securityQuestionsDTO.getAnswer3().length() > 0) {
			secQuestionBO3.setSecQuesAnswer(WebServiceUtil
					.URLDecoderField(securityQuestionsDTO.getAnswer3()));
		}
		if (securityQuestionsDTO.getQuestionId4() != null
				&& securityQuestionsDTO.getQuestionId4().length() > 0) {
			secQuestionBO4.setSecQuesId(new Long(securityQuestionsDTO
					.getQuestionId4()));
		}
		if (securityQuestionsDTO.getAnswer4() != null
				&& securityQuestionsDTO.getAnswer4().length() > 0) {
			secQuestionBO4.setSecQuesAnswer(WebServiceUtil
					.URLDecoderField(securityQuestionsDTO.getAnswer4()));
		}
		if (securityQuestionsDTO.getQuestionId5() != null
				&& securityQuestionsDTO.getQuestionId5().length() > 0) {
			secQuestionBO5.setSecQuesId(new Long(securityQuestionsDTO
					.getQuestionId5()));
		}
		if (securityQuestionsDTO.getAnswer5() != null
				&& securityQuestionsDTO.getAnswer5().length() > 0) {
			secQuestionBO5.setSecQuesAnswer(WebServiceUtil
					.URLDecoderField(securityQuestionsDTO.getAnswer5()));
		}
		listSecQuestion.add(secQuestionBO1);
		listSecQuestion.add(secQuestionBO2);
		listSecQuestion.add(secQuestionBO3);
		listSecQuestion.add(secQuestionBO4);
		listSecQuestion.add(secQuestionBO5);

		userBO.setListSecQuestion(listSecQuestion);

		return userBO;
	}

	
	/***
	 * mandatory field checking for update user
	 * 
	 * @param obj contain all mandatory parameters
	 * @return true/false
	 */
	public static boolean checkMandatoryFieldsUpdateUser(Object obj) {

		if (obj instanceof UpdateUserDTO) {

			UpdateUserDTO updateUserDTO = (UpdateUserDTO) obj;

			if (updateUserDTO.getFirstName().trim().isEmpty()
					|| updateUserDTO.getLastName().trim().isEmpty()
					|| updateUserDTO.getAddress1().trim().isEmpty()
					|| updateUserDTO.getCity().trim().isEmpty()
					|| updateUserDTO.getState().trim().isEmpty()
					|| updateUserDTO.getZipCode().trim().isEmpty()
					|| updateUserDTO.getNewEmailId().trim().isEmpty()

			) {

				return false;
			}
		}

		return true;
	}

	
	/***
	 * convertion DTO to BO for update user profile
	 * 
	 * @param obj contain all mandatory parameters
	 * @return true/false
	 */
	public static CloudSvrUsersProfile convertFromDTOtoBOUpdateUserProf(
			Object object) {

		CloudSvrUsersProfile updateuserBO = commonUse(object);

		if (object instanceof UpdateUserDTO) {

			UpdateUserDTO updateUserDTO = (UpdateUserDTO) object;
			updateuserBO.setAddress1(WebServiceUtil
					.URLDecoderField(updateUserDTO.getAddress1()));
			updateuserBO.setAddress2(WebServiceUtil
					.URLDecoderField(updateUserDTO.getAddress2()));
			updateuserBO.setAddress3(WebServiceUtil
					.URLDecoderField(updateUserDTO.getAddress3()));
			updateuserBO.setFirstName(WebServiceUtil
					.URLDecoderField(updateUserDTO.getFirstName()));
			updateuserBO.setLastName(WebServiceUtil
					.URLDecoderField(updateUserDTO.getLastName()));
			updateuserBO.setCity(WebServiceUtil.URLDecoderField(updateUserDTO
					.getCity()));
			updateuserBO.setState(updateUserDTO.getState());
			updateuserBO.setZipCode(updateUserDTO.getZipCode());
			updateuserBO.setNewEmailId(updateUserDTO.getNewEmailId());
			// setting email

		}
		return updateuserBO;
	}

	/***
	 * convertion DTO to BO for update user profile
	 * 
	 * @param obj contain all mandatory parameters
	 * @return updateuserBO  UpdateUserDTO object
	 */
	public static UpdateUserDTO convertBOUpdateUserProftoFromDTO(Object object,
			CloudSvrUser cloudSvrUser) {

		UpdateUserDTO updateuserBO = new UpdateUserDTO();

		if (object instanceof CloudSvrUsersProfile) {

			CloudSvrUsersProfile updateUser = (CloudSvrUsersProfile) object;

			updateuserBO.setAddress1(updateUser.getAddress1());

			if (updateUser.getAddress2() != null) {

				updateuserBO.setAddress2(updateUser.getAddress2());
			}

			if (updateUser.getAddress3() != null) {

				updateuserBO.setAddress3(updateUser.getAddress3());
			}

			if (updateUser.getFirstName() != null) {

				updateuserBO.setFirstName(updateUser.getFirstName());
			}

			if (updateUser.getLastName() != null) {

				updateuserBO.setLastName(updateUser.getLastName());
			}

			if (updateUser.getCity() != null) {

				updateuserBO.setCity(updateUser.getCity());
			}

			updateuserBO.setState(updateUser.getState());

			updateuserBO.setZipCode(updateUser.getZipCode());

			if (cloudSvrUser.getEmailId() != null) {

				// setting email
				updateuserBO.setEmailId((cloudSvrUser.getEmailId()));
			}

			updateuserBO.setUpdate(true);

		}
		return updateuserBO;
	}


	/***
	 * convert DTO to BO for update user profile
	 * 
	 * @param obj contain all mandatory parameters
	 * @return updateuserBO  CloudSvrUsersProfile object
	 */
	private static CloudSvrUsersProfile commonUse(Object object) {
		CloudSvrUsersProfile updateuserBO = null;

		if (object instanceof UpdateUserDTO) {

			UpdateUserDTO updateUserDTO = (UpdateUserDTO) object;

			updateuserBO = new CloudSvrUsersProfile();

			CloudSvrUser userBO = new CloudSvrUser();

			if (updateUserDTO.getPhoneNumber() != null
					&& updateUserDTO.getPhoneNumber().length() > 0) {
				userBO.setMobileNo((updateUserDTO.getPhoneNumber()));
			}
			if (updateUserDTO.getEmailId() != null
					&& updateUserDTO.getEmailId().length() > 0) {
				userBO.setEmailId(updateUserDTO.getEmailId());
			}
			userBO.setImeiNo(updateUserDTO.getImeiNo());

			// update profile
			updateuserBO.setUser(userBO);
		}
		return updateuserBO;
	}

	
	/***
	 * method use for security question
	 * 
	 * @param 
	 * @return List<Integer>
	 */
	private static List<Integer> nextInt() {
		Random rnd = new Random();
		List<Integer> l = new ArrayList<Integer>();
		for (;;) {
			final int r = rnd.nextInt(5);
			if (!l.contains(r)) {
				l.add(r);
			}
			if (l.size() == 5)
				return l;
		}
	}

}
