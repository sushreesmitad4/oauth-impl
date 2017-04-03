package com.omnypay.common.services;

public final class WebServiceConstants {

	private WebServiceConstants(){
	}
	
	
	public static final String FIELDS_MISSING = "Mandatory fields are missing";	
	public static final String ONE = "01";
	public static final String TWO = "02";
	public static final String THREE = "03";
	public static final String FOUR = "04";
	public static final String FIVE = "05";
	public static final String SIX = "06";
	public static final String ADD_BANK_SUCCESS = "Bank Added Successfully"; 
	public static final String ADD_BANK_EXIST = "Bank Exist";
	public static final String ACCOUNT_DELETED = "Account Deleted Successfully"; 
	public static final String ACCOUNT_DELETED_HCE_FAIL = "Account deleted successfully but token not deleted"; 
	public static final String ACCOUNT_NOT_DELETED = "Account Cant Delete"; 
	public static final String ADD_CARD_SUCCESS = "Card Added Successfully"; 
	public static final String ADD_CARD_SUCCESS_HCE_FAIL = "Card added successfully but token not generated";
	public static final String ADD_BANK_SUCCESS_HCE_FAIL = "Bank added successfully but token not generated"; 
	public static final String ADD_CARD_EXIST = "Card Exist";
	public static final String ENTERED_FIELD_NOT_CORRECT = "Please check, entered field is not correct";
	public static final String GETTING_TOKEN_SUCCESS = "Get Token Successfully"; 
	public static final String GETTING_TOKEN__FAIL = "Get Token Failed"; 
	public static final String GETTING_CHECK_DETAILS_SUCCESS = "Get Check Details Successfully"; 
	public static final String GETTING_CHECK_DETAILS_FAILED = "Get Check Details failed"; 
	
	public static final String ACCESS_KEY_NOT_EXIST = "Please Provide Correct MerchantAccessKey";
	public static final String ENTITY_NULL = "Passed entity is null";
	public static final String FIELDS_ONETWO_MISSING = "One/more fields are missing";
	public static final String DELETE_ACCT = "Account deleted Successfully"; 
	public static final String DEFAULT_ACCT = "Default Account Set Successfully"; 
	public static final String DEFAULT_ACCT_NOT_SET = "Default Account Cant Set You have Already"; 
	public static final String FORGOT_PWD = "New Password link has been sent to your registered email address�. Please click the provided link to activate your account";
	public static final String RECORDS_FOUND = "Records found";
	public static final String RECORDS_NOT_FOUND = "Records not found";
	public static final String SECURITY_QUESTIONS_NOT_FOUND = "Security questions have not been set. Please contact customer care for further information";
	public static final String BUSINESS_CONTEXT = "businessContext";
	public static final String PROF_UPD_SUCCESS = "Profile updated successfully";
	public static final String PROF_UPD_SUCCESS_NOT = "Profile not updated";
	public static final String TRANSACTION_INITIATE_AMOUNT = "Transaction amount retrieved successfully";
	public static final String TRANSACTION_INITIATE_AMOUNT_NOT = "Transaction amount not retrieved";
	public static final String TRANSACTION_PROCESS = "Successfully payment has been done.";
	public static final String TRANSACTION_PROCESS_NOT = "Payment Not done";
	public static final String TRANSACTION_PROCESS_NOT_RRN = "Payment Not done becasue of RRN Number";
	public static final String TRANSACTION_PROCESS_NOT_PARSING = "Payment Not done parsing problem";
	public static final String TRANSACTION_INTIATE_NOT_PARSING = "Transaction amount not retrieved parsing problem";
	public static final String INVALID_QRCODE = "Invalid Qr Code";
	public static final String TRANSACTION_PROCESS_AUTH_FAIL = "Failed due to merchant intergration.";
	//log4j constants
	public static final String LOG_ENTRY = "Entering in to";
	public static final String LOG_EXIT = "Exiting from ";
	public static final String STR_COMMA = ",";
	public static final String LINESEPARATOR = System.getProperty("line.separator");
	public static final String LOG_ENTRY_ID = "Unique Id:";
	public static final String STR_EMPTY = "";
	public static final String ENCODED_FORMAT_UTF = "UTF-8";
	public static final String LOG4J_XML_FILE = "/log4j.xml";
	public static final String COMMONS_PROPERTY_FILE_PATH = "/commons-logging.properties";;
	public static final String PASSWORD_SENT = "New Password link has been sent to your registered email address";
	public static final String ANSWERS_NOT_MATHCED = "Entered answers are not matching";

	
	//Account
	public static final String CREDIT_CARD_TYPE = "1";
	public static final String DEBIT_CARD_TYPE = "2";
	public static final String GIFT_PRIVATE_LAB_CARD_TYPE = "3";
	public static final String BANK_TYPE = "0";
	//public static final String SERVICE_CODE = "1200";
	//crias say
	public static final String SERVICE_CODE = "101";
	
	public static final String CONNECTION_PROBLEM ="Connection problem";
	
	public static final String FIRSTCARD = "Y";
	
	public static final String IS_FIRST_CARD = "true";
	
	public static final String IS_FIRST_CARD_NOT = "false";
	
	public static final String EXIST = "EXIST";
	
	public static final String EXIST_NOT = "NOTEXIST";
	
	
	
	//SecurityQuestions
	public static final String SECURITY_QUESTIONS_ANSWERS_ADDED = "Security questions updated successfully";
	public static final String SECURITY_QUESTIONS_ANSWERS_ADDED_NOT = "Security questions not updated";
	public static final String WRONG_ATTEMPT_OF_SECURITY__ANSWERS = " wrong attempt of Security answers";
	
	
	
	
	//password change constant
	public static final String PASSWORD_CHANGED = "password changed successfully";
	
	 public static final String SECURE_PASSWORD_NOT_SENT ="Entered security answers does not match";
	 
	public static final String PASSWORD_ALREADY_EXIST = "New password Already Exist Please enter a new one";
	
	 public static final String PASSWORD_NOT_CORRECT = "Entered password is not correct";
	
	 public static final String PASSWORD_OLD_NOT_CORRECT = "Entered Old password is not correct";
	
	
	//USER
	 public static final String ACCOUNT_BLOCKED ="Account is blocked, Please Contact Customer Care at XXXX-XXX-XXXX";
	public static final String DEVICE_EXIST = "Device Already Registered";
	public static final String DEVICE_NOT_EXIST = "User is not Registered, Please Register. ";
	public static final String REGSTN_SUCCESS = "Registration Successful";
	public static final String USER_EXIST = "User  Already Registered";
	public static final String LOGIN_SUCCESS = "Login Successful";
	public static final String LOGIN_FAIL = "Login Failed";
	public static final String LOG_OUT = "User signed out";
	public static final String SIGN_OUT_NOT = "User not signed out";
	
	public static final String LOGIN_INVALID_CREDENETIAL = "Please Enter Valid Credentials";
	public static final String LOGIN_FAIL_ALREADYLOGIN = "User Already Logged In";
	public static final String LOGIN_LOCKED = "Your account is locked, please reset the password using Forgot Password or call at XXXX-XXX-XXXX";
	public static final String NUMBER_ZERO = "00";
	public static final String NUMBER_ONE = "01";
	public static final String NUMBER_TWO = "02";
	public static final String NUMBER_THREE = "03";
	public static final String NUMBER_FOUR = "04";
	public static final String NUMBER_FIVE = "05";
	public static final String NUMBER_SIX = "06";
	public static final String NUMBER_SEVEN = "07";
	public static final String NUMBER_EIGHT = "08";
	public static final String USER_NOT_REGISTER = "User is not Registered, Please Register. ";
	public static final String PARSER_EXCEPTION ="Fields Parsing Exception";
	 
	
	//HCE
	public static final String HCE_ONLINE_T_TYPE = "online";
	public static final String HCE_OFFLINE_T_TYPE = "offline";
	public static final String HCE_SUCCESS = "1";
	
	
	//Ecommerce
	public static final String AMOUNT_SAVED = "Amount saved Successfully";
	public static final String AMOUNT_NOT_SAVED  = "Amount Not saved successfully";
	public static final String AMOUNT_EXIST  = "Amount exist";
	public static final String BANK = "BANK";
	
	public static final String CARD = "CARD";
	
	public static final String NOT_APPLICABLE = "N/A";
	public static final String SALE = "sale";
	
	public static final String ECOMM = "ecomm";
	
	public static final String REQUEST_TYPE_INSERT = "insert";
	
	public static final String REQUEST_TYPE_UPDATE = "update";
	
	public static final String PROCESSING_CODE = "0000";
	
	public static final String NETWORK_ID = "11";
	
	public static final String POS_ENTRY_MODE = "10";
	
	public static final String MERCHANTID = "TESTMR512207164";
	
	public static final String INVOICE_NO = "123456";
	
	public static final String UPDATE_SWITCH_SERVER_SUCC_CODE = "00";
	
	public static final String CODE = "M00110";
	
	public static final String BATCH_ID = "1";
	
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public static final String DATE_FORMAT_MMM = "dd-MMM-yy";
	
	//public static final String DATE_FORMAT_TIMESTAMP = "dd/MM/yy HH:mm:ss aa";
	//public static final String DATE_FORMAT_TIMESTAMP = "MM/dd/yyyy hh.mm.ss aa";
	public static final String DATE_FORMAT_TIMESTAMP = "MM/dd/yyyy hh:mm:ss aa";
	
	public static final String MASTER_CARD = "Master";
	
	public static final String MERCHANT_ID = "TESTMR512207164";
	
	public static final String TERMINAL_ID = "12345678";
	
	public static final String SEMICOLON = ";";
	
	public static final String EQUAL_OPERATOR = "=";
	
	public static final String TERNURY_OPERATOR = "?";
	
	public static final String SEMICOLON_WITH_BACKSPACE = "\\;";
	
	public static final String DOT = ".";
	
	public static final String EMPTY_STRING = "";
	
	public static final String SINGLE_ZERO = "0";
	
	public static final String AMOUNT_FORMAT = "0.00";
	
	
    //dynamic Ip
	public static final String AMOUNT = "ipaddress";
	
	public static final String PAYNOW = "process payment from mobile";
	
	public static final String NO_REQUEST_FROM_MOBILE = "No Request from Mobile";
	
	public static final String COMFIRM_AMOUNT = "Confirm amount came from mobile";
	
	public static final String IS_PROCESS_REQ_YES = "Y";
	
	public static final String IS_PROCESS_REQ_NOT = "N";
	
	public static final String NOT_PROCESS_REQ = "N";
	
	public static final String IS_AMOUNT_REQ_YES = "Y";
	
	public static final String PROCESS_PAYMENT_NOT_DONE = "N";
	
	public static final String RRN_SAVED = "rrn number updated successfully";
	
	public static final String FIELD_UPDATED = "field updated successfully for TimeOut Request";
	
	public static final String RRN_NOT_SAVED = "rrn number not updated successfully";
	
	
	public static final String THREAD_SLEEP_TIME = "THREAD_SLEEP_TIME";
	
	
	public static final String PROCESS_WAIT = "processing";
	
	
	public static final String IS_ECOMMERCE_TXN = "Y";
	
	public static final String IS_NOT_ECOMMERCE_TXN = "N";
	
	public static final String YES = "Y";
	
	public static final String NO = "N";
	
	
	//5965 Direct Marketing - Combination Catalog and Retail Merchant
	// all the card type are same
	public static final String MERCHANT_CATEGORY_CODES = "5965";
	
	
	public static final String HCE_SERVER_ERROR ="Hce Server Error";
	public static final String COF_SERVER_ERROR ="Cof Server Error";
	public static final String CLOUD_SERVER_ERROR ="Cloud Server Error";
	
	
	//bean definations of services
	public static final String SECQUESTION_SERVICE = "secQuestionService";
	public static final String USER_SERVICE = "userService";
	public static final String PASSWORD_SERVICE = "passwordService";
	public static final String COF_SERVICE  = "cardOnFileService";
	public static final String COMMON_SERVICE = "commonService";
	public static final String HCE_SERVICE = "hceService";
	public static final String TXN_SERVICE = "txnService";
	public static final String POS_SERVICE = "posService";
	public static final String ACCOUNT_SERVICE  = "accountService";
	public static final String MITECK_SERVER_SERVICE = "mitekServerService";
	
	public static final String ACCOUNT_BANK_TYPE= "0";
	
	// exception
	
	/** The Constant COMMA. */
	public static final String COMMA = ",";
	
	public static final String REWARD_CALC_SUCCESS = "Reward calculation  successfully";
	
	public static final String REWARD_UPD_SUCCESS = "Reward updated successfully";
	
	public static final String REWARD_ADD_SUCCESS = "Reward added successfully";
	
	public static final String REWARD_ADD_FAILED = "Reward not added/updated due to internal error";
	
	public static final String DISCOUNT_SUCCESS= "Discount applied successfully";
	
	public static final String DISCOUNT_FAIELD= "Discount not applied";
	
	}



