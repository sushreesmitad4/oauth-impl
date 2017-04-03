/**
 * 
 */
package com.omnypay.business.service;

import java.util.List;









import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;





/**
 * @author iliyasm
 *
 */
public interface AccountService {
	
	// this method is used for add card in cof via cloud server 
	CardOnFileRespDTO     addAccountService(CardOnFileDTO accountDTO)throws CloudServiceException;
	
	// this method is used for add bank in cof via cloud server
	CardOnFileRespDTO     addAchService(CardOnFileDTO accountDTO) throws CloudServiceException;;
	
	// this method is used for update card and update bank in cof via cloud server
	CardOnFileRespDTO     updateAccountService(CardOnFileDTO accountDTO) throws CloudServiceException;
	
	// this method is used for getting all card/ bank added by user
	List<CardOnFileDTO> getAccountDetailsService(CardOnFileDTO cardOnFileDTO) throws IntegrationException;
		
	// this method is used to get based upon account id get account detail
	CardOnFileRespDTO getAccountService(CardOnFileDTO cardOnFileDTO) throws CloudServiceException;
	
	CardOnFileRespDTO  deleteAccountService(CardOnFileDTO cardOnFileDTO);
			
		

}
