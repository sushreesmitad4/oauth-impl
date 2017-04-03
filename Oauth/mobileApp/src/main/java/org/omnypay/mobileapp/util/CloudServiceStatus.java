/**
 * 
 */
package org.omnypay.mobileapp.util;

import javax.ws.rs.core.Response;













import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.CouponSubfieldDTO;
import com.omnypay.common.services.dto.CustomerRespDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;
import com.omnypay.log.ErrorCodeConstants;

/**
 * @author iliyasm
 *
 */
public class CloudServiceStatus {
	
	
	
	
	
	
	
	
	public static Status getStatusByException(CloudServiceException cloudServiceException ){
		
		
		Status status =null;
		
		if (ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY
				.equals(cloudServiceException.getErrorCode())) {

			status = accessKeyNull(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

		
			
		} else if (ErrorCodeConstants.GET_USER.equals(cloudServiceException
				.getErrorCode())) {

			status = userNull(ErrorCodeConstants.GET_USER);

		
		} else if (ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS);

			
		} else if (com.omnypay.log.ErrorCodeConstants.UPDATE_PASS_VAL_STRING
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.UPDATE_PASS_VAL_STRING);

		} else if (com.omnypay.log.ErrorCodeConstants.LAST_FIVE_PASSWORD_MATCH
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.LAST_FIVE_PASSWORD_MATCH);

		} else if (com.omnypay.log.ErrorCodeConstants.USER_CHANGE_PASSWORD
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_CHANGE_PASSWORD);

		} else if (com.omnypay.log.ErrorCodeConstants.TRANSACTION_INITIATE_AMOUNT_NOT
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.TRANSACTION_INITIATE_AMOUNT_NOT);

		} else if (com.omnypay.log.ErrorCodeConstants.TRANSACTION_PROCESS_NOT
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.TRANSACTION_PROCESS_NOT);

		} else if (com.omnypay.log.ErrorCodeConstants.PARSE_EXCEPTION_DURING_SENDTOSWITCH
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.PARSE_EXCEPTION_DURING_SENDTOSWITCH);

		} else if (com.omnypay.log.ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_SENDTOSWITCH
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_SENDTOSWITCH);

		} else if (com.omnypay.log.ErrorCodeConstants.CONNECTING_WITH_SWITCH
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.CONNECTING_WITH_SWITCH);

		} else if (com.omnypay.log.ErrorCodeConstants.USER_REPORT_HISTORY
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_REPORT_HISTORY);

		} else if (com.omnypay.log.ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY);

		}	else if (ErrorCodeConstants.REGISTER_USER.equals(cloudServiceException
				.getErrorCode())) {

			status = userNull(ErrorCodeConstants.REGISTER_USER);
		} else if (ErrorCodeConstants.USER_LOGIN.equals(cloudServiceException
				.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_LOGIN);

		} else if (ErrorCodeConstants.USER_ALREADY_LOGIN
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_ALREADY_LOGIN);

		} else if (ErrorCodeConstants.USER_LOGIN_VALIDATE
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_LOGIN_VALIDATE);

		} else if (ErrorCodeConstants.USER_LOGIN_FAILED
				.equals(cloudServiceException.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_LOGIN_FAILED);

		}

		else if (ErrorCodeConstants.USER_LOGOUT.equals(cloudServiceException
				.getErrorCode())) {

			status = userNull(ErrorCodeConstants.USER_LOGOUT);

		} else if (ErrorCodeConstants.REGISTER_USER_STORE
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.REGISTER_USER_STORE);

		} else if (ErrorCodeConstants.USER_UPADTE_PROFILE
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.USER_UPADTE_PROFILE);

		}

		else if (ErrorCodeConstants.CONNECTING_WITH_COF
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_COF);

		} else if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD);

		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_CARD_ADD
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_CARD_ADD);

		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_DELETE_ACCT
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_DELETE_ACCT);

		} else if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_DELETE_ACCT
				.equals(cloudServiceException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_DELETE_ACCT);

		}
		return status;
	}


	
	
	
	
	
	public static Status getStatusByIntergrationException(IntegrationException integrationException){
		
		
		Status status =null;
		
		if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_GETONLINETOKEN
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_GETONLINETOKEN);

		
			
		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_GETONLINETOKEN.equals(integrationException
				.getErrorCode())) {

			status = serverError(ErrorCodeConstants.NULLPOINTER_EXCEPTION_GETONLINETOKEN);

		
		} else if (ErrorCodeConstants.FETCH_ALL_CARD_BANK_FROM_COF_
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.FETCH_ALL_CARD_BANK_FROM_COF_);

			
		} else if (com.omnypay.log.ErrorCodeConstants.CONNECTING_WITH_COF
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_COF);

		} else if (com.omnypay.log.ErrorCodeConstants.CONNECTING_WITH_HCE
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_HCE);

		} else if (com.omnypay.log.ErrorCodeConstants.USER_CHANGE_PASSWORD
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.USER_CHANGE_PASSWORD);

		} else if (com.omnypay.log.ErrorCodeConstants.TRANSACTION_INITIATE_AMOUNT_NOT
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.TRANSACTION_INITIATE_AMOUNT_NOT);

		}
		
		
		return status;
	}

	
	
	
	
	
	
	
	
	
	public static Status getStatusByErrorCode(String errorCode ){
		
		
		Status status =null;
		
		if (ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY
				.equals(errorCode)) {

			status = accessKeyNull(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

		
			
		} else if (ErrorCodeConstants.GET_USER.equals(errorCode)) {

			status = userNull(ErrorCodeConstants.GET_USER);

		

		} else if (ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS
				.equals(errorCode)) {

			status = userNull(ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS);

			
		} else if (com.omnypay.log.ErrorCodeConstants.UPDATE_PASS_VAL_STRING
				.equals(errorCode)) {

			status = userNull(ErrorCodeConstants.UPDATE_PASS_VAL_STRING);

		} else if (com.omnypay.log.ErrorCodeConstants.USER_FORGOT_PASSWORD
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.USER_FORGOT_PASSWORD);

		}else if (com.omnypay.log.ErrorCodeConstants.ENTITY_NULL_OR_EMPTY
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);

		}else if (com.omnypay.log.ErrorCodeConstants.USER_CHANGE_PASSWORD
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.USER_CHANGE_PASSWORD);

		}else if (com.omnypay.log.ErrorCodeConstants.TRANSACTION_PROCESS_NOT
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.TRANSACTION_PROCESS_NOT);

		}else if (com.omnypay.log.ErrorCodeConstants.STORE_REQUESTOF_AMOUNTSAVE
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.STORE_REQUESTOF_AMOUNTSAVE);

		}else if (com.omnypay.log.ErrorCodeConstants.PING_REQUEST_FROMSTORE
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.PING_REQUEST_FROMSTORE);

		}else if (com.omnypay.log.ErrorCodeConstants.GET_TIMEOUTREQ_FROM_UNICENTA
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.GET_TIMEOUTREQ_FROM_UNICENTA);

		}else if (com.omnypay.log.ErrorCodeConstants.GET_REQUEST_FROM_SWITCH
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.GET_REQUEST_FROM_SWITCH);

		}else if (com.omnypay.log.ErrorCodeConstants.AMOUNT_REQUEST_OF_ECOMM
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.AMOUNT_REQUEST_OF_ECOMM);

		}
		else if (com.omnypay.log.ErrorCodeConstants.REGISTER_USER
				.equals(errorCode)) {

			status = userNull(ErrorCodeConstants.REGISTER_USER);

		}

		else if (com.omnypay.log.ErrorCodeConstants.USER_LOGOUT
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.USER_LOGOUT);

		} else if (com.omnypay.log.ErrorCodeConstants.REGISTER_USER_STORE
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.REGISTER_USER_STORE);

		} else if (com.omnypay.log.ErrorCodeConstants.USER_UPADTE_PROFILE
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.USER_UPADTE_PROFILE);

		}

		else if (com.omnypay.log.ErrorCodeConstants.CONNECTING_WITH_COF
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_COF);

		} else if (com.omnypay.log.ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD);

		} else if (com.omnypay.log.ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_CARD_ADD
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD);

		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_DELETE_ACCT
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_DELETE_ACCT);

		} else if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_DELETE_ACCT
				.equals(errorCode)) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_DELETE_ACCT);

		}
		return status;
	}
		
		
	
	
	
	
	public static Status getStatusIntegrationException(
			IntegrationException integrationException) {

		Status status = null;

		if (ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY
				.equals(integrationException.getErrorCode())) {

			status = accessKeyNull(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

		} else if (ErrorCodeConstants.GET_USER.equals(integrationException
				.getErrorCode())) {

			status = userNull(ErrorCodeConstants.GET_USER);

		}

		if (ErrorCodeConstants.CONNECTING_WITH_COF.equals(integrationException
				.getErrorCode())) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_COF);

		} else if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD);

		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_CARD_ADD
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_CARD_ADD);

		}

		if (ErrorCodeConstants.CONNECTING_WITH_COF.equals(integrationException
				.getErrorCode())) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_COF);

		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_DELETE_ACCT
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_DELETE_ACCT);

		} else if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_DELETE_ACCT
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_DELETE_ACCT);

		}

		if (ErrorCodeConstants.CONNECTING_WITH_HCE.equals(integrationException
				.getErrorCode())) {

			status = serverError(ErrorCodeConstants.CONNECTING_WITH_HCE);

		} else if (ErrorCodeConstants.PARSE_EXCEPTION_DURING_GETOFFLINETOKEN
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.PARSE_EXCEPTION_DURING_GETOFFLINETOKEN);

		} else if (ErrorCodeConstants.NULLPOINTER_EXCEPTION_GETOFFLINETOKEN
				.equals(integrationException.getErrorCode())) {

			status = serverError(ErrorCodeConstants.NULLPOINTER_EXCEPTION_GETOFFLINETOKEN);

		}

		return status;
	}

	
	
	
	/***
	 * get Status if no access key found in db
	 * 
	 * @param errorcode a string having errorcode
	 * @return Status object
	 */
	public static TransactionInitiateRespDTO getTransactionStatus(Status status) {

		TransactionInitiateRespDTO	tnxstatus = new TransactionInitiateRespDTO();

		tnxstatus.setCode(status.getCode());
		tnxstatus.setMessage(status.getMessage());
		tnxstatus.setType(status.getType());

		return tnxstatus;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * get Status if no access key found in db
	 * 
	 * @param errorcode a string having errorcode
	 * @return Status object
	 */
	private static Status accessKeyNull(String errorcode) {

		Status	status = new Status();

		status.setCode(errorcode);
		status.setMessage(WebServiceConstants.ACCESS_KEY_NOT_EXIST);
		status.setType(WebServiceConstants.FIVE);

		return status;
	}

	
	
	/***
	 * get Status if user missed any field 
	 * 
	 * @param status  Status object 
	 * @return status Status object 
	 */
	private static Status entityNull(Status status) {

		status = new Status();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(WebServiceConstants.ENTITY_NULL);
		status.setType(WebServiceConstants.SIX);

		return status;
	}
	




	/***
	 * get Status if any server Error 
	 * 
	 * @param errorCode  represent errorcode
	 * @return status Status object 
	 */
	private static Status serverError(String  errorCode) {

		Status	status = new Status();

		status.setCode(errorCode);
		status.setMessage(WebServiceConstants.CLOUD_SERVER_ERROR);
		status.setType(WebServiceConstants.FIVE);

		return status;

	}

	
	
	/***
	 * get Status if no user found
	 * 
	 * @param errorcode a string having errorcode
	 * @return Status object
	 */
	private static Status userNull(String errorcode) {

	Status status = new Status();

	status.setCode(WebServiceConstants.CODE);
	status.setMessage(WebServiceConstants.USER_NOT_REGISTER);
	status.setType(WebServiceConstants.FIVE);

	return status;
}
	
	
	
	
	/***
	 * get Status if user missed any field
	 * 
	 * @param status
	 *            Status object
	 * @return status Status object
	 */
	public static CustomerRespDTO customerStatus(Status status) {

		CustomerRespDTO CustomerRespDTO = new CustomerRespDTO();

		CustomerRespDTO.setCode(status.getCode());
		CustomerRespDTO.setMessage(status.getMessage());
		CustomerRespDTO.setType(status.getType());

		return CustomerRespDTO;
	}

	/***
	 * get Status if user missed any field
	 * 
	 * @param status
	 *            Status object
	 * @return status Status object
	 */
	public static AccountRespDTO accountStatus(Status status) {

		AccountRespDTO accountStatus = new AccountRespDTO();

		accountStatus.setCode(status.getCode());
		accountStatus.setMessage(status.getMessage());
		accountStatus.setType(status.getType());

		return accountStatus;
	}

	
	
	
	
	
	/***
	 * get Status if user missed any field
	 * 
	 * @param status
	 *            Status object
	 * @return status Status object
	 */
	public static SecurityQuestionsRespDTO securitytStatus(Status status) {

		SecurityQuestionsRespDTO accountStatus = new SecurityQuestionsRespDTO();

		accountStatus.setCode(status.getCode());
		accountStatus.setMessage(status.getMessage());
		accountStatus.setType(status.getType());

		return accountStatus;
	}

	
	
	
	/***
	 * get Status if user missed any field
	 * 
	 * @param status
	 *            Status object
	 * @return status Status object
	 */
	public static CouponRespDTO coupontStatus(Status status) {

		CouponRespDTO couponStatus = new CouponRespDTO();

		couponStatus.setCode(status.getCode());
		couponStatus.setMessage(status.getMessage());
		couponStatus.setType(status.getType());

		return couponStatus;
	}

	
	
	
	
	/***
	 * get Status if user missed any field
	 * 
	 * @param status
	 *            Status object
	 * @return status Status object
	 */
	public static DiscountRespDTO discountStatus(Status status) {

		DiscountRespDTO discountStatus = new DiscountRespDTO();

		discountStatus.setCode(status.getCode());
		discountStatus.setMessage(status.getMessage());
		discountStatus.setType(status.getType());

		return discountStatus;
	}

	
	
	
	
	
	
	
	
	/***
	 * get Status if user missed any field
	 * 
	 * @param status
	 *            Status object
	 * @return status Status object
	 */
	public static RewardRespDTO rewardStatus(Status status) {

		RewardRespDTO rewardStatus = new RewardRespDTO();

		rewardStatus.setCode(status.getCode());
		rewardStatus.setMessage(status.getMessage());
		rewardStatus.setType(status.getType());

		return rewardStatus;
	}
	
	
	
	
	


}
