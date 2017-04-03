/**
 * 
 */
package com.omnypay.business.service;


import java.util.List;




import com.omnypay.business.util.IntegrationException;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HceTokenRespDTO;


/**
 * @author iliyasm
 *
 */
public interface HceTokenService {
	
	
	// this method is used to get line token from hce server
	HceTokenRespDTO  getOnlineTokenService(HceTokenDTO hceTokenDTO,String accType) throws IntegrationException ;
	
	HceTokenRespDTO  deleteOnlineToken(HceTokenDTO hceTokenDTO);
	
	// this method is used to get offline token from hce server
	List<HceTokenRespDTO>  getOfflineTokenForAllCardsService(List<HceTokenDTO> hceTokenDTO) throws IntegrationException ;

}
