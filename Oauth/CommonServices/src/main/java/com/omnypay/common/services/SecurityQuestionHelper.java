package com.omnypay.common.services;

import java.util.ArrayList;
import java.util.List;





import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.RespSubFieldsDTO;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;

//import com.omnypay.dao.bo.CloudSvrSecQuesMaster;

/**
 * 
 * @author iliyasm
 *
 */
public class SecurityQuestionHelper {

	
	/***
	 * check Mandatory Fields for security question
	 * @param obj Object having all details
	 * 
	 * 
	 * @return  true/false
	 */
	public static boolean checkMandatoryFields(Object obj){
		
		if(obj instanceof BaseDTO ){
			
			BaseDTO baseDTO = (BaseDTO)obj;
			boolean isEmailCheckRequired = false;
			
			if ( baseDTO.getPhoneNumber().trim().isEmpty())  {

				isEmailCheckRequired = true;
			}
			if(isEmailCheckRequired && baseDTO.getEmailId().trim().isEmpty()){
				
				return true;
			}
			if(baseDTO.getImeiNo().trim().isEmpty()) {
				
				return true;
			}
		}
		return false;
	}
	

	/***
	 * convert From BO List ToDTO List for security question
	 * @param boList List<CloudSvrSecQuesMaster> object having all details
	 * 
	 * 
	 * @return  List<RespSubFieldsDTO>
	 */
	public static List<RespSubFieldsDTO> converFromBOListToDTOList(
								List<CloudSvrSecQuesMaster> boList){
		
		List<RespSubFieldsDTO> respSubFieldsDTOs = new ArrayList<RespSubFieldsDTO>();
		RespSubFieldsDTO respSubFieldsDTO =null;
		
		for (CloudSvrSecQuesMaster cloudSvrSecQuesMaster : boList) {
			
			respSubFieldsDTO = new RespSubFieldsDTO();
			respSubFieldsDTO.setQuestionId(cloudSvrSecQuesMaster.getId());
			respSubFieldsDTO.setQuestionName(cloudSvrSecQuesMaster.getQuestion());
			respSubFieldsDTOs.add(respSubFieldsDTO);
		}
		return respSubFieldsDTOs;
	}
}
