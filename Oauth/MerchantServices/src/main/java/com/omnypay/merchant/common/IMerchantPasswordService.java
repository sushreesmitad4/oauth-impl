/**
 * 
 */
package com.omnypay.merchant.common;

import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.common.services.dto.Status;

/**
 * @author jagdishm
 *
 */
public interface IMerchantPasswordService {
	
	
	Status  forgotPassword(PasswordDTO passwordDTO);
	
	Status  changePassword(PasswordDTO passwordDTO);
	
	

}
