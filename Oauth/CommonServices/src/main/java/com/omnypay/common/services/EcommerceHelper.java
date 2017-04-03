package com.omnypay.common.services;





//import com.omnypay.dao.bo.CloudSvrEcommerceTxn;

import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;


public class EcommerceHelper {
	

	/* ---------validations---------------*/	
	
	/***
	 * check Mandatory Fields To Save Amount Info
	 * 
	 * @param object having all details
	 * 
	 * @return true/false
	 */
public static boolean checkMandatoryFieldsToSaveAmountInfo(Object obj){
		
		if(obj instanceof TransactionDTO ){
			
			TransactionDTO transDTO = (TransactionDTO)obj;
						
			if ( transDTO.getIpAddress().trim().isEmpty()
					|| transDTO.getSessionId().trim().isEmpty()
					|| transDTO.getAmount().trim().isEmpty()
					|| transDTO.getMerchantAccessKey().trim().isEmpty()
					|| transDTO.getTransactionDateTime().trim().isEmpty()) {
					
				return  false;
			}
			
		}
		
		return true;
	}



/***
 * check Mandatory Fields To To Get Info for ecommerce txn
 * 
 * @param object having all details
 * 
 * @return true/false
 */
public static boolean checkMandatoryFieldsToGetInfo(Object obj){
	
	if(obj instanceof TransactionDTO ){
		
		TransactionDTO transDTO = (TransactionDTO)obj;
					
		if ( transDTO.getIpAddress().trim().isEmpty()
				|| transDTO.getSessionId().trim().isEmpty()
				) {
				
			return  false;
		}
		
	}
	
	return true;
}



/***
 * convert From DTO to BO Amount for ecommerce txn
 * 
 * @param transDTO having all details
 * 
 * @return CloudSvrEcommerceTxn
 */
public static CloudSvrEcommerceTxn convertFromDTOtoBOAmountForEcomm(TransactionDTO transDTO) {
	
	CloudSvrEcommerceTxn amountBO=new CloudSvrEcommerceTxn();
	
	if(transDTO.getAmount()!= null && transDTO.getAmount().length()!= 0){
		amountBO.setAmount((transDTO.getAmount()));
	}
	if(transDTO.getIpAddress()!= null && transDTO.getIpAddress().length()!= 0){
		amountBO.setIpAddress(transDTO.getIpAddress());
	}
	if(transDTO.getSessionId()!= null && transDTO.getSessionId().length()!= 0){
		amountBO.setSessionId(transDTO.getSessionId());
	}
	if(transDTO.getMerchantAccessKey()!= null && transDTO.getMerchantAccessKey().length()!= 0){
		amountBO.setMerchantAccessKey((transDTO.getMerchantAccessKey()));
	}
	if(transDTO.getTransactionDateTime()!= null && transDTO.getTransactionDateTime().length()!= 0){
		amountBO.setTransactionDateTime((transDTO.getTransactionDateTime()));
	}
	
	
	return amountBO;
}





}
