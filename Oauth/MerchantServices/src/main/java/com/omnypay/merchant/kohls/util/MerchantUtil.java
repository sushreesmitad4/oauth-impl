/**
 * 
 */
package com.omnypay.merchant.kohls.util;


import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.CustomerRespDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.MerchantName;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;

/**
 * @author jagdishm
 *
 */
public class MerchantUtil {
	
	public static Status getStatus(String code,String message,String type )
	{
		Status status=new Status();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
	public static MerchantName getEnumFromString(MerchantName merchantName)
	{
		
	return	MerchantName.valueOf(merchantName.toString().toLowerCase());
		
	}
	
	
	
	public static SecurityQuestionsRespDTO getSecurityQuestionsStatus(String code,String message,String type )
	{
		SecurityQuestionsRespDTO status=new SecurityQuestionsRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}

	
	
	public static AccountRespDTO getAccountRespStatus(String code,String message,String type )
	{
		AccountRespDTO status=new AccountRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
	
	
	public static CustomerRespDTO getCustomerRespStatus(String code,String message,String type )
	{
		CustomerRespDTO status=new CustomerRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
	
	
	public static TransactionInitiateRespDTO getTransactionStatus(String code,String message,String type )
	{
		TransactionInitiateRespDTO status=new TransactionInitiateRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
		
	public static CouponRespDTO getCouponRespStatus(String code,String message,String type )
	{
		CouponRespDTO status=new CouponRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
	
	
	public static RewardRespDTO getRewardRespStatus(String code,String message,String type )
	{
		RewardRespDTO status=new RewardRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
	
	
	public static DiscountRespDTO getCouponCalRespStatus(String code,String message,String type )
	{
		DiscountRespDTO status=new DiscountRespDTO();
		status.setCode(code);
		status.setMessage(message);
		status.setType(type);
		return status;
	}
	
	
	
	
	
}
