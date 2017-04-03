/*package com.omnypay.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.EcommerceService;
import com.omnypay.business.util.BusinessException;
import com.omnypay.dao.EcommerceDAO;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.util.DaoException;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;


@Service("ecommService")
public class EcommerceServiceImpl implements EcommerceService {
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	
	private final String CLASS_NAME = this.getClass().getName();
	
	@Autowired
	EcommerceDAO ecommerceDAO;
	
	public EcommerceServiceImpl() {
	}
	
	
	@Transactional
	public boolean SaveAmountInfoService(CloudSvrEcommerceTxn amount)
	{
         String METHOD_NAME="SaveAmountInfoService";
         
         boolean isAmountSave=false;
         
         log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);             
        
         try
         {
        	
        	 
        	 isAmountSave = this.ecommerceDAO.saveAmountInfoDao(amount);
       
        	       
          }
         catch (DaoException daoException) 
        {
        	
        	 log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, daoException.fillInStackTrace(),
 					new StringBuilder(METHOD_NAME));
        	
 		}
         log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return isAmountSave;
	}

	@Transactional
	public String ResponseInfoServiceOfEcommerce(CloudSvrEcommerceTxn info) {
		
		String METHOD_NAME = "ResponseInfoServiceOfEcommerce";

		String isAmountSave = null;

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {

			isAmountSave = this.ecommerceDAO.ResponseInfoDaoOfEcommerce(info);

		} catch (DaoException daoException) {

			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, daoException.fillInStackTrace(),
 					new StringBuilder(METHOD_NAME));
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		
		return isAmountSave;
	}


	@Transactional
	public void ResponseUpdateServiceOfEcommerce(String status, String posid) {
		
		String METHOD_NAME = "ResponseUpdateServiceOfEcommerce";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {

			this.ecommerceDAO.ResponseUpdateDaoOfEcommerce(status, posid);

		} catch (DaoException daoException) {

			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, daoException.fillInStackTrace(),
 					new StringBuilder(METHOD_NAME));
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		
		
		
	}

	
	
	
	
}
*/