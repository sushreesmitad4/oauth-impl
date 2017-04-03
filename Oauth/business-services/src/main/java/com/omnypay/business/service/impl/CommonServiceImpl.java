package com.omnypay.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.CommonService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.MerchantName;
import com.omnypay.dao.CommonDAO;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.impl.CommonDaoImpl;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

//@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();

	private final String CLASS_NAME = this.getClass().getName();

	@Autowired
	CommonDAO commonDAO;

	/*public CommonServiceImpl() {
	}*/

	
	
	/**
	 * to get AccessKey For Merchant
	 * @param entityInfo CloudSvrBusinessEntityInfo object having all details to get the merchant access key
	 * @param newEncryptedPass  represent new password for the user
	 * @param isOldPasscode    represent old password for the user
	 * @return String
	 * @throws CloudServiceException in case business validation failed
	 * 
	 */
	@Transactional
	public MerchantAccessDTO isAccessKeyForMerchantExistService(String merchantAccess) throws CloudServiceException {
		
		
		String METHOD_NAME = "isAccessKeyForMerchantExistService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrBusinessEntityInfo entity = null;
		
		MerchantAccessDTO merchantAccessDTO = null;
		
				
		
		try {
		
			//entity = commonDAO.getAccessKeyForMerchantDao(enti);
			
			//commonDAO = new CommonDaoImpl();
			entity = commonDAO.getAccessKeyForMerchantDao(merchantAccess);
			
			if (entity !=null && entity.getExternalMerchant().equalsIgnoreCase("true")){
				
				
				merchantAccessDTO = new MerchantAccessDTO();
				//if (entity.getMerchantName().equalsIgnoreCase(anotherString)){
				merchantAccessDTO.setMerchantNAme(MerchantName.valueOf(entity.getMerchantName()));
				//}
				merchantAccessDTO.setIsExternal(true);
				
				
			} else if (entity !=null && entity.getExternalMerchant().equalsIgnoreCase("false")){
				
				merchantAccessDTO = new MerchantAccessDTO();
				merchantAccessDTO.setIsExternal(false);
				
			}
			
			

		
		} catch (CloudDAException daoException) {

			isAccessKeyForMerchantExistCloudServiceException(daoException);
			
		} catch (Exception ex) {
			isAccessKeyForMerchantExistException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		
		return merchantAccessDTO;
	}

	
	
	/**
	 * get cloud service Exception for Merchant key Exist or not
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException isAccessKeyForMerchantExistCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "isAccessKeyForMerchantExistCloudServiceException";;
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("DaoException occured  matching merchant access key").append(BusinessConstants.COMMA);
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(), accessException.getMessage());
	}
	
	/**
	 * get Exception for Merchant key Exist or not
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  isAccessKeyForMerchantExistException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "isAccessKeyForMerchantExistException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred  matching merchant access key")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY,ex.getMessage());
	
	}
	
}
