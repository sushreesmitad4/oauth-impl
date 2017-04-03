/**
 * 
 */
package com.omnypay.common.services;


import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.TransactionDTO;







/**
 * @author iliyasm
 *
 */
public class AccountHelper {
	
	
	
	
	/* ---------validations---------------*/
	
	/***
	 * check Mandatory Fields for adding Card
	 * 
	 * @param obj represent all details
	 *  
	 * @return true/false 
	 */
	public static boolean checkMandatoryFieldsCard(Object obj){
		
		if(obj instanceof AccountDTO ){
			
			AccountDTO accountDTO = (AccountDTO)obj;
						
			if ( accountDTO.getAcctHolderName().trim().isEmpty()
					||accountDTO.getAcctNo().trim().isEmpty()
					||accountDTO.getCardNickName().trim().isEmpty()
					||accountDTO.getAccType().trim().isEmpty()
					||accountDTO.getAccBrand().trim().isEmpty() )
			{
					
				return  false;
			}
			
		}
		
		return true;
	}
	
	/***
	 * check Mandatory Fields for adding BAnk
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return true/false 
	 */	
public static boolean checkMandatoryFieldsBank(Object obj){
		
		if(obj instanceof AccountDTO ){
			
			AccountDTO accountDTO = (AccountDTO)obj;
						
			if ( accountDTO.getAcctHolderName().trim().isEmpty()
					||accountDTO.getAcctBankName().trim().isEmpty()
					||accountDTO.getAcctNo().trim().isEmpty()
					||accountDTO.getAccBankRout().trim().isEmpty()
					||accountDTO.getAccBankType().trim().isEmpty()
					||accountDTO.getAccStreet().trim().isEmpty()
					||accountDTO.getAccCity().trim().isEmpty()
					||accountDTO.getAccState().trim().isEmpty()
					||accountDTO.getAccZipcode().trim().isEmpty()
					)
			{
					
				return  false;
			}
			
		}
		
		return true;
	}
	

/***
 * check Mandatory Fields for Delete Account(Card/Bank)
 * 
 * @param obj represent all details
 * 
 * 
 * @return true/false 
 */	
	public static boolean checkMandatoryFieldsDeleteAccount(Object obj) {

		if (obj instanceof AccountDTO) {

			AccountDTO accountDTO = (AccountDTO) obj;

			if (accountDTO.getAcctId().trim().isEmpty()) {

				return false;
			}

		}

		return true;
	}
	
	/***
	 * check Mandatory Fields for Getting AccountType
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return CardOnFileDTO
	 */	
	public static CardOnFileDTO checkMandatoryFieldsGetAccountType(Object obj,String userid) {

		CardOnFileDTO accBO = new CardOnFileDTO();
		
		if (obj instanceof TransactionDTO) {

			TransactionDTO tranDTO = (TransactionDTO) obj;
			
			if(tranDTO.getAccId()!=null){
			accBO.setAccId(tranDTO.getAccId());
			}
			// new add code by iliyas
			 if (userid != null){
				 
				 accBO.setUserId(userid); 
			 }
			if(tranDTO.getAccNo()!=null){
			 accBO.setAccId(tranDTO.getAccNo());
			}
		}

		return accBO;
	}
	
	
	
	
	
	
		
	/***
	 * convert From DTO to COFDTO AddCard And Bank
	 * 
	 * @param obj represent all details
	 * @param userId represent userID
	 * 
	 * @return CardOnFileDTO
	 */	
	public static CardOnFileDTO convertFromDTOtoCOFDTOAddCardAndBank(Object object,String userId) {
			
			CardOnFileDTO accBO = new CardOnFileDTO();
			
			if(object instanceof AccountDTO ){
				
				AccountDTO accountTO = (AccountDTO) object;
				
				
				accBO.setAccHolderName(WebServiceUtil.URLDecoderField(accountTO
						.getAcctHolderName()));

				accBO.setAccNo(accountTO.getAcctNo());
				
				
				accBO.setAccType(accountTO.getAccType());
				
				
				accBO.setUserId(userId);
				
				
				if (accountTO.getAccTransactionPwd() != null
						&& accountTO.getAccTransactionPwd().length() != 0) {

					accBO.setAccTransactionPwd(accountTO.getAccTransactionPwd());
				}

				
				
				// card add case 
				
				if (accountTO.getAccType().equalsIgnoreCase(WebServiceConstants.CREDIT_CARD_TYPE)
						|| accountTO.getAccType().equalsIgnoreCase(WebServiceConstants.DEBIT_CARD_TYPE)
						|| accountTO.getAccType().equalsIgnoreCase(WebServiceConstants.GIFT_PRIVATE_LAB_CARD_TYPE)){
					
					
				
				

				if (accountTO.getAcctCardExpDate() != null
						&& accountTO.getAcctCardExpDate().length() != 0) {
					accBO.setAccCardExpDate(accountTO.getAcctCardExpDate());
				}
				if (accountTO.getAcctCardExpYear() != null
						&& accountTO.getAcctCardExpYear().length() != 0) {

					accBO.setAccCardExpYear(accountTO.getAcctCardExpYear());
				}

				if (accountTO.getAccCardVfcCode() != null
						&& accountTO.getAccCardVfcCode().length() != 0) {

					// accBO.setAccCardCvvCode(accountTO.getAccCardVfcCode());
					// if cvv number coming we are not store in data base
					// crisy say 20.05.2015
					if (accountTO.getAccCardVfcCode().length() == 3) {
						accBO.setAccCardCvvCode("XXX");
					} else if (accountTO.getAccCardVfcCode().length() == 4) {

						accBO.setAccCardCvvCode("XXXX");
					}

				}
		

				accBO.setCardNickName(WebServiceUtil.URLDecoderField(accountTO
						.getCardNickName()));

				accBO.setAccBrand(accountTO.getAccBrand());

				
				
				// banks type 
				// in case of bank 
				} else if (accountTO.getAccType().equalsIgnoreCase(WebServiceConstants.BANK_TYPE)){
					
					
					
					accBO.setAccBankName(WebServiceUtil.URLDecoderField(accountTO.getAcctBankName()));
					accBO.setAccBankRout(accountTO.getAccBankRout());
					accBO.setAccBankType(accountTO.getAccBankType());
				    accBO.setAccStreet(WebServiceUtil.URLDecoderField(accountTO.getAccStreet()));
				    accBO.setAccCity(WebServiceUtil.URLDecoderField(accountTO.getAccCity()));
					accBO.setAccState(accountTO.getAccState());
					accBO.setAccZipcode(accountTO.getAccZipcode());
					//heard code for bank always zero
					accBO.setAccType(WebServiceConstants.BANK_TYPE);
					
					
					
					
				}
				
				
				
				
				
				
			}	
				
				
			
			return accBO;
		}
		
		
			
		
		/***
		 * conversion from DTO to BO	for Bank
		 * 
		 * @param obj represent all details
		 * @param userId represent userID
		 * 
		 * @return CardOnFileDTO
		 */	
		public static CardOnFileDTO convertFromDTOtoBODeleteAccount(Object object,String userId) {
					
					CardOnFileDTO accBO = new CardOnFileDTO();
					
					if(object instanceof AccountDTO ){
						
						AccountDTO accountTO = (AccountDTO) object;
						
						accBO.setAccId(accountTO.getAcctId());
						accBO.setUserId(userId);
								
						}
					
					return accBO;
				}
		
		
		
			
				/***
				 * conversion from DTO to BO	for Getting More OfflineTokens
				 * 
				 * @param object represent all details
				 * 
				 * 
				 * @return CardOnFileRespDTO
				 */					
			public static CardOnFileRespDTO convertFromDTOtoBOGetMoreOfflineToken(Object object) {
					
					CardOnFileRespDTO accBO = new CardOnFileRespDTO();
					
					if(object instanceof AccountDTO ){
						
						AccountDTO accountTO = (AccountDTO) object;
						
						accBO.setAccId(accountTO.getAcctId());
						
								
						}
					
					return accBO;
				}
				
/***
 * checkMandatoryFields Check Scan CardData
 * 
 * @param obj represent all details
 * 
 * 
 * @return true/false
 */					
	public static boolean checkMandatoryFieldsCheckScanCardData(Object obj) {

		if (obj instanceof AccountDTO) {

			AccountDTO accountDTO = (AccountDTO) obj;
			if (accountDTO.getAccCheckScanData().trim().isEmpty()) {

				return false;
			}
		}

		return true;
	}
	
	
	
	
	
	
	/***
	 * get Status after adding an card
	 * 
	 * @param accountId
	 * @return AccountRespDTO as status
	 */
	public static AccountRespDTO addCard(CardOnFileRespDTO accountId) {

		AccountRespDTO status = null;
		if (accountId != null) {

			if (accountId.getType().equalsIgnoreCase(WebServiceConstants.ONE)) {

				status = (AccountRespDTO) WebServiceUtil.getAccount(
						WebServiceConstants.ADD_CARD_SUCCESS,
						WebServiceConstants.ONE, accountId.getAccId(),
						accountId.getTokens(), accountId.getTokenexpDate(),
						accountId.getUdk());

			} else if (accountId.getType().equalsIgnoreCase(
					WebServiceConstants.TWO)) {

				status = (AccountRespDTO) WebServiceUtil.getAccount(
						WebServiceConstants.HCE_SERVER_ERROR,
						WebServiceConstants.TWO, accountId.getAccId(), null,
						null, null);

			} else if (accountId.getType().equalsIgnoreCase(
					WebServiceConstants.THREE)) {

				status = (AccountRespDTO) WebServiceUtil.getAccount(
						WebServiceConstants.ADD_CARD_EXIST,
						WebServiceConstants.THREE, accountId.getAccId(), null,
						null, null);

			} else if (accountId.getType().equalsIgnoreCase(
					WebServiceConstants.FOUR)) {

				status = (AccountRespDTO) WebServiceUtil.getAccount(
						WebServiceConstants.COF_SERVER_ERROR,
						WebServiceConstants.THREE, accountId.getAccId(), null,
						null, null);

			}
		}

		return status;
	}

	
	
	
}


