package com.omnypay.business.util;

public final class BusinessConstants {

	private BusinessConstants() {
	}
	
	public static final String USER_ALREADY_EXISTS = "User already exists";
	public static final String INVALID_USER = "Invalid User";
	
	
	//card 
	
	public static final String CARD_ALREADY_EXISTS = "Card No  already exists";
	public static final String CARD_DEFAULT_NO = "N";
	public static final String CARD_DEFAULT_YES = "N";
	public static final String CARD_TYPE = "CARD_TYPE";
	public static final String BANK_TYPE = "BANK_TYPE";
	
	//Account
	public static final String CREDIT_CARD_TYPE = "1";
	public static final String DEBIT_CARD_TYPE = "2";
	public static final String GIFT_CARD_TYPE = "3";
	public static final String ACH_BANK_TYPE = "0";
	
	
	
	// user
	public static final String ZERO = "0";
	public static final String ISLOGOUT = "N";
	public static final String ISLOGIN = "Y";
	public static final String ISLOCKED = "Y";
	public static final String ISUNLOCKED = "N";
	
	
	//Password
	public static final String PASSWORD_CHANGE_TYPE = "User";
	
	//service DAO 
	public static final String DAO = "DAO";
	public static final String QUERY_APPENDER = ",";
	
	//HSM
	public static final String DECREQSTRING = "decReqString"; 
	
	public static final String HSM_DEC_URL  = "HSM_DEC_URL";
	
	
	// common constants
	public static final String ONE = "01";
	public static final String TWO = "02";
	public static final String THREE = "03";
	public static final String FOUR = "04";
	public static final String FIVE = "05";
	public static final String SIX = "06";
	public static final String SEVEN = "07";
	public static final String EIGHT = "08";
	
	
	
	
	
	
	//Property constants
	public static final String FILE_PATH = "FilePath :";
		
	public static final String PROFILE_PATH = "propfilepath";
	
	

	//JSON CONSTANT
	public static final String JSON_HEADER_TYPE = "application/json";
	
    public static final String HCE_STSTUS_CODE = "statusCode";
	
	public static final String TOKENS = "tokens";
	
	public static final String TOKEN_EXP_DATE = "tokenExpDate";
	
	public static final String UDK = "udk";
	
	//POS
	
	public static final String ERR_MSG = "ERR_MSG";
	public static final String MESSAGE = "message";
	public static final String SWITCH_TXN_DATE = "switchTxnDate";
	
	
	// exception
	
	/** The Constant COMMA. */
	public static final String COMMA = ",";
	
		
	/** The Constant ERROR_MSG_STR. */
	 public static String ERROR_MSG_STR = "Error Message : ";
	 
	 	/** The Constant ERROR_CODE_STR. */
		 public static String ERROR_CODE_STR = "Error Code : ";
		 
		 
 //BANK/CARD
 
			
public static final String NUMBER_ONE = "1";
			
public static final String NUMBER_TWO = "2";
			
			
public static final String BANK = "bank";
			
public static final String DEBIT = "debit";
			
public static final String CREDIT = "credit";




public static final String COF_SERVICE_ADD_CARD_URL = "COF_URL_ADD_CARD";

public static final String COF_SERVICE_ADD_BANK_URL = "COF_URL_ADD_BANK";

public static final String COF_SERVICE_UPDATE_URL = "COF_URL_UPDATE";

public static final String COF_SERVICE_DELETE_URL = "COF_URL_DELETE";

public static final String COF_SERVICE_GETACCOUNT_DETAILS_URL = "COF_URL_GET_ACCOUNT_DETAILS";

public static final String COF_SERVICE_GETACCOUNT_URL = "COF_URL_GET_ACCOUNT";

//public static final String COF_SERVICE_GETACCOUNT_TYPE_URL = "COF_URL_GET_ACCOUNT_TYPE";

public static final String SWH_SERVICE_URL="SWH_SERVICE_URL";

public static final String HCE_SERVICE_GET_ONLINE_TOKEN_URL = "HCE_SERVICE_GET_ONLINE_TOKEN_URL";

public static final String HCE_SERVICE_DELETE_ONLINE_TOKEN_URL = "HCE_SERVICE_DELETE_ONLINE_TOKEN_URL";



public static final String POS_SERVICE_URL = "POS_SERVICE_URL";

public static final String POS_SERVICE_URL_CONFIRM_AMOUNT = "POS_SERVICE_URL_CONFIRM_AMOUNT";

public static final String HCE_SERVICE_GET_OFFLINE_TOKEN_FOR_ALLCARDS_URL="HCE_SERVICE_GET_OFFLINE_TOKEN_FOR_ALLCARDS_URL";

public static final String REQ_TYPE = "01";


public static final String HCE_ONLINE_T_TYPE = "online";

public static final String HCE_OFFLINE_T_TYPE = "offline";

public static final String HCE_STATUS_CODE_SUCCESS = "1";

public static final String POS_STATUS_CODE_SUCCESS = "00";

public static final String POS_PAYMENTSTATUS_CODE_SUCCESS = "01";

public static final String POS_STATUS_CODE_FAIL = "01";

public static final String COF_STATUS_CODE_SUCCESS = "1";


//Account

public static final String ACCOUNT_BANK_TYPE= "0";

public static final String IS_FIRST_CARD= "true";

public static final String REQ_TYPE_PROCESS_BANK = "02";

public static final String TXN_TYPE_PROCESS_BANK = "02";

public static final String REQ_TYPE_PROCESS_CARD = "02";

public static final String TXN_TYPE_PROCESS_CARD = "01";

public static final String REQUEST_TYPE_INSERT = "insert";

public static final String REQUEST_TYPE_UPDATE = "update";




//CardOnFileConstants
public static final String TYPE = "type";

public static final String ACCOUNT_ID = "accountId";

public static final String ISFIRSTCARD = "isFirstCard";

public static final String YES = "Y";

public static final String ACC_TRANSACTION_PASSWORD = "accTransactionPwd";

public static final String ACC_BRAND = "accBrand";

public static final String ACCT_TYPE = "acctype";

public static final String REQUESTED_DATA = "requestedData";

//public static final String TOKENS = "tokens";






public static final String ACC_ID = "accId";

public static final String USER_ID = "userId";

public static final String ACC_HOLDER_NAME = "accHolderName";

public static final String ACC_TYPE = "accType";

public static final String ACC_NO = "accNo";

public static final String ACC_BANKNAME = "accBankName";

public static final String ACC_BANK_ROUT = "accBankRout";

public static final String ACC_BANK_TYPE = "accBankType";

public static final String ACC_STREET = "accStreet";

public static final String ACC_CITY = "accCity";

public static final String ACC_STATE = "accState";

public static final String ACC_ZIPCODE = "accZipcode";

public static final String ACC_CARD_CVVCODE = "accCardCvvCode";

public static final String ACC_CARD_EXPDATE = "accCardExpDate";

public static final String CARD_NICKNAME = "cardNickName";

public static final String ACCOUNT = "account";

public static final String ACCOUNT_REFID = "accountRefId";







//POS
public static final String RSP_CODE = "RSP_CODE";

public static final String RRN = "RRN";

public static final String AMOUNT = "AMOUNT";

public static final String AMOUNT_FORMAT = "0.00";

public static final String POSID = "POSID";



//e-commerce 

public static final String ECOMMERCE_RES_STRING = "ecommResString"; 

public static final String ECOMMERCE_URL  = "ECOMMERCE_URL";



//mitech userName

public static final String MITECH_USERNAME = "MITECH_USERNAME";
	
public static final String MITECH_PASSWORD = "MITECH_PASSWORD";

public static final String MITECH_PHONE_KEY = "MITECH_PHONE_KEY";

public static final String MITECH_ORG_NAME = "MITECH_ORG_NAME";	
	
public static final String MITECH_SERVER_URL = "MITECH_SERVER_URL";
	




	
}
