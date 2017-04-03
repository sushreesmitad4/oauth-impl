/**
 * 
 */
package com.omnypay.common.services;

import java.util.ArrayList;
import java.util.List;



import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;


//import org.omnypay.httpclient.dto.CardOnFileDTO;
//import org.omnypay.httpclient.dto.CardOnFileDTO;
//import org.omnypay.mobileapp.dto.AccountDTO;
//import org.omnypay.mobileapp.dto.RespSubFieldsDTO;
//import org.omnypay.mobileapp.dto.SecurityQuestionsRespDTO;
//import org.omnypay.mobileapp.util.WebServiceConstants;

//import com.omnypay.business.dto.CardOnFileDTO;

/**
 * @author iliyasm
 *
 */
public class CustomerHelper {
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * convert From DTO to BO
	 * 
	 * @param userId represent userId
	
	 * 
	 * 
	 * @return CardOnFileDTO
	 */
	
	public static CardOnFileDTO convertFromDTOtoBO(String userId) {
		
			CardOnFileDTO accBO = new CardOnFileDTO();
		
			accBO.setUserId(userId);
			
			return accBO;
	}
	
	/***
	 * convert From BO List To DTOList
	 * 
	 * @param boList List<CardOnFileDTO> object having all details
	
	 * 
	 * 
	 * @return List<AccountDTO> 
	 */
	
	public static List<AccountDTO> converFromBOListToDTOList(
			 List<CardOnFileDTO> boList) {

		List<AccountDTO> respSubFieldsDTOs = new ArrayList<AccountDTO>();

		for (CardOnFileDTO account : boList) {

			AccountDTO accountDTO = new AccountDTO();
			
			accountDTO.setAcctId(account.getAccId());
			
			accountDTO.setAcctNo(account.getAccNo());
					
			accountDTO.setAcctHolderName(account.getAccHolderName());
			
			if (account.getAccTransactionPwd()!=null && account.getAccTransactionPwd().length()!=0){
				
				accountDTO.setAccTransactionPwd(account.getAccTransactionPwd());
			}
			
			
			
			// based upon account type 
			if(account.getAccType().equalsIgnoreCase(WebServiceConstants.CREDIT_CARD_TYPE)
					||account.getAccType().equalsIgnoreCase(WebServiceConstants.DEBIT_CARD_TYPE)
					||account.getAccType().equalsIgnoreCase(WebServiceConstants.GIFT_PRIVATE_LAB_CARD_TYPE)
					){
			
				accountDTO.setAccType(account.getAccType());
				
				if (account.getAccCardExpDate()!=null && account.getAccCardExpDate().length()!=0){
				
					accountDTO.setAcctCardExpDate(account.getAccCardExpDate());
					
					
					
				}
				
				accountDTO.setCardNickName(account.getCardNickName());
				if (account.getIsFirstCard() != null
						&& account.getIsFirstCard().equalsIgnoreCase(
								WebServiceConstants.FIRSTCARD)) {

					accountDTO.setIsFirstCard(WebServiceConstants.IS_FIRST_CARD);

				} else {

					accountDTO.setIsFirstCard(WebServiceConstants.IS_FIRST_CARD_NOT);

				}
				
				accountDTO.setAccBrand(account.getAccBrand());
				
			}else if (account.getAccType().equalsIgnoreCase(WebServiceConstants.BANK_TYPE)){
				
				
				accountDTO.setAccType(WebServiceConstants.BANK_TYPE);
				accountDTO.setAcctBankName(account.getAccBankName());
				accountDTO.setAccBankRout(account.getAccBankRout());
				accountDTO.setAccBankType(account.getAccBankType());
				accountDTO.setAccCity(account.getAccCity());
				accountDTO.setAccState(account.getAccState());
				accountDTO.setAccStreet(account.getAccStreet());
				accountDTO.setAccZipcode(account.getAccZipcode());
			}
			respSubFieldsDTOs.add(accountDTO);
		}
		return respSubFieldsDTOs;

	}




		
		
		

/***
 * for getting status for security question
 * 
 * @param message represent response message
 * @param type represent response type
 * @param subfields having all fields
 * 
 * 
 * @return SecurityQuestionsRespDTO
 */
/*	public static SecurityQuestionsRespDTO getSecurityQuestionResp(
			String message, String type, List<RespSubFieldsDTO> subfields) {
		SecurityQuestionsRespDTO status = new SecurityQuestionsRespDTO();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setRequestedData(subfields);
		return status;
	}
*/






}
