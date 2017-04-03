package com.omnypay.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.TransactionDao;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTempToken;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * 
 * @author Iliyasm
 *
 */
@Repository("txnDao")
public class TransactionDaoImpl extends BaseDaoImpl implements TransactionDao {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	
	private final String CLASS_NAME = this.getClass().getName();
	

	public TransactionDaoImpl() {
	}

	
	
	/***
	 * to get user all transaction history 
	 * 
	 * @param userId String  userId of the user
	 * @param txn  CloudSvrTxn  object having all details to get transaction report
	 * @throws CloudDAException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<CloudSvrTxn> getHistoryDao(CloudSvrTxn txn, String userId) throws CloudDAException {

		String METHOD_NAME = "getHistoryDao";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		List<CloudSvrTxn> txnList = null;
		
		StringBuilder queryString  = this.getHistory(txn,userId);
	 
		
		try {
			
			Query query = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(queryString.toString());

			 txnList = (List<CloudSvrTxn>)query.list();
	
			
		}  catch (DataAccessException accessException) {
			
			getHistoryDaoDAException(accessException);
			
		} catch (Exception ex) {
			
			getHistoryDaoException(ex);
			
			}
		
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return txnList;
	}

	
	

	/***
	 * to get the user AccountBased report transaction history 
	 * 
	 * 
	 * @param txn  CloudSvrTxn  object having all details to get AccountBased transaction report
	 * @throws CloudDAException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<CloudSvrTxn> getAcctBasedTransactionSummaryDao(CloudSvrTxn txn) throws CloudDAException{

		String METHOD_NAME = "getAcctBasedTransactionSummaryDao";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		List<CloudSvrTxn> txnList = new ArrayList<CloudSvrTxn>();

		
		 StringBuilder queryString =  this.getAcctBased(txn);
	

		try {

			Query query = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(queryString.toString());
			
			txnList = (List<CloudSvrTxn>)query.list();

			

		}  catch (DataAccessException accessException) {
			
			getAcctBasedTransactionSummaryDaoDAException(accessException);
			
		} catch (Exception ex) {
			
			
			getAcctBasedTransactionSummaryDaoException(ex);
			
		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return txnList;
	}

	
	
			
	
	/***
	 * to get the Mid From Database
	 * 
	 * 
	 * @param posCon  CloudSvrQrCodeTxn  object having all details to get Mid
	 * @throws CloudDAException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public CloudSvrPosCon getMidFromDbDao(CloudSvrQrCodeTxn posCon) throws CloudDAException{

		String METHOD_NAME = "getMidFromDbDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrPosCon posConData = null;
		
		StringBuilder queryString =this .getMerchantDetail(posCon);

	
		try {

			Query query = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(queryString.toString());

			List<Object[]> list = query.list();

			for (Object[] result : list) {

				String posId = (String) result[0];

				String qrCode = (String) result[1];

				String mid = (String) result[2];

				String mName = (String) result[3];

				String mAccessKey = (String) result[4];

				String mAddress1 = (String) result[5];

				String mAddress2 = (String) result[6];

				String city = (String) result[7];

				String state = (String) result[8];

				String country = (String) result[9];

				String zipcode = (String) result[10];

				String tid = (String) result[11];

				String sid = (String) result[12];

				posConData = new CloudSvrPosCon();

				posConData.setMerchantId(mid);

				posConData.setMerchantName(mName);

				posConData.setMerchantAccKey(mAccessKey);

				posConData.setMerchantAddress(mAddress1 + city);

				posConData.setPosId(posId);

				posConData.setQrCode(qrCode);

				posConData.settId(tid);

				posConData.setStoreId(sid);

			}

		}  catch (DataAccessException accessException) {
			
			getMidFromDbDaoDAException(accessException);
			
			
		} catch (Exception ex) {
			getMidFromDbDaoException(ex);
		}
		

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return posConData;
	}			
			
			
	
	/***
	 * to getHistory quary
	 * 
	 * @param txn  CloudSvrTxn  object having all details to get History quary
	 * @param userId  String represent userId of the user
	 * @return string represent the query
	 * 
	 */	
	private StringBuilder getHistory(CloudSvrTxn txn, String userId){
		
		
		StringBuilder queryString = new StringBuilder("from CloudSvrTxn c where 1=1");
		
		if(userId != null) {
		
			queryString.append(" and c.customerId = '").append(userId).append("'");
		} if (txn.getFromDate() != null && txn.getToDate() != null) {

			queryString
					.append(" and to_date(cast(txnDate as date),'dd-mm-yy') between to_date('"
							+ txn.getFromDate()
							+ "','dd-mm-yy') and TO_DATE('"
							+ txn.getToDate()
							+ "','dd-mm-yy')");
			
		}
		
		queryString.append(" and c.txnStatus = '").append("SUCCESS").append("' order by txnDate desc");
		
	  return queryString;
		
		
	}
	
	
	
	/***
	 * to getAcctBasedHistory query
	 * 
	 * @param txn  CloudSvrTxn  object having all details to get acc base History query
	 * @return string represent the query
	 * 
	 * 
	 */	
	private StringBuilder getAcctBased(CloudSvrTxn txn){
		
		StringBuilder queryString = new StringBuilder(
				" from  CloudSvrTxn t  where t.customerId ='");

		queryString.append(txn.getCustomerId()).append("'");
		
		if (txn.getAcctype().equalsIgnoreCase(DaoConstants.CREDIT_CARD_TYPE )
				|| txn.getAcctype().equalsIgnoreCase(DaoConstants.DEBIT_CARD_TYPE) 
				|| txn.getAcctype().equalsIgnoreCase(DaoConstants.GIFT_CARD_TYPE) ){
			
			queryString.append("and t.cardNo ='")
			.append(txn.getCardNo()).append("'");
			
		}else if (txn.getAcctype().equalsIgnoreCase(DaoConstants.BANK_TYPE)){
			
			queryString.append("and t.bankAcc_No ='")
			.append(txn.getBankAcc_No()).append("'");
			
		}
	
		queryString.append(" and t.txnStatus = '").append("SUCCESS").append("' order by t.txnDate desc ");
		
		//queryString.append(" order by t.txnDate desc ");
		
		
		return queryString;
		
	}
	
	
	
	/***
	 * to  merchant details from omnypay potal 
	 * 
	 * @param posCon  CloudSvrQrCodeTxn  object having all details to get merchant details from omnypay potal 
	 * @return string represent the query
	 * 
	 * 
	 */	
	private StringBuilder  getMerchantDetail(CloudSvrQrCodeTxn posCon){
		
		
		StringBuilder queryString = new StringBuilder(
				" SELECT PS.posId AS POS_ID,PS.qrCode AS QR_CODE ,"
						+ "(select merchantId from CloudSvrTxnEntity where status=9376503 and Id=PS.mId) AS MID,"
						+ "(select merchantName from CloudSvrBusinessEntityInfo where status=9376503 and entityId=PS.mId) AS MNAME,"
						+ "(select merchantKey from CloudSvrBusinessEntityInfo where status=9376503 and entityId=PS.mId) AS MMKEY,"
						+ "(select address from CloudSvrDemographics where status=9376503 and entityId=PS.mId) AS address,"
						+ "(select address2 from CloudSvrDemographics where status=9376503 and entityId=PS.mId) AS address2,"
						+ "(select city from CloudSvrDemographics where status=9376503 and entityId=PS.mId) AS city,"
						+ "(select (select stateName  from CloudSvrPGState where status=9376503 and stateId=cd.state)  from CloudSvrDemographics cd where status=9376503 and entityId=PS.mId) AS mystate,"
						+ "(select (select countryName from CloudSvrPGCountry where status=9376503 and countryId=cd.country) from CloudSvrDemographics cd where status=9376503 and entityId=PS.mId) AS myconutryName,"
						+ "(select zipcode from CloudSvrDemographics where status=9376503 and entityId=PS.mId) AS myzipcode,"

						// +"(select cd.address AS MADDRESS1 ,cd.address2 AS MADDRESS2,cd.city AS mycity,"
						// not working
						// +"(select cd.address ,cd.address2 ,cd.city ,(select stateName  from CloudSvrPGState where status=9376503 and stateId=cd.state) , (select countryName from CloudSvrPGCountry where status=9376503 and countryId=cd.country)  from CloudSvrDemographics cd where status=9376503 and cd.entityId=PS.mId) as mmm"
						// +
						// " AS MADDRESS1 ,AS MADDRESS2 ,AS mycity ,AS MState,AS MConutry, "
						// +"(select countryName from CloudSvrPGCountry where status=9376503 and countryId=cd.country) AS MConutry ,"
						// +
						// " from CloudSvrDemographics cd where status=9376503 and entityId=PS.mId)"
						+ " PS.tId AS TERMINAL_ID,PS.storeId AS STORE_ID  FROM "
						+ " CloudSvrPosCon PS WHERE  STATUS=9376503 ");
	
		queryString.append(" and PS.posId = '").append(posCon.getPosId())
				.append("'");

		
		
		
		return queryString;
	}
	
	
	/////////////////////private methods for exception////////////////////////

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getHistoryDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getHistoryDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user report history ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_REPORT_HISTORY,
				accessException.getMessage());
		
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getHistoryDaoException(
			Exception ex) throws CloudDAException {
		String METHOD_NAME = "getHistoryDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while user report history ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_REPORT_HISTORY,
				ex.getMessage());
		
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getAcctBasedTransactionSummaryDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getAcctBasedTransactionSummaryDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user report history of account based")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY,
				accessException.getMessage());
		
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getAcctBasedTransactionSummaryDaoException(
			Exception ex) throws CloudDAException {
		String METHOD_NAME = "getAcctBasedTransactionSummaryDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user report history of account based")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY,
				ex.getMessage());
		
		
	}
	
	
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getMidFromDbDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getMidFromDbDaoDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while try to get merchant details  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO,
				accessException.getMessage());
		
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getMidFromDbDaoException(
			Exception accessException) throws CloudDAException {
		String METHOD_NAME = "getMidFromDbDaoException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while try to get merchant details  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO,
				accessException.getMessage());
		
		
	}
	
	
}
