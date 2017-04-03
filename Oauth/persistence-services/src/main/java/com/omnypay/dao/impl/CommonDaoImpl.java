package com.omnypay.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.CommonDAO;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

@Repository
public class CommonDaoImpl extends BaseDaoImpl implements CommonDAO {

	private static Log4jAdapter log = Log4jAdapter.getInstance();

	private final String CLASS_NAME = this.getClass().getName();

	// constructor
	public CommonDaoImpl() {
	}

	
	
	
	
	
	/***
	 * to get access key for merchant
	 * 
	 * @param entityInfo  CloudSvrBusinessEntityInfo  object having details to get access key from db
	 * @return String
	 * @throws CloudDAException
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public CloudSvrBusinessEntityInfo getAccessKeyForMerchantDao(String entity) throws CloudDAException {

		String METHOD_NAME = "getAccessKeyForMerchantDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		
		StringBuilder accessKey = null;
		
		List<CloudSvrBusinessEntityInfo> entityInfos = null;
		
		CloudSvrBusinessEntityInfo myEntity = null;
		
		 System.out.println("checking here ");
		 
		 
		 
		if (entity!=null ) {
				
			accessKey = new StringBuilder("from CloudSvrBusinessEntityInfo c where c.status='"+ DaoConstants.ACTIVE_STATUS_DATABASE+"'").append(" and  c.merchantKey= '")
						.append(entity).append("'");
		}

		//String accessKeyQuery = this.getAccessKeyForMerchantDaoQuery(entityInfo);
		
		try {
			
			//System.out.println("go");
			//CloudSvrBusinessEntityInfo entityInfos = (CloudSvrBusinessEntityInfo) super.getHibernateTemplate().get(CloudSvrBusinessEntityInfo.class, new Long("1068"));
		
			
			 entityInfos = (List<CloudSvrBusinessEntityInfo>) super.getHibernateTemplate().find(accessKey.toString());
			
			 if (entityInfos != null & entityInfos.size() > 0) {
				 
				 myEntity = entityInfos.get(Integer.parseInt(DaoConstants.NUMBER_ZERO));
				
				//accessKey = true;
				//bool asd= entityInfos.get()==enum.kolh? true:false;
				
			}
			// accessKey = true;
			 
		
		} catch (DataAccessException accessException) {
			
			getAccessKeyForMerchantDaoDAException(accessException);
			
			
		} catch (Exception ex) {
			getAccessKeyForMerchantDaoException(ex);
		}
		
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		
		return myEntity;

	}

	

	/***
	 * to get query for access key from database
	 * 
	 * @param entityInfo  CloudSvrBusinessEntityInfo  object having details for making query to get access key from db
	 * @return String
	 * 
	 * 
	 * 
	 */
	private String getAccessKeyForMerchantDaoQuery(
			CloudSvrBusinessEntityInfo enityInfo) {

	
		StringBuilder queryString =  null;

	
	
		/*else if (enityInfo.getMerchantKey() != null
				&& !enityInfo.getMerchantKey().isEmpty()) {
			queryString.append(" and  c.merchantKey= '")
					.append(enityInfo.getMerchantKey()).append("'");
		}*/

		return queryString.toString();

	}
	
	
	
	// /////////////// private methods for Exception /////////////////

		/**
		 * get CloudDAException
		 * 
		 * @param DataAccessException
		 * @return CloudDAException
		 * @throws CloudDAException
		 */
		private CloudDAException getAccessKeyForMerchantDaoDAException(
				DataAccessException accessException) throws CloudDAException {
			String METHOD_NAME = "getAccessKeyForMerchantDaoDAException";
			StringBuilder errorBuffer = new StringBuilder();
			errorBuffer
					.append("DataAccessException occurred while matching merchant access key")
					.append(DaoConstants.COMMA).append("Error Code : ")
					.append(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY)
					.append(DaoConstants.COMMA).append("Error Message : ")
					.append(accessException.getMessage());
			log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
			throw new CloudDAException(
					ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY,
					accessException.getMessage());

		}

		/**
		 * get CloudDAException
		 * 
		 * @param Exception
		 * @return CloudDAException
		 * @throws CloudDAException
		 */
		private CloudDAException getAccessKeyForMerchantDaoException(
				Exception ex) throws CloudDAException {
			String METHOD_NAME = "getAccessKeyForMerchantDaoException";
			StringBuilder errorBuffer = new StringBuilder();
			errorBuffer
					.append("Exception occurred while getting the NewRandomPwdTO")
					.append(DaoConstants.COMMA).append("Error Code : ")
					.append(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY)
					.append(DaoConstants.COMMA).append("Error Message : ")
					.append(ex.getMessage());

			log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
			throw new CloudDAException(
					ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY,
					ex.getMessage());

		}

}
