/*package com.omnypay.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.omnypay.dao.EcommerceDAO;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

public class EcommerceDAOImpl extends BaseDaoImpl implements EcommerceDAO {
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	
	private final String CLASS_NAME = this.getClass().getName();

	//constructor
	public EcommerceDAOImpl() {
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean saveAmountInfoDao(CloudSvrEcommerceTxn amount) {
		
		
		 String METHOD_NAME = "saveAmountInfoDao";
		
		 log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		 boolean isAmountSave = false;
		 
		 CloudSvrEcommerceTxn cloudSvrEcommerceInfo = null;
		 
		 List<CloudSvrEcommerceTxn> ecommerce = null;
		 
		 String ecommQuery = getQueryForEcommerceSaveOrUpdate(amount);
		 		
		try {
			
			
			ecommerce = (List<CloudSvrEcommerceTxn>)super.getHibernateTemplate().find(ecommQuery);
			
			if(ecommerce != null & ecommerce.size() > 0){
				
				cloudSvrEcommerceInfo = ecommerce.get(0);
				
				cloudSvrEcommerceInfo.setStatus(DaoConstants.PROCESSING);
				
				cloudSvrEcommerceInfo.setAmount(amount.getAmount());
				
				cloudSvrEcommerceInfo.setSessionId(amount.getSessionId());
				
				cloudSvrEcommerceInfo.setTransactionDateTime(amount.getTransactionDateTime());
				
				super.getHibernateTemplate().update(cloudSvrEcommerceInfo);
				
				isAmountSave = true ;
				
			} else {
				
				// status 
				 amount.setStatus(DaoConstants.PROCESSING); 
				
				
				super.getHibernateTemplate().save(amount);
				
				isAmountSave = true;
				
				
			}
			
			
			
		}
		catch(DataAccessException accessException){
		
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, accessException.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
			
			
		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);	
		
		return isAmountSave;
		
	}


	
	public String ResponseInfoDaoOfEcommerce(CloudSvrEcommerceTxn info) {

		 String METHOD_NAME = "ResponseInfoDaoOfEcommerce";
		
		 String status = null;
		 
		 log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		 
		 String ecommQuery = this.getQueryForEcommerce(info);
		 
		 
		try {
			
			
			@SuppressWarnings("unchecked")
			List<CloudSvrEcommerceTxn> ecommerce = (List<CloudSvrEcommerceTxn>)super.getHibernateTemplate().
					 									find(ecommQuery);
			if(ecommerce != null & ecommerce.size() > 0){
				
				status =  ecommerce.get(0).getStatus();
				
				
			}
			
			
			//super.getHibernateTemplate().save(amount);
			
			//status = true;
		} catch(DataAccessException accessException) {
		
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, accessException.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);	
		return status;
		
	}

	
	
	
	
	public void ResponseUpdateDaoOfEcommerce(String status, String posid) {
		
		String METHOD_NAME = "ResponseUpdateDaoOfEcommerce";
			
		 log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		 
		 String ecommQuery = this.getQueryForEcommerceUpdate(posid);
		 
		 CloudSvrEcommerceTxn ecommerceAmountInfo = null;
		 
		try {
			
			
			@SuppressWarnings("unchecked")
			List<CloudSvrEcommerceTxn> ecommerce = (List<CloudSvrEcommerceTxn>)super.getHibernateTemplate().
					 									find(ecommQuery);
			if(ecommerce != null & ecommerce.size() > 0){
				
				ecommerceAmountInfo =  ecommerce.get(0);
				
				ecommerceAmountInfo.setStatus(status);
				
				super.getHibernateTemplate().update(ecommerceAmountInfo);
			}
			
			
			//super.getHibernateTemplate().save(amount);
			
			//status = true;
		} catch(DataAccessException accessException) {
		
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, accessException.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);	
				
	}
	
	
	
	
	private String getQueryForEcommerce(CloudSvrEcommerceTxn info){

       		
		StringBuilder queryString = new StringBuilder("from CloudSvrEcommerceAmountInfo c where 1=1");
		
		
		if(info.getIpAddress() != null) {
			
			queryString.append(" and c.ipAddress= '").append(info.getIpAddress()).append("'");
		}
		
		if(info.getSessionId() != null ) {
			queryString.append(" and c.sessionId= '").append(info.getSessionId()).append("'");
		}
		
		
		
		return queryString.toString();
		 
	}


	
	private String getQueryForEcommerceUpdate(String posid){

      
        
        
        String as[] = posid.split(";");
        
        	
		StringBuilder queryString = new StringBuilder("from CloudSvrEcommerceAmountInfo c where 1=1");
		
		
		if(as[0] != null) {
			
			queryString.append(" and c.ipAddress= '").append(as[0]).append("'");
		}
		
		if(as[1] != null ) {
			queryString.append(" and c.sessionId= '").append(as[1]).append("'");
		}
		
		
		
		return queryString.toString();
		 
	}
	
	
	
	private String getQueryForEcommerceSaveOrUpdate(CloudSvrEcommerceTxn info){

       		
		StringBuilder queryString = new StringBuilder("from CloudSvrEcommerceAmountInfo c where 1=1");
		
		
		if(info.getIpAddress() != null) {
			
			queryString.append(" and c.ipAddress= '").append(info.getIpAddress()).append("'");
		}
		
		
		
		return queryString.toString();
		 
	}
	
}
*/