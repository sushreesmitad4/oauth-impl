/**
 * 
 */
package com.omnypay.business.service;




import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.dto.PosDTO;
import com.omnypay.common.services.dto.SwitchTxnRespDTO;




//import com.omnypay.dao.bo.CloudSvrEcommerceTxn;

/**
 * @author iliyasm
 *
 */
public interface PosService {
	//PosRespDTO  posConnectorService(PosDTO posDTO,HceTokenRespDTO token,String acctype);
	
	//String  posConnector(PosDTO posDTO);
	
	//PosRespDTO  posAmountConfirmConnectorService(PosDTO posDTO);
	
	
	SwitchTxnRespDTO  sendToSwitch(PosDTO posdata)throws CloudServiceException ;
}
