/*
 * This code contains copyright information which is the proprietary property
 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Author : Bhanu Pratap 
 * Description:This Interface  handles displays the APCConstants  functionality.
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package org.omnypay.email.service;

/**
 * This Interface handles displays the APCConstants functionality.
 */
public interface APCConstants
{
	/** The Constant LOG_ENTRY. */
	public static final String LOG_ENTRY = "Entering in to ";

	/** The Constant LOG_EXIT. */
	public static final String LOG_EXIT = "Exiting from ";

	/** The Constant COMMA_SPACE. */
	public static final String COMMA_SPACE = " , ";

	/** The Constant COMMA. */
	public static final String COMMA = ",";

	/** The Constant ERROR_CODE_STR. */
	public static final String ERROR_CODE_STR = "Error Code : ";

	/** The Constant ERROR_MSG_STR. */
	public static final String ERROR_MSG_STR = "Error Message : ";

	/** The Constant BANK_ENCRYPTION. */
	public static final String BANK_ENCRYPTION = "BANK_ENCRYPTION";

	/** The Constant BANK_TYPE. */
	public static final String BANK_TYPE = "BANK_TYPE";
	/** The Constant COMMA. */
	public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

	/** The Constant BANK_DECRYPTION. */
	public static final String BANK_DECRYPTION = "BANK_DECRYPTION";

	/** The Constant DEFAULT_USER. */
	public static final String DEFAULT_USER = "DEFAULT_USER";

	/** The Constant BANK_ENCRYPTION_IDS. */
	public static final String BANK_ENCRYPTION_IDS = "BANK_ENCRYPTION_IDS";

	/** The Constant BANK_DECRYPTION_IDS. */
	public static final String BANK_DECRYPTION_IDS = "BANK_DECRYPTION_IDS";

	/** The Constant BANK_TYPE_IDS. */
	public static final String BANK_TYPE_IDS = "BANK_TYPE_IDS";

	/** The Constant BANK_INFO. */
	public static final String BANK_INFO = "BANK_INFO";

	/** The Constant BANK_SECURITY_INFO. */
	public static final String BANK_SECURITY_INFO = "BANK_SECURITY_INFO";

	/** The Constant BANK_TYPE_INFO. */
	public static final String BANK_TYPE_INFO = "BANK_TYPE_INFO";

	/** The Constant CHANNEL_INFO_LIST. */
	public static final String CHANNEL_INFO_LIST = "CHANNEL_INFO_LIST";

	/** The Constant CHANNEL_ID_LIST. */
	public static final String CHANNEL_ID_LIST = "CHANNEL_ID_LIST";

	/** The Constant CHANNEL_COM_LIST. */
	public static final String CHANNEL_COM_LIST = "CHANNEL_COM_LIST";

	/** The Constant CHANNEL_PAY_LIST. */
	public static final String CHANNEL_PAY_LIST = "CHANNEL_PAY_LIST";

	/** The Constant CHANNEL_AUTH_LIST. */
	public static final String CHANNEL_AUTH_LIST = "CHANNEL_AUTH_LIST";

	/** The Constant BANK_CHANNEL_INFO_LIST. */
	public static final String BANK_CHANNEL_INFO_LIST = "BANK_CHANNEL_INFO_LIST";

	/** The Constant ACTIVE. */
	public static final String ACTIVE = "ACTIVE";

	/** The Constant BANK_IDS. */
	public static final String BANK_IDS = "BANK_IDS";

	/** The Constant BANK_NAMES. */
	public static final String BANK_NAMES = "BANK_NAMES";

	/** The Constant PROCESSOR_INFO_CONFIG_VO. */
	public static final String PROCESSOR_INFO_CONFIG_VO = "PROCESSOR_INFO_CONFIG_VO";

	/** The Constant NB_VALUE. */
	public static final Long NB_VALUE = 345678908L;

	/** The FIL e_ pat h_ cn t_ er r_ prop. */
	public static String FILE_PATH_CNT_ERR_PROP = "/ControllerError.properties";
	/** The FIL e_ pat h_ cn t_ er r_ prop. */
	public static String FILE_PATH_ERR_PROP = "/ErrorCode.properties";
	/** The FIL e_ pat h_ ser v_ er r_ prop. */
	public static String FILE_PATH_SERV_ERR_PROP = "/ServiceError.properties";

	/** The FIL e_ pat h_ da o_ er r_ prop. */
	public static String FILE_PATH_DAO_ERR_PROP = "/DAOError.properties";

	public static String FILE_PATH_PROJ_PROP = "/project.properties";

	/** The log repeater. */
	public final String logRepeater = "#";

	/** The line separator. */
	public final String lineSeparator = System.getProperty("line.separator");

	/** The Constant SUCCESS_CODE_STR. */
	public static final long SUCCESS_CODE_STR = 0;

	/** The Constant SUCCESS_MSG_STR. */
	public static final String SUCCESS_MSG_STR = "SUCCESS";

	/** The Constant JSP_DATE_FORMAT. */
	public static final String JSP_DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant DB_DATE_FORMAT. */
	public static final String DB_DATE_FORMAT = "dd-MMM-yy";

	/** The Constant BLOCK_SCOPE_LIST. */
	public static final String BLOCK_SCOPE_LIST = "BLOCK_SCOPE_LIST";

	/** The Constant BLOCK_TYPE_LIST. */
	public static final String BLOCK_TYPE_LIST = "BLOCK_TYPE_LIST";

	/** The Constant BLOCK_CATEGORY_LIST. */
	public static final String BLOCK_CATEGORY_LIST = "BLOCK_CATEGORY_LIST";

	/** The Constant TXN_HISTORY_EXCEL. */
	public static final String TXN_HISTORY_EXCEL = "Transaction History Report";

	/** The Constant TXN_HISTORY_EXCEL_FILE_NAME. */
	public static final String TXN_HISTORY_EXCEL_FILE_NAME = "Transaction_History.xls";

	// Login Information
	/** The Constant lOGIN_ACTION_PERFORMED. */
	public static final String lOGIN_ACTION_PERFORMED = "Login";

	/** The Constant lOGOUT_ACTION_PERFORMED. */
	public static final String lOGOUT_ACTION_PERFORMED = "LogOut";

	/** The Constant USR_AGENT. */
	public static final String USR_AGENT = "User-Agent";

	/** The Constant LOG_FILES_PATH. */
	public static final String LOG_FILES_PATH = "logs.Files.Path";;

	public static final String ENCODED_FORMAT_UTF = "UTF-8";

	public static final String BG_Color_List = "bgColorList";
	public static final String Font_Style_List = "fontStyleList";
	public static final String SETTLE_SERVICE_SERVER_URL = "settle.service.server.url";
	public static final String Font_Size_List = "fontSizeList";
	public static final String Primry_Text_ClrList = "primryTextClrList";
	public static final String BOTTON_COLOR_LIST = "bottonClrList";
	public static final String iFrame_Url = "iframe.service.server.url";

	// Refund Request Param Fields
	public static final String REQ_PARMTR_JSON = "JsonData";
	public static final String PF_REQ_MERCH_ID = "MerchantID";
	public static final String PF_REQ_TXNID = "PgTxnId";
	public static final String PF_REQ_PROCESSOR_MID = "processormId";
	public static final String PF_REQ_PROCESSOR_TXNID = "nPgTxnId";
	public static final String PF_REQ_TXN_AMT = "txnAmt";
	public static final String PF_REQ_REFUND_AMOUNT = "RefundAmount";
	public static final String PF_REQ_RF_COMMENTS = "Comment";
	public static final String PF_TXN_TYPE = "TxnType";
	public static final String PF_ORDER_ID = "OrderID";
	public static final String PF_TXN_TYPE_SETTLE = "Settle";
	public static final String PF_TXN_TYPE_REFUND = "Refund";
	public static final String PF_TXN_TYPE_CANCEL = "Cancel";
	public static final String PF_REQ_SETTLE_AMOUNT = "Amount";
	public static final String PF_CARD_STORAGE_ID = "CardStorageId";

	// Refund Response Params
	public static final String PF_RES_RESPONSE_CODE = "Response Decision";
	public static final String PF_RES_RESPONSE_MSG = "Response Text";

	// public static String STR_COMMA = ",";
	public static final String STR_ERROR_CODE = "Error Code :";
	public static final String STR_ERROR_MSG = "Error Message :";

	// Json Error Constants
	public static final String JSN_ERR_CODE = "ErrorCode";
	public static final String JSN_ERR_MSG = "ErrorMsg";

	// This has been declared for Refund,where ICICI support online Refund
	public static final String PROCESSOR_NAME_NPG = "NPG";

	// APC Constants
	public static final String O_ERROR_CODE = "O_ERROR_CODE";
	public static final String O_ERROR_MSG = "O_ERROR_MSG";
	public static final String O_ERROR_CODE1 = "o_error_code";
	public static final String O_ERROR_MSG1 = "o_error_msg";

	// APC Constants for LANGUAGE SP
	public static final String GETLANGUAGELIST = "GETLANGUAGELIST";
	public static final String I_LANGUAGE_ID = "I_LANGUAGE_ID";
	public static final String I_LANGUAGE_CODE = "I_LANGUAGE_CODE";
	public static final String O_LANGUAGE_INFO = "O_LANGUAGE_INFO";
	public static final String LANGUAGE_ID = "language_id";
	public static final String LANGUAGE_CODE = "language_code";
	public static final String LANGUAGE_DESCRIPTION = "language_description";
	public static final String ACCESS_FLAG = "access_flag";

	// APC Constants for Currency SP
	public static final String GETCURRENCYLIST = "GETCURRENCYLIST";
	public static final String I_CURRENCY_ID = "I_CURRENCY_ID";
	public static final String I_CHAR_CODE = "I_CHAR_CODE";
	public static final String I_PRCSR_PYMNT_MTHD_ID_LIST = "I_PRCSR_PYMNT_MTHD_ID_LIST";
	public static final String O_CURRENCY_INFO = "O_CURRENCY_INFO";
	public static final String I_PRCSR_PYMNT_MTHD_ID = "I_PRCSR_PYMNT_MTHD_ID";
	public static final String I_CREATED_BY = "I_CREATED_BY";
	public static final String I_CREATED_DATE = "I_CREATED_DATE";
	public static final String I_MODIFIED_BY = "I_MODIFIED_BY";
	public static final String I_MODIFIED_DATE = "I_MODIFIED_DATE";
	public static final String PROCESSOR_URL_PRIMARY_REQUEST = "PROCESSOR_URL_PRIMARY_REQUEST";
	public static final String PROCESSOR_URL_PRIMARY_RESPONSE = "PROCESSOR_URL_PRIMARY_RESPONSE";
	public static final String PROCESSOR_URL_SECONDARY_REQUEST = "PROCESSOR_URL_SECONDARY_REQUEST";
	public static final String PROCESSOR_URL_SECONDARY_RESPONSE = "PROCESSOR_URL_SECONDARY_RESPONSE";
	public static final String CURRENCY_ID = "currency_id";
	public static final String CURRENNCY_ID = "currenncy_id";
	public static final String CHAR_CODE = "char_code";
	public static final String MODIFIED_BY = "modified_by";
	public static final String MODIFIED_DATE = "modified_date";
	public static final String PRCSR_PYMNT_MTHD_CRNCY_CONF_ID = "prcsr_pymnt_mthd_crncy_conf_id";
	public static final String PRCSR_PYMNT_MTHD_CONF_ID = "prcsr_pymnt_mthd_conf_id";

	public static final String I_PRCSR_PYMNT_MTHD_CONF_ID = "I_PRCSR_PYMNT_MTHD_CONF_ID";
	public static final String I_CURRENCY_ID_LIST = "I_CURRENCY_ID_LIST";
	public static final String O_PROC_PYMNT_MTHD_CRNCY_CNF_ID = "O_PROC_PYMNT_MTHD_CRNCY_CNF_ID";
	public static final String O_CURRENCY_ID_LIST = "O_CURRENCY_ID_LIST";

	// APC Constants for Country SP
	public static final String GETCOUNTRYLIST = "GETCOUNTRYLIST";
	public static final String I_COUNTRY_ID = "I_COUNTRY_ID";
	public static final String I_COUNTRY_NAME = "I_COUNTRY_NAME";
	public static final String O_COUNTRY_INFO = "O_COUNTRY_INFO";
	public static final String COUNTRY_ID = "country_id";
	public static final String COUNTRY_NAME = "country_name";
	public static final String COUNTRY_CODE = "country_code";
	public static final String COUNTRY_AREA_CODE = "country_area_code";
	// APC Constants for TimeZone SP
	public static final String GETTIMEZONELIST = "GET_SYS_TIMEZONE_LIST";
	public static final String I_TIMEZONE_ID = "I_TIMEZONE_ID";
	public static final String I_TIMEZONE_N = "I_TIMEZONE_N";
	public static final String O_TIMEZONE_INFO_LIST = "O_TIMEZONE_INFO_LIST";
	public static final String ID = "id";
	public static final String TIMEZONE_N = "timezone_n";
	public static final String TIMEZONE_CODE = "timezone_code";
	public static final String OFFSET_UTC = "offset_utc";
	public static final String TIMEZONE_VALUE = "timezone_value";

	// APC Constants for Processor SP
	public static final String GET_ALL_PROCESSOR_INFO = "GET_ALL_PROCESSOR_INFO";
	public static final String GET_PROC_PYMNT_MTHD_LIST = "GET_PROC_PYMNT_MTHD_LIST";
	
	public static final String O_PROC_COMM_MODE_LIST = "O_PROC_COMM_MODE_LIST";
	public static final String I_PROCESSOR_ID = "I_PROCESSOR_ID";
	public static final String O_PROCESSOR_INFO_LIST = "O_PROCESSOR_INFO_LIST";
	public static final String O_PROC_PYMNT_MTHD_LIST = "O_PROC_PYMNT_MTHD_LIST";
	public static final String O_PROC_LIST = "O_PROC_LIST";
	public static final String PAYMENT_METHOD_ID = "payment_method_id";
	public static final String I_PROCESSOR_NAME = "I_PROCESSOR_NAME";
	public static final String I_PROCESSOR_CODE = "I_PROCESSOR_CODE";
	public static final String I_PROC_PAYMENT_METHOD_ID_LIST = "I_PROC_PAYMENT_METHOD_ID_LIST";
	public static final String I_EMAIL_ID = "I_EMAIL_ID";
	public static final String I_COMM_MODE_LIST = "I_COMM_MODE_LIST";
	public static final String I_PRIMARY_REQ_URL = "I_PRIMARY_REQ_URL";
	public static final String I_PRIMARY_RES_URL = "I_PRIMARY_RES_URL";
	public static final String I_SECONDARY_REQ_URL = "I_SECONDARY_REQ_URL";
	public static final String I_SECONDARY_RES_URL = "I_SECONDARY_RES_URL";
	public static final String I_COMM_MODE = "I_COMM_MODE";
	public static final String I_PRIMARY_REQURL = "I_PRIMARY_REQURL";
	public static final String I_PRIMARY_RESURL = "I_PRIMARY_RESURL";
	public static final String I_SECONDARY_REQURL = "I_SECONDARY_REQURL";
	public static final String I_SECONDARY_RESURL = "I_SECONDARY_RESURL";
	public static final String I_COUNTYR_ID = "I_COUNTYR_ID";
	public static final String I_PAYMETHOD_ID = "I_PAYMETHOD_ID";
	public static final String I_USER_ID = "I_USER_ID";
	public static final String O_PROCESSOR_ID = "O_PROCESSOR_ID";
	public static final String PROC_PYMNT_MTHD_ID = "proc_pymnt_mthd_id";
	public static final String O_PROC_PYMNT_MTHD_CONF_LIST = "O_PROC_PYMNT_MTHD_CONF_LIST";
	public static final String O_PRO_COMM_MODE_INFO_LIST = "O_PRO_COMM_MODE_INFO_LIST";
	public static final String O_PROCESSOR_URL_INFO = "O_PROCESSOR_URL_INFO";
	public static final String EMAIL_ID = "email_id";
	public static final String CATEGORY = "category";
	public static final String SUB_CATEGORY = "sub_category";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String DESCRIPTION = "description";
	public static final String PROC_PYMNT_METH_ID = "proc_pymnt_meth_id";
	public static final String PROCESSOR_ID = "processor_id";
	public static final String PROCESSOR_CODE = "processor_code";
	public static final String PROCESSOR_NAME = "processor_name";
	public static final String CONTACT_NAME = "contact_name";
	public static final String country_id = "country_id";
	public static final String PROC_PMNT_MTHD_CNF_ID = "proc_pmnt_mthd_cnf_id";
	public static final String PROC_COMM_MODE_ID = "proc_comm_mode_id";
	public static final String COMM_MODE_ID = "comm_mode_id";
	public static final String COMM_MODE = "comm_mode";
	public static final String PROCESSOR_URL_INFO_ID = "processor_url_info_id";
	public static final String URL_TYPE_NAME = "url_type_name";
	public static final String URL = "url";
	public static final String URL_TYPE_ID = "url_type_id";
	public static final String PAYMENT_METHOD_NAME = "payment_method_name";
	public static final String I_SETTLE_CURR_ID_LIST = "i_settle_curr_id_list";
	public static final String PYMNT_CURR_TYPE = "pymnt_curr_type";
	public static final String I_TXN_CURRENCY_ID = "i_txn_currency_id";
	public static final String I_CARD_TYPE_ID = "i_card_type_id";
	public static final String I_TXN_TYPE_ID = "i_txn_type_id";
	public static final String O_FEE_DETAILS = "o_fee_details";
	public static final String TXN_CURRENCY = "txn_currency";
	public static final String CURRENCY_TYPE_ID = "currency_type_id";
	public static final String PAYMENT_OPTION_IDENTIFIER = "payment_option_identifier";
	public static final String FEE_CURRENCY = "fee_currency";
	public static final String FEE_CURRENCY_ID = "fee_currency_id";
	public static final String CATEGORY_TYPE = "category_type";
	public static final String CATEGORY_TYPE_ID = "category_type_id";
	public static final String TXN_TYPE = "txn_type";
	public static final String TXN_TYPE_ID = "txn_type_id";
	public static final String FEE_TYPE_IDENTIFIER = "fee_type_identifier";
	public static final String FEE_TYPE = "fee_type";
	public static final String FLAT_FEE = "flat_fee";
	public static final String PERCENTAGE_FEE = "percentage_fee";
	public static final String STARTRANGE = "startrange";
	public static final String ENDRANGE = "endrange";
	public static final String RANGETYPE = "rangetype";
	public static final String APPLICABLE_MONTH = "applicable_month";

	// APC Constants for ProcessorFee
	public static final String GET_PROCESSOR_FEE_DETAILS = "GET_PROCESSOR_FEE_DETAILS";
	public static final String INSERT_PROCESSOR_FEE_DETAILS = "INSERT_PROCESSOR_FEE_DETAILS";
	public static final String O_BANK_FEE_DETAILS = "O_BANK_FEE_DETAILS";
	public static final String I_CURRENCY_TYPE = "I_CURRENCY_TYPE";
	public static final String I_CATEGORY_TYPE = "I_CATEGORY_TYPE";
	public static final String I_FEE_TYPE = "I_FEE_TYPE";
	public static final String I_FLAT_FEE = "I_FLAT_FEE";
	public static final String I_PERCENTAGE_FEE = "I_PERCENTAGE_FEE";
	public static final String I_SIMPLE_RADIO_BTN = "I_SIMPLE_RADIO_BTN";
	public static final String I_SIMPLE_BOTH = "I_SIMPLE_BOTH";
	public static final String I_START_RANGE1 = "I_START_RANGE1";
	public static final String I_END_RANGE1 = "I_END_RANGE1";
	public static final String I_FLAT_FEE_AMT1 = "I_FLAT_FEE_AMT1";
	public static final String I_RANG_FEE_PERCENTAG1 = "I_RANG_FEE_PERCENTAG1";
	public static final String I_CHECK_BOX_CONDITION_BOTH1 = "I_CHECK_BOX_CONDITION_BOTH1";
	public static final String I_START_RANGE2 = "I_START_RANGE2";
	public static final String I_END_RANGE2 = "I_END_RANGE2";
	public static final String I_FLAT_FEE_AMT2 = "I_FLAT_FEE_AMT2";
	public static final String I_RANG_FEE_PERCENTAG2 = "I_RANG_FEE_PERCENTAG2";
	public static final String I_CHECK_BOX_CONDITION_BOTH2 = "I_CHECK_BOX_CONDITION_BOTH2";
	public static final String I_START_RANGE3 = "I_START_RANGE3";
	public static final String I_END_RANGE3 = "I_END_RANGE3";
	public static final String I_FLAT_FEE_AMT3 = "I_FLAT_FEE_AMT3";
	public static final String I_RANG_FEE_PERCENTAG3 = "I_RANG_FEE_PERCENTAG3";
	public static final String I_CHECK_BOX_CONDITION_BOTH3 = "I_CHECK_BOX_CONDITION_BOTH3";
	public static final String I_START_RANGE4 = "I_START_RANGE4";
	public static final String I_END_RANGE4 = "I_END_RANGE4";
	public static final String I_FLAT_FEE_AMT4 = "I_FLAT_FEE_AMT4";
	public static final String I_RANG_FEE_PERCENTAG4 = "I_RANG_FEE_PERCENTAG4";
	public static final String I_CHECK_BOX_CONDITION_BOTH4 = "I_CHECK_BOX_CONDITION_BOTH4";
	public static final String I_START_RANGE5 = "I_START_RANGE5";
	public static final String I_END_RANGE5 = "I_END_RANGE5";
	public static final String I_FLAT_FEE_AMT5 = "I_FLAT_FEE_AMT5";
	public static final String I_RANG_FEE_PERCENTAG5 = "I_RANG_FEE_PERCENTAG5";
	public static final String I_CHECK_BOX_CONDITION_BOTH5 = "I_CHECK_BOX_CONDITION_BOTH5";
	public static final String I_APPLICABLE_MONTH = "i_applicable_month";

	// APC Constants for State SP
	public static final String GETSTATELIST = "GETSTATELIST";
	public static final String I_STATE_ID = "I_STATE_ID";
	public static final String I_STATE_NAME = "I_STATE_NAME";
	public static final String O_STATE_INFO = "O_STATE_INFO";
	public static final String STATE_ID = "state_id";
	public static final String STATE_NAME = "state_name";
	public static final String STATE_CODE = "state_code";
	// APC Constants for getBankList SP
	public static final String GET_ALL_SALES_CH_BANK_INFO = "GET_ALL_SALES_CH_BANK_INFO_UPDATE";
	public static final String I_SALE_CHANNEL_BANK_ID = "I_SALE_CHANNEL_BANK_ID";
	public static final String I_BANK_ID = "I_BANK_ID";
	public static final String I_STATUS = "I_STATUS";
	public static final String I_BANK_NAME = "I_BANK_NAME";
	public static final String I_COUNTRY = "I_COUNTRY";
	public static final String O_SALE_CH_BANK_INFO_LIST = "O_SALE_CH_BANK_INFO_LIST";
	public static final String SALES_CH_BANK_MASTER_ID = "sales_ch_bank_master_id";
	public static final String BANK_NAME = "bank_name";
	public static final String BANK_CODE = "bank_code";
	public static final String CONTACT_PERSON = "contact_person";
	public static final String CONTACT_NUMBER = "contact_number";
	public static final String EMAIL = "email";
	public static final String DEMOGRAPHICS_ID = "demographics_id";
	public static final String ADDRESS_1 = "address_1";
	public static final String ADDRESS_2 = "address_2";
	public static final String CITY_NAME = "city_name";
	public static final String ZIPCODE = "zipcode";
	public static final String TIMEZONE = "timezone";
	public static final String I_SPONSORED = "I_SPONSORED";
	public static final String SPONSOR_BANK = "sponsor_bank";

	// APC Constants for user role SP
	public static final String I_ROLE_NAME = "I_ROLE_NAME";
	public static final String ROLE_GROUP_ID = "role_group_id";
	public static final String I_DESCRIPTION = "I_DESCRIPTION";
	public static final String O_ROLE_LIST = "O_ROLE_LIST";
	public static final String O_ROLE_INFO_LIST = "O_ROLE_INFO_LIST";
	public static final String O_USER_INFO_LIST = "O_USER_INFO_LIST";
	public static final String O_PERMISSIONS_LIST = "O_PERMISSIONS_LIST";
	public static final String I_ROLE_DESC = "I_ROLE_DESC";
	public static final String O_HIERARCHY_INFO_LIST = "O_HIERARCHY_INFO_LIST";
	public static final String O_RESP_CUR = "O_RESP_CUR";
	public static final String I_GROUP_ID = "I_GROUP_ID";
	public static final String I_PERMISSION_ID_LIST = "I_PERMISSION_ID_LIST";
	public static final String ROLEGROUP_ID = "rolegroup_id";
	public static final String ENT_ID = "ent_id";
	public static final String ENT_NAME = "ent_name";
	public static final String ENT_CODE = "ent_code";
	public static final String O_ROLE_INFO = "O_ROLE_INFO";
	public static final String I_HEIRACY_ID = "I_HEIRACY_ID";
	public static final String O_ENTITY_INFO = "O_ENTITY_INFO";
	public static final String ROLE_ID = "role_id";
	public static final String ROLE_NAME = "role_name";
	public static final String SUBROLE_NAME = "subrole_name";
	public static final String ROLE_DESCRIPTION = "role_description";
	public static final String SUBROLE_DESCRIPTION = "subrole_description";
	public static final String CREATED_BY = "created_by";
	public static final String permission_id = "permission_id";
	public static final String CREATED_DATE = "created_date";
	public static final String I_ROLE_ID = "I_ROLE_ID";
	public static final String I_USER_NAME = "I_USER_NAME";
	public static final String I_CONTACT_NO = "I_CONTACT_NO";
	public static final String I_FIRST_NAME = "I_FIRST_NAME";
	public static final String I_LAST_NAME = "I_LAST_NAME";
	public static final String I_ROLEGROUP_ID = "I_ROLEGROUP_ID";
	public static final String I_ENTITY_ID = "I_ENTITY_ID";
	public static final String I_ADDRESS_1 = "I_ADDRESS_1";
	public static final String I_ADDRESS_2 = "I_ADDRESS_2";
	public static final String I_PIN_CODE = "I_PIN_CODE";
	public static final String I_PHONE_NUMBER = "I_PHONE_NUMBER";
	public static final String I_TIME_ZONE_ID = "I_TIME_ZONE_ID";
	public static final String I_LOGIN_USER = "I_LOGIN_USER";
	public static final String I_PASSWORD = "I_PASSWORD";
	public static final String O_USR_ID = "O_USR_ID";
	public static final String I_HIERARCHY_ID = "I_HIERARCHY_ID";
	public static final String PERMISSION_ID = "permission_id";
	public static final String GROUP_ID = "group_id";
	public static final String GROUP_DESCRIPTION = "group_description";
	public static final String GROUP_NAME = "group_name";
	public static final String USER_ID = "usr_loginname";
	public static final String ENTITY_NAME = "entity_name";
	public static final String API_USR_ID = "api_user_id";
	public static final String USR_LOGINNAME = "usr_loginname";
	public static final String subrole_name = "subrole_name";
	public static final String subrole_description = "subrole_description";
	public static final String USR_EMAIL = "usr_email";
	public static final String PHONE_NUMBER = "phone_number";
	public static final String country = "country";
	public static final String I_FUNCTION = "I_FUNCTION";

	public static final String USERID = "user_id";
	public static final String HIERARCHY_ID = "hierarchy_id";
	public static final String HIERARCHY_TYPE = "hierarchy_type";
	public static final String USR_FIRSTNAME = "usr_firstname";
	public static final String USR_LASTNAME = "usr_lastname";
	public static final String USR_LOCALE = "usr_locale";
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	public static final String STATE = "state";

	// public static final String TIMEZONE_N = "TIMEZONE_N";
	public static final String STATUS = "status";
	public static final String GET_ALL_SALES_CH_CORPORATE = "GET_ALL_SALES_CH_CORPORATE";
	public static final String I_SALE_CHANNEL_CORPORATE_ID = "I_SALE_CHANNEL_CORPORATE_ID";
	public static final String I_CORPORATE_NAME = "I_CORPORATE_NAME";
	public static final String O_SALE_CH_CORPORATE_LIST = "O_SALE_CH_CORPORATE_LIST";
	public static final String SALES_CH_CORPORATES_MASTER_ID = "sales_ch_corporates_master_id";
	public static final String CORPORATE_NAME = "corporate_name";
	public static final String CORPORATE_CODE = "corporate_code";
	public static final String BANK_ID = "bank_id";
	public static final String INSERT_SALES_CHANNEL_BANK = "INSERT_SALES_CHANNEL_BANK_UPDATE";
	public static final String I_BANK_CODE = "I_BANK_CODE";
	public static final String I_CONTACT_PERSON = "I_CONTACT_PERSON";
	public static final String I_CONTACT_NUMBER = "I_CONTACT_NUMBER";
	public static final String I_EMAIL = "I_EMAIL";
	public static final String I_ADDRESS1 = "I_ADDRESS1";
	public static final String I_ADDRESS2 = "I_ADDRESS2";
	public static final String I_ZIPCODE = "I_ZIPCODE";
	public static final String I_TIMEZONE = "I_TIMEZONE";
	public static final String I_STATE = "I_STATE";
	public static final String I_CITY = "I_CITY";
	public static final String I_CURRENCY = "I_CURRENCY";
	public static final String UPDATE_SALES_CHANNEL_BANK = "UPDATE_SALES_CHANNEL_BANK_UPDATE";
	public static final String INSERTSALECHANNELCOOPERATE = "INSERT_SALES_CHANNEL_CORPORATE";
	public static final String INSERT_SALE_CHANNEL_CORPORATE = "INSERT_SALES_CHANNEL_CORPORATE";
	public static final String I_CORPORATE_CODE = "I_CORPORATE_CODE";
	public static final String UPDATE_SALES_CHANNEL_CORPORATE = "UPDATE_SALES_CHANNEL_CORPORATE";
	public static final String O_SALE_CHANNEL_BANK_ID = "O_SALE_CHANNEL_BANK_ID";
	public static final String O_SALE_CHANNEL_CORPORATE_ID = "O_SALE_CHANNEL_CORPORATE_ID";
	public static final String I_BANK_MASTER_CODE = "I_BANK_MASTER_CODE";

	public static final String GET_MERCHANTS_LIST = "GET_MERCHANTS_LIST";
	public static final String I_NAME = "i_name";
	public static final String O_MERCHANTS_LIST = "O_MERCHANTS_LIST";
	public static final String I_MID = "i_mid";
	public static final String I_MID_1 = "I_MID";
	public static final String O_MERCHANT_INFO = "O_MERCHANT_INFO";
	public static final String GET_MERCHANT = "GET_MERCHANT";
	public static final String GET_MERCHANT_KEY = "GET_MERCHANT_KEY";
	public static final String GET_MERCHANT_KYC = "GET_MERCHANT_KYC";
	public static final String O_MERCHANT_KEY = "O_MERCHANT_KEY";
	public static final String O_MERCHANT_KYC = "O_MERCHANT_KYC";
	public static final String ADD_MERCHANT = "ADD_MERCHANT";
	public static final String UPDATE_MERCHANT_INFO = "UPDATE_MERCHANT_INFO";
	public static final String I_BUSINESS_NAME = "I_BUSINESS_NAME";
	public static final String I_DISPLAY_NAME = "I_DISPLAY_NAME";
	public static final String I_CORPORATE_ID = "I_CORPORATE_ID";
	public static final String I_LANGUAGE = "I_LANGUAGE";
	public static final String I_WEBSITE_URL = "I_WEBSITE_URL";
	public static final String I_MERCHANT_TIMEZONE = "I_MERCHANT_TIMEZONE";
	public static final String I_DST = "I_DST";
	public static final String I_MCC = "I_MCC";
	public static final String I_MCP = "I_MCP";
	public static final String I_VIRTUAL_POS = "I_VIRTUAL_POS";
	public static final String I_FSS = "I_FSS";
	public static final String I_HOSTED_PAYPAGE_CUSTN = "I_HOSTED_PAYPAGE_CUSTN";
	public static final String I_DCC = "I_DCC";
	public static final String I_LEVEL_2_3_DATA = "I_LEVEL_2_3_DATA";
	public static final String I_AIRLINE_DATA = "I_AIRLINE_DATA";
	public static final String I_ENCRYPT_REQ_RESP = "I_ENCRYPT_REQ_RESP";
	public static final String I_CHECKSUM_ENABLED = "I_CHECKSUM_ENABLED";
	public static final String I_ADDRESS_VALIDATION = "I_ADDRESS_VALIDATION";
	public static final String I_VALID_FROM = "I_VALID_FROM";
	public static final String I_MID_GENERATED = "I_MID_GENERATED";
	public static final String I_AUTO_RENEW = "I_AUTO_RENEW";
	public static final String I_VALID_TO = "I_VALID_TO";
	public static final String I_CUSTOM = "I_CUSTOM";
	public static final String I_SOFT_DESCRPTR_TYPE = "I_SOFT_DESCRPTR_TYPE";
	public static final String I_SOFT_DESCRPTR_VALUE = "I_SOFT_DESCRPTR_VALUE";
	public static final String I_CHARGE_DESCRIPTOR = "I_CHARGE_DESCRIPTOR";
	public static final String I_CNTCT_TITLE = "I_CNTCT_TITLE";
	public static final String I_CNTCT_FIRST_NAME = "I_CNTCT_FIRST_NAME";
	public static final String I_CNTCT_LAST_NAME = "I_CNTCT_LAST_NAME";
	public static final String I_CNTCT_USERNAME = "I_CNTCT_USERNAME";
	public static final String I_CNTCT_PHONE_NO = "I_CNTCT_PHONE_NO";
	public static final String I_CNTCT_MOBILE_NUMBER = "I_CNTCT_MOBILE_NUMBER";
	public static final String I_CNTCT_PRIMARY_EMAIL = "I_CNTCT_PRIMARY_EMAIL";
	public static final String I_CNTCT_SECONDARY_EMAIL = "I_CNTCT_SECONDARY_EMAIL";
	public static final String I_CNTCT_ADDRESS1 = "I_CNTCT_ADDRESS1";
	public static final String I_CNTCT_ADDRESS2 = "I_CNTCT_ADDRESS2";
	public static final String I_CNTCT_COUNTRY = "I_CNTCT_COUNTRY";
	public static final String I_CNTCT_STATE_PROVINCE = "I_CNTCT_STATE_PROVINCE";
	public static final String I_CNTCT_CITY = "I_CNTCT_CITY";
	public static final String I_CNTCT_POSTAL_CODE = "I_CNTCT_POSTAL_CODE";
	public static final String I_LEVEL1_NAME = "I_LEVEL1_NAME";
	public static final String I_LEVEL1_EMAIL_ADDRESS = "I_LEVEL1_EMAIL_ADDRESS";
	public static final String I_LEVEL1_CONTACT_NO = "I_LEVEL1_CONTACT_NO";
	public static final String I_LEVEL2_NAME = "I_LEVEL2_NAME";
	public static final String I_LEVEL2_EMAIL_ADDRESS = "I_LEVEL2_EMAIL_ADDRESS";
	public static final String I_LEVEL2_CONTACT_NO = "I_LEVEL2_CONTACT_NO";
	public static final String O_ENTITY_ID = "O_ENTITY_ID";
	public static final String ADD_MERCHANT_KYC_INFO = "ADD_MERCHANT_KYC_INFO";
	public static final String UPDATE_MERCHANT_KYC = "UPDATE_MERCHANT_KYC";
	public static final String I_CARD_STORE_OPTION = "I_CARD_STORE_OPTION";
	public static final String I_RESPONSE_URL="I_RESPONSE_URL";
	public static final String I_3D_SECURE="I_3D_SECURE";
	public static final String UPDATE_MERCHANT_LICENSE_VALIDITY="UPDATE_MERCHANT_LICENSE_VALIDITY";

	public static final String I_IS_KYC_VALIDATED_BY_BANK = "I_IS_KYC_VALIDATED_BY_BANK";
	public static final String I_IS_PUBLIC_QUOTED_COMPANY = "I_IS_PUBLIC_QUOTED_COMPANY";
	public static final String I_STOCK_SYMBOL = "I_STOCK_SYMBOL";
	public static final String I_STOCK_EXCHANGE = "I_STOCK_EXCHANGE";
	public static final String I_COMPANY_REGISTRATION_NUMBER = "I_COMPANY_REGISTRATION_NUMBER";
	public static final String I_VAT_REGISTRATION_NO = "I_VAT_REGISTRATION_NO";
	public static final String I_DIRECTOR1_NAME = "I_DIRECTOR1_NAME";
	public static final String I_DIRECTOR1_DOCUMENT_TYPE = "I_DIRECTOR1_DOCUMENT_TYPE";
	public static final String I_DIRECTOR1_DESCRIPTION = "I_DIRECTOR1_DESCRIPTION";
	public static final String I_DIRECTOR2_NAME = "I_DIRECTOR2_NAME";
	public static final String I_DIRECTOR2_DOCUMENT_TYPE = "I_DIRECTOR2_DOCUMENT_TYPE";
	public static final String I_DIRECTOR2_DESCRIPTION = "I_DIRECTOR2_DESCRIPTION";
	public static final String I_BENEFICIAL_OWNER_NAME = "I_BENEFICIAL_OWNER_NAME";
	public static final String I_BENEFICIAL_OWNER_ADDRESS = "I_BENEFICIAL_OWNER_ADDRESS";
	public static final String I_KYC_DOCUMENT_LOCATION = "I_KYC_DOCUMENT_LOCATION";
	public static final String I_KYC_REMARKS = "I_KYC_REMARKS";

	public static final String IS_MID_PRESENT_SP = "IS_MID_PRESENT_SP";
	public static final String APPROVE_REJECT_MERCHANT = "APPROVE_REJECT_MERCHANT";
	public static final String GET_SOFT_DESCRIPTORS = "GET_SOFT_DESCRIPTORS";
	public static final String GET_KYC_DCMNT_TYPES = "GET_KYC_DCMNT_TYPES";
	public static final String LOCK_UNLOCK_MERCHANT = "LOCK_UNLOCK_MERCHANT";

	// COMMISSION
	public static final String ADD_ENTITY_COMISSION = "ADD_ENTITY_COMISSION";
	public static final String UPDATE_ENTITY_COMMISSION = "UPDATE_ENTITY_COMMISSION";
	public static final String GET_ENTITY_COMMISSION = "GET_ENTITY_COMMISSION";

	public static final String I_COMMISSION_TYPE = "I_COMMISSION_TYPE";
	public static final String I_COMM_TYPE_IDENTIFIER = "I_COMM_TYPE_IDENTIFIER";
	public static final String I_FLAT_AMOUNT = "I_FLAT_AMOUNT";
	public static final String I_PERCENTAGE_AMOUNT = "I_PERCENTAGE_AMOUNT";
	public static final String I_START_RANGE_1 = "I_START_RANGE_1";
	public static final String I_END_RANGE_1 = "I_END_RANGE_1";
	public static final String I_FLAT_AMOUNT_1 = "I_FLAT_AMOUNT_1";
	public static final String I_PERCENTAGE_AMOUNT_1 = "I_PERCENTAGE_AMOUNT_1";
	public static final String I_START_RANGE_2 = "I_START_RANGE_2";
	public static final String I_END_RANGE_2 = "I_END_RANGE_2";
	public static final String I_FLAT_AMOUNT_2 = "I_FLAT_AMOUNT_2";
	public static final String I_PERCENTAGE_AMOUNT_2 = "I_PERCENTAGE_AMOUNT_2";
	public static final String I_START_RANGE_3 = "I_START_RANGE_3";
	public static final String I_END_RANGE_3 = "I_END_RANGE_3";
	public static final String I_FLAT_AMOUNT_3 = "I_FLAT_AMOUNT_3";
	public static final String I_PERCENTAGE_AMOUNT_3 = "I_PERCENTAGE_AMOUNT_3";
	public static final String I_COMM_TYPE_IDENTIFIER_1 = "I_COMM_TYPE_IDENTIFIER_1";
	public static final String I_COMM_TYPE_IDENTIFIER_2 = "I_COMM_TYPE_IDENTIFIER_2";
	public static final String I_COMM_TYPE_IDENTIFIER_3 = "I_COMM_TYPE_IDENTIFIER_3";

	public static final String O_COMMISSIONDETAILS = "I_COMM_TYPE_IDENTIFIER_3";

	// public static final String COMMISSION_TYPE_SIMPLE = "COMMISSION_TYPE_SIMPLE";

	public static final String COMMISSION_ID = "id";
	public static final String ENTITY_ID = "entity_id";
	public static final String COMMISSION_TYPE = "commission_type";
	public static final String COMM_TYPE_IDENTIFIER = "comm_type_identifier";
	public static final String START_RANGE = "start_range";
	public static final String END_RANGE = "end_range";
	public static final String FLAT_AMOUNT = "flat_amount";
	public static final String PERCENTAGE_AMOUNT = "percentage_amount";
	public static final String I_RESP_DESCRIPTION = "I_RESP_DESCRIPTION";
	public static final String I_RESP_CODE = "I_RESP_CODE";

	// COMMISSION TYPE
	public static final String COMMISSION_TYPE_SIMPLE = "SIMPLE";
	public static final String COMMISSION_TYPE_SLAB_TXN_AMT = "SLAB_TXN_AMT";
	public static final String COMMISSION_TYPE_SLAB_DAILY_TXN_AMT = "SLAB_DAILY_TXN_AMT";
	public static final String O_MID = "O_MID";

	// COMMISSION TYPE IDENTIFIER
	public static final String REVENUE_TYPE_FLAT = "FLAT";
	public static final String REVENUE_TYPE_PERCENTAGE = "PERCENTAGE";
	public static final String REVENUE_TYPE_BOTH = "BOTH";

	public static final String I_AMOUNT = "I_AMOUNT";

	public static final String O_VELOCITYDETAILS = "O_VELOCITYDETAILS";

	// VELOCITY
	public static final String VELOCITY_ID = "id";
	public static final String AMOUNT = "amount";
	public static final String BASE_CURRENCY = "base_currency";

	// FSS
	public static final String O_FSSENABLEDVIEW = "O_FSSENABLEDVIEW";
	public static final String O_FSSNEGATIVEDBVIEW = "O_FSSNEGATIVEDBVIEW";
	public static final String O_FSSSLABVIEW = "O_FSSSLABVIEW";
	public static final String I_USERNAME = "I_USERNAME";
	public static final String I_FSS_ENABLED = "I_FSS_ENABLED";
	public static final String I_NEGATIVEDB_CHECK = "I_NEGATIVEDB_CHECK";
	public static final String I_NEGATIVEDB_WHITE_ACTION = "I_NEGATIVEDB_WHITE_ACTION";
	public static final String I_NEGATIVEDB_WHITE_COUNT = "I_NEGATIVEDB_WHITE_COUNT";
	public static final String I_NEGATIVEDB_GRAY_ACTION = "I_NEGATIVEDB_GRAY_ACTION";
	public static final String I_NEGATIVEDB_GRAY_COUNT = "I_NEGATIVEDB_GRAY_COUNT";
	public static final String I_NEGATIVEDB_BLACK_ACTION = "I_NEGATIVEDB_BLACK_ACTION";
	public static final String I_NEGATIVEDB_BLACK_COUNT = "I_NEGATIVEDB_BLACK_COUNT";
	public static final String I_FSS_SCORE_CHECK = "I_FSS_SCORE_CHECK";
	public static final String I_FIRST_START_RANGE = "I_FIRST_START_RANGE";
	public static final String I_FIRST_END_RANGE = "I_FIRST_END_RANGE";
	public static final String I_FIRST_SCORE_ACTION = "I_FIRST_SCORE_ACTION";
	public static final String I_FIRST_SCORE_COUNT = "I_FIRST_SCORE_COUNT";
	public static final String I_SECOND_START_RANGE = "I_SECOND_START_RANGE";
	public static final String I_SECOND_END_RANGE = "I_SECOND_END_RANGE";
	public static final String I_SECOND_SCORE_ACTION = "I_SECOND_SCORE_ACTION";
	public static final String I_SECOND_SCORE_COUNT = "I_SECOND_SCORE_COUNT";
	public static final String I_THIRD_START_RANGE = "I_THIRD_START_RANGE";
	public static final String I_THIRD_END_RANGE = "I_THIRD_END_RANGE";
	public static final String I_THIRD_SCORE_ACTION = "I_THIRD_SCORE_ACTION";
	public static final String I_THIRD_SCORE_COUNT = "I_THIRD_SCORE_COUNT";
	public static final String I_IP_LOCATION_CHECK = "I_IP_LOCATION_CHECK";
	public static final String I_MATCHING_ACTION = "I_MATCHING_ACTION";
	public static final String I_MATCHING_COUNT = "I_MATCHING_COUNT";
	public static final String I_NOT_MATCHING_ACTION = "I_NOT_MATCHING_ACTION";
	public static final String I_NOT_MATCHING_COUNT = "I_NOT_MATCHING_COUNT";
	public static final String ERROR_CODE = "ERROR_CODE";
	public static final String ERROR_MSG = "ERROR_MSG";
	public static final String O_FSSACTIONS = "O_FSSACTIONS";
	public static final String O_SOFT_DSRCPTRS = "O_SOFT_DSRCPTRS";
	public static final String O_KYC_DCMNT_TYPES = "O_KYC_DCMNT_TYPES";

	// ApproveReject
	public static final String I_VERIFIED = "I_VERIFIED";
	public static final String I_DIRECTOR_DETAILS = "I_DIRECTOR_DETAILS";
	public static final String I_BENIFICIAR_DETAILS = "I_BENIFICIAR_DETAILS";
	public static final String I_APPROVE_REJECT = "I_APPROVE_REJECT";
	public static final String I_COMMENTS = "I_COMMENTS";
	public static final String I_ENTITY_KEY = "I_ENTITY_KEY";

	// MERCHANT MAPPING
	public static final String COMM_MODE_MAPPING = "CommModeList";
	public static final String AUTH_MODE_MAPPING = "AuthModeList";
	public static final String URLTYPE_MAPPING = "UrlTypeList";
	public static final String TXNTYPE_MAPPING = "TxnTypeList";
	public static final String LEVELTYPE_MAPPING = "LevelTypeList";

	public static final String O_MERCHANT_PREF_INFO = "O_MERCHANT_PREF_INFO";
	public static final String O_MERCHANT_TECH_INFO = "O_MERCHANT_TECH_INFO";

	public static final String STR_EMPTY = "";
	public static final String ENTITY_PROCESSOR_CONFIG_LIST = "ENTITY_PROCESSOR_CONFIG_LIST";
	public static final String I_LOGGEDIN_USER = "I_LOGGEDIN_USER";

	public static final String DCC_CONFIG_INFO = "DCC_CONFIG_INFO";

	public static final String MCP_INFO = "MCP_INFO";

	public static final String MERCHANT_INFO = "MERCHANT_INFO";

	// APC Constants for Configuration SP
	public static final String ADD_DCC_CONFIG = "ADD_DCC_CONFIG";
	public static final String I_PAYMENT_METHOD_ID_LIST = "I_PAYMENT_METHOD_ID_LIST";
	public static final String GET_AUTH_SETTLE_COMM_VAL = "GET_AUTH_SETTLE_COMM_VAL";
	public static final String I_PAYMENT_METHOD_ID = "I_PAYMENT_METHOD_ID";
	public static final String O_AUTH_CURRENCY_LIST = "O_AUTH_CURRENCY_LIST";
	public static final String O_SETTLE_CURRENCY_LIST = "O_SETTLE_CURRENCY_LIST";
	public static final String O_COMM_MODE_LIST = "O_COMM_MODE_LIST";
	public static final String O_PAYMENT_METHOD_CONFIG_LIST = "O_PAYMENT_METHOD_CONFIG_LIST";
	public static final String GET_ENTITY_PROCSR_CONF_LIST = "GET_ENTITY_PROCSR_CONF_LIST";
	public static final String GET_ENTITY_PROCESSOR_CONFIG = "GET_ENTITY_PROCESSOR_CONFIG";
	public static final String O_DCC_CONFIG_INFO = "O_DCC_CONFIG_INFO";
	public static final String O_MCP_CURR_MAPP_LIST = "O_MCP_CURR_MAPP_LIST";
	public static final String I_ENTITY_PROCESSOR_CONFIG_ID = "I_ENTITY_PROCESSOR_CONFIG_ID";
	public static final String I_AUTH_CURRENCY_ID = "I_AUTH_CURRENCY_ID";
	public static final String I_SETTLEMENT_CURRENCY_ID = "I_SETTLEMENT_CURRENCY_ID";
	public static final String I_PROCESSOR_MERCHANT_ID = "I_PROCESSOR_MERCHANT_ID";
	public static final String I_CONFIG_ID = "I_CONFIG_ID";
	public static final String I_PAYMENT_TYPE_ID = "I_PAYMENT_TYPE_ID";
	public static final String UPDATE_ENTITY_PROCESSOR_CONFIG = "UPDATE_ENTITY_PROCESSOR_CONFIG";
	public static final String ADD_ENTITY_PROCESSOR_CONFIG = "ADD_ENTITY_PROCESSOR_CONFIG";
	public static final String I_PAYMENT_TYPE_ID_LIST = "I_PAYMENT_TYPE_ID_LIST";
	public static final String X_DUPLICATE = "X_DUPLICATE";
	public static final String I_VELOCITY_VAL = "I_VELOCITY_VAL";
	public static final String MCP_CURR_MAPP_LIST = "MCP_CURR_MAPP_LIST";
	public static final String PAYMENT_METHOD_CONFIG_LIST = "PAYMENT_METHOD_CONFIG_LIST";

	// added by ganesh form FEE
	public static final String JAVA_DATE_FORMAT = "dd-MMM-yyyy";
	public static final String JAVA_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
	public static final String JAVA_DATE_TIME_FORMAT_FULL = "dd-MM-yyyy HH:mm:ss";
//DD-MM-YYYY HH24:MI
	public static final String DATE_FORMAT_1 = "yyyy-MM-dd";

	// This has been declared for Bank Key File Path
	public static final String BANK_KEY_FILE_PATH = "bank.key.file.path";

	// This has been declared for Default Bank Key File Path,It will take the root Directory
	public static final String BANK_KEY_FILE_PATH_DEFAULT = "/";

	// IDENTIFIERS
	public static final String IDENTIFIER_GENERATE_PASSWORD = "GENERATE_PASSWORD";
	public static final String IDENTIFIER_CHECKSUM_ENABLED = "CHECKSUM_ENABLED";
	public static final String IDENTIFIER_CHECKSUM_DISABLED = "CHECKSUM_DISABLED";
	public static final String IDENTIFIER_MERCHANT_REGISTERED = "MERCHANT_REGISTERED";
	public static final String IDENTIFIER_RESET_PASSWORD = "RESET_PASSWORD";

	public static final String CONFIG_CHANNELS = "Config Channels";

	public static final String LOG_USER_NAME = "UserName :";

	public static final String JSON_RESULT = "result";

	public static final String JSON_ERR_MSG = "errormsg";

	public static final String JSON_TRUE = "['true']";

	public static final String JSON_FALSE = "['false']";

	public static final String FALSE = "false";

	public static final String TRUE = "true";

	public static final String ORA = "ORA";

	public static final String ALL = "ALL";

	public static final String UNDEFINED = "undefined";

	public static final String JSON_EXISTING_STRING = "existingstrings";

	public static final String USER_NAME = "UserName :";
	public static final String NET_BANKING = "NB";

	public static final String BANK_IDS_KEY = "bankIds";

	public static final String BANK_NAMES_KEY = "bankNames";
	public static final String CURRENCY_TYPE_MAP = "currencyTypeMap";
	public static final String INDUSTRY_TYPE_MAP = "industryTypeMap";
	public static final String CATEGORY_MAP = "categoryMap";
	public static final String BANK_INFO_CONFIG_VO = "bankInfoConfigVO";
	public static final String PAGE_ONE = "1";
	public static final String PAGE_FIVE = "5";
	public static final String PAGE_TEN = "10";
	public static final String PAGE_FIFTY = "50";
	public static final String FROM_EDIT_FLOW = "fromEditFlow";
	public static final String C_INDUSTRY_TYPE = "cIndustryType";

	public static final String C_CC_DC_TXN_TYPE = "cCcDcTxnType";
	public static final String C_CARD_CATEGORY_TYPE = "cCardCategory";
	public static final String CC_RADIO_BUTTON = "ccRadioBtn";
	public static final String DC_RADIO_BUTTON = "dcRadioBtn";
	public static final String DOT_SEPARATOR = "\\.";
	public static final String OPEN_ANCHOR_LINK_FOR_FILE = "<a class=\"morebutton\" href=\"./showLogFile?fileName=";
	public static final String BACK_SLASH = "\">";
	public static final String CLOSE_ANCHOR_LINK_FOR_FILE = "</a>";
	public static final String FWD_SLASH = "/";
	public static final String HYPEN_SEPARATOR = "-";
	public static final String CONTENT_TYPE = "application/text";
	public static final String HEADER_DISPOSITION = "Content-Disposition";
	public static final String HEADER_FILE_NAME = "attachment; filename=";
	public static final String HEADER_INLINE_FILE = "inline;filename=";
	public static final String CONTENT_TYPE_XLS = "application/xls";
	public static final String ZERO_DECIMAL_NO = "0.00";
	public static final String ONUS = "ONUS";
	public static final Long ZERO_LONG_VALUE = 0L;
	public static final String BANKS = "banks";
	public static final String ADD_MERCHANT_COMM = "addMerchantCommission";
	public static final String SLAB_TXN_AMT = "SLAB_TXN_AMT";
	public static final String TRANS_AMNT_SLAB = "TransAmountslap";
	public static final String DAILY_TRANS_AMNT_SLAB = "DailyTransAmountslap";
	public static final String SIMPLE = "Simple";
	public static final String LOWER_CASE_SIMPLE = "Simple";
	public static final String YES = "YES";
	public static final String NO = "NO";
	public static final String Y = "Y";
	public static final String N = "N";
	public static final String DEFAULT_ORACLE_MSG = "ORA";
	public static final String COUNT = "count";
	public static final String BANK_NAME_EDIT = "bankNameEdit";

	public static final String BANK_RESP_CODE_EDIT = "bankResponseCodeEdit";
	public static final String BANK_RESP_TEXT_EDIT = "bankResponseTextEdit";
	public static final String GATEWAY_RESP_CODE_EDIT = "gatewayResponseCodeEdit";
	public static final String GATEWAY_RESP_TEXT_EDIT = "gatewayResponseTextEdit";
	public static final String LOGS_ERROR = "0001";

	public static final String PAGE = "1";
	public static final String TOTAL = "10";
	public static final String SUCCESS = "Success";

	public static final String FILE_UPLOAD_BANK_INFO = "FILE_UPLOAD_BANK_INFO";
	public static final String ROLE_SUB_STRING = "20";
	public static final int ZERO = 0;
	
	// Added
	public static final String STR_ZERO = "0";
	public static final String STATUS_ALL = "ALL";
	public static final String STR_DAY_YESTERDAY = "YESTERDAY";
	public static final String STR_DAY_TODAY = "TODAY";
	public static final String STR_WEEKLY = "WEEKLY";
	public static final String STR_MONTHLY = "MONTHLY";

	public static final String TXN_PARAM_MID = "mid";
	public static final String TXN_PARAM_RESP_CODE = "respCode";
	public static final String TXN_PARAM_CC_DC = "ccDc";
	public static final String TXN_PARAM_TXN_ID = "txnId";
	public static final String TXN_PARAM_ORDER_ID = "orderId";

	public static final String STR_CARD_DIGITS_3 = "000";
	public static final String STR_CARD_DIGITS_2 = "00";
	public static final String STR_CARD_DIGITS_1 = "0";

	public static final String REPORT_NAME_TXN_HISTORY = "Transaction History";

	public static final String MANAGER = "MANAGER";
	public static final String ROLES = "roles";
	public static final String STATUS_REJECTED = "REJECTED";
	public static final String STATUS_FORCE_APPROVAL = "FORCE_APPROVAL";
	public static final String SYSTEM_GENERATED = "SYSTEM GENERATED";
	public static final String SYSTEM = "SYSTEM";
	public static final String CUSTOM = "CUSTOM";
	public static final String REQUEST = "REQUEST";
	public static final String RESPONSE = "RESPONSE";
	public static final String STR_DA_O_ERROR_CODE = "O_ERROR_CODE";
	public static final String STR_DA_O_ERROR_MSG = "O_ERROR_MSG";
	public static final String VALID_TO = "validTo";
	public static final String VALID_FROM = "validFrom";
	public static final String SPACE = " ";
	public static final String PAGE_CONSTANT = "PAGE";
	public static final String CHANNELS = "channels";
	public static final String AUTHS = "auths";
	public static final String INDUSTRYS = "industrys";

	public static final String STR_RESP_CODE = "responseCode";
	public static final String STR_RESP_MSG = "responseMsg";

	public static final String NUMBER = "NUM";
	public static final String MID = "MID";
	public static final String CONFIGLIST = "CONFIGLIST";
	public static final String PAGE_CHECKLIST = "PAGE";
	public static final String SUBBIT_APPROVAL = "Submit for Approval";
	public static final String SAVE = "Save";
	public static final String logs_Path = "logs.Path";
	public static final String IVN = "IVN";

	public static final String MBID_ACTIVE_STATUS = "STATUS_ACTIVE";
	public static final String MBID_INACTIVE_STATUS = "INACTIVE";
	public static final String FIELD_VALUES = "fieldValues";
	public static final String FSS_MID = "mid";
	public static final String FSS = "fss";
	public static final String ONE = "1";
	public static final String TEN = "10";
	public static final String VELOCITYONCARD = "velocityOnCardNumber";

	public static final String ENTER_VALID_USERNAME = "Please Enter a valid user name";
	public static final String EMPTY_STRING = "";
	public static final String INVALID_USER = "Invalid User";

	public static final String REPRESENTATIVE = "REPRESENTATIVE";

	public static final String T = "T";

	public static final String USER_ACCOUNT_EXPIRED = "User Account has Expired";

	public static final String ACCOUNT_LOCKED = "Account Locked";

	public static final String PASSWORD_EXPIRED = "Password expired";

	public static final String ROLE_MERCHANT = "ROLE_MERCHANT";
	public static final String ROLE_MERCHANT_SALES_ADMIN = "ROLE_MERCHANT_SALES_ADMIN";
	public static final String ROLE_ACCOUNT_ADMIN = "ROLE_ACCOUNT_ADMIN";
	public static final String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";
	public static final String ROLE_MERCHANT_SUPPORT = "ROLE_MERCHANT_SUPPORT";
	public static final String ROLE_RISK_ADMIN = "ROLE_RISK_ADMIN";

	public static final String NUM = "NUM";
	public static final String INR = "INR";
	public static final String ENCRYPT_REQUEST_RESPONSE = "ENCRYPT REQUEST RESPONSE";
	public static final String CHECK_ENABLED_LATER = "CHECK_ENABLED_LATER";
	public static final String OCP_ENABLED = "OCP_ENABLED";
	public static final String CHECKSUM_ENABLED = "CHECKSUM_ENABLED";
	public static final String STORE_CARD_DETAILS = "STORE CARD DETAILS";
	public static final String CONFIG_SIZE = "CONFIG_SIZE";
	public static final String MID_GENERATION = "MID_GENERATION";
	public static final String EDIT_MERCHANT = "editMerchant";
	public static final String I_TOCODE = "I_TOCODE";
	public static final String BACK = "Back";
	public static final String FLAG = "FLAG";
	public static final String EXCEPTION_OCCURED_WHILE_CHECKLIST = "Exception occured while checkList";
	public static final String SUBMIT_FOR_APPROVAL = "Submit for Approval";
	public static final String APPROVE = "Approve";
	public static final String Go_TO_MANAGE_MERCHANTS = "Go To Manage Merchants";
	public static final String REJECT = "Reject";
	public static final String CHANGE_PASSWORD = "changePassword";
	public static final String CHANGE_PASS = "changePass";
	public static final String I_PAYMENT_OPTION = "I_PAYMENT_OPTION";
	public static final String I_CARD_CATEGORY_NAME = "I_CARD_CATEGORY_NAME";
	public static final String O_RANGEINFO = "O_RANGEINFO";
	public static final String I_ID = "I_ID";
	public static final String I_ID_COUNT = "I_ID_COUNT";
	public static final String I_TXN_TYPE = "I_TXN_TYPE";
	public static final String O_BANKFEESLABINFO = "O_BANKFEESLABINFO";
	public static final String I_FEE_CURRENCY = "i_fee_currency";

	public static String FILE_PATH_MAIL_PROP = "/mail.properties";
	public static String FILTER_OAUTH_PROP =  "/filter_oauth.properties";
	public static String USER_MAIL = "/userMailFormat.txt";
	public static String USER_MAIL_PASSWORD = "/userMailPasswordFormat.txt";
	public static String FORGOT_MAIL_PASSWORD = "/forgotPasswordFormat.txt";
	public static String API_USER_MAIL = "/apiUserMailFormat.txt";
	public static String SMTP_HOST_NAME = "SMTP_HOST_NAME";
	public static final String EMAIL_FROM_ADDRESS = "EMAIL_FROM_ADDRESS";
	public static final String USER_CREATE_SUBJECT = "USER_CREATE_SUBJECT";
	public static final String MAIL_URL = "MAIL_URL";
	public static final String RESET_PASS_URL = "RESET_PASS_URL";
	public static final String API_USER_CREATE_SUBJECT = "API_USER_CREATE_SUBJECT";
	public static final String USER_CREATE_MSG = "USER_CREATE_MSG";
	public static final String SMTP_AUTH_USER = "SMTP_AUTH_USER";
	public static final String SMTP_AUTH_PWD = "SMTP_AUTH_PWD";
	public static final String FORGOT_PASS_SUBJECT = "FORGOT_PASS_SUBJECT";
	public static final String FORGOT_PASS_MSG = "FORGOT_PASS_MSG";
	public static final String HTTPS = "https://";
	public static final String COLON = ":";
	// public static final String ENTITY_APPROVED = "APPROVED";
	//#PG-178
	public static final String PROGRAM_NAME="programme.name";
	public static final String PROGRAM_SUPPORT_EMAIL="program.support.email";
	public static final String PROGRAM_SUPPORT_PHONE="program.support.phone";
	public static final String SIGNATURE = "signature";
	public static final String PROGRAM_WEBSITE ="program.website";

	// APC Constants for Transaction Search SP
	
	public static final String GET_TRANSACTION_LIST = "transaction_search_latest_txnType";//"transaction_search_latest_timestamp";//"transaction_search_latest";
	public static final String GET_TRANSACTION_BASIC_INFO = "transaction_basic_information";
	public static final String GET_TRANSACTION_HISTORY_INFO = "txn_hist_info_timestamp";//"txn_hist_info";
	public static final String I_USERNAME_TS = "i_username";
	public static final String I_TXN_STATUS = "i_txn_status";
	public static final String I_PERIOD = "i_period";
//	public static final String I_GATEWAY = "i_processor_gateway";
	public static final String I_STARTDATE = "i_startdate";
	public static final String I_ENDDATE = "i_enddate";
	public static final String I_START_AMT = "i_start_amt";
	public static final String I_END_AMT = "i_end_amt";
	public static final String I_TXN_ID = "i_txn_id";
	public static final String I_TXN_ID_1 = "I_TXN_ID";
	public static final String I_ORDER_ID = "i_orderid";
	public static final String I_CURRENCY_TS = "i_currency";
	public static final String O_TXNHIST = "O_TXNHIST";
	public static final String I_TIMESTAMP = "i_timestamp_type";
	public static final String I_TXNTYPE="i_txnType";
	public static final String I_TXNTYPE_1="I_TXNTYPE";
	public static final String I_CARDNUM="i_cardNum";
	public static final String I_CHANNEL_TYPE="i_channel_type";

	// APC Constants for PayPage Config SP
	public static final String i_card_type = "i_card_type";
	public static final String i_response_method = "i_response_method";
	public static final String i_response_url = "i_response_url";
	public static final String i_bg_color = "i_bg_color";
	public static final String i_font_style = "i_font_style";
	public static final String i_font_size = "i_font_size";
	public static final String i_primary_txt_clr = "i_primary_txt_clr";
	public static final String i_logo = "i_logo";
	public static final String i_display_page = "i_display_page";
	public static final String i_frame_width = "i_frame_width";
	public static final String i_header_file = "i_header_file";
	public static final String i_footer_file = "i_footer_file";
	public static final String i_display_cust_dtls = "i_display_cust_dtls";
	public static final String i_display_bill_dtls = "i_display_bill_dtls";
	public static final String i_display_merchant_dtls = "i_display_merchant_dtls";
	public static final String i_display_item_dtls = "i_display_item_dtls";
	public static final String i_display_address_dtls = "i_display_address_dtls";
	public static final String i_language_id = "i_language_id";
	public static final String i_email_text = "i_email_text";
	public static final String i_header_clr = "i_header_clr";
	public static final String i_email_id = "i_email_id";
	public static final String i_email_format = "i_email_format";
	public static final String i_include_line_item_dtls = "i_include_line_item_dtls";
	public static final String i_include_line_bill_dtls = "i_include_line_bill_dtls";
	public static final String i_logo_path = "i_logo_path";
	public static final String o_pay_page_config_list = "o_pay_page_config_list";
	public static final String i_semd_mail_approve = "i_semd_mail_approve";
	public static final String i_semd_mail_declines = "i_semd_mail_declines";
	public static final String i_header_file_path = "i_header_file_path";
	public static final String i_footer_file_path = "i_footer_file_path";
	public static final String i_logo_file_name = "i_logo_file_name";
	public static final String i_header_file_name = "i_header_file_name";
	public static final String i_footer_file_name = "i_footer_file_name";
	public static final String i_logo_file_content = "i_logo_file_content";
	public static final String i_frame_height = "i_frame_height";
	public static final String i_hide_header = "i_hide_header";
	public static final String I_BOTTON_COLOR = "i_botton_color";

	public static final String ENTITY_APPROVED = "APPROVED";
	public static final String SP_SETTLEMENT_REPORT = "sp_settlement_summary_report_latest_timestamp";//"sp_settlement_summary_report_latest";
	public static final String O_SELLEMENT_CURSOR = "O_SELLEMENT_CURSOR";

	// Recurring Customer
	public static final String I_CUSTOMERID = "i_customerid";
	public static final String I_TITLE = "i_title";
	public static final String I_FIRSTNAME = "i_firstname";
	public static final String I_LASTNAME = "i_lastname";
	public static final String I_DAYPHONE = "i_dayphone";
	public static final String I_NIGHTPHONE = "i_nightphone";
	public static final String I_MOBILE = "i_mobile";
	public static final String I_EMAIL1 = "i_email";
	public static final String I_FAX = "i_fax";
	public static final String I_BILLADDRESS1 = "i_billaddress1";
	public static final String I_BILLADDRESS2 = "i_billaddress2";
	public static final String I_COUNTRYID = "i_countryid";
	public static final String I_COUNTRYNAME = "i_countryname";
	public static final String I_STATEID = "i_stateid";
	public static final String I_CITY1 = "i_city";

	public static final String I_ENTITYID = "i_entityid";
	public static final String I_ZIPCODE1 = "i_zipcode";
	public static final String I_STATUS1 = "i_status";
	public static final String I_CREATED_BY1 = "i_created_by";

	public static final String ADD_CUSTOMERCONTRACTINFO = "ADD_CUSTOMERCONTRACTINFO";
	public static final String I_CONTRACTKEY = "i_contractkey";
	public static final String I_CONTRACTID = "i_contractid";
	public static final String I_CONTRACTNAME = "i_contractname";
	public static final String I_TAXAMT = "i_taxamt";
	public static final String I_SUBTOTAL = "i_subtotal";
	public static final String I_TOTALAMT = "i_totalamt";
	public static final String I_CONTRACTBILLEDAMT = "i_contractbilledamt";
	public static final String I_CONTRACTEXECUTE = "i_contractexecute";
	public static final String I_NUMTAXFAILURES = "i_numtaxfailures";
	public static final String I_TAXCONFIRMMAIL = "i_taxconfirmemail";
	public static final String I_TAXRECEIVEDEMAIL = "i_taxreceivedemail";
	public static final String I_TRANSACTIONREPROSS = "i_transactionrepross";
	public static final String I_REQUESTTYPEID = "i_requesrttypeid";
	public static final String O_CONTRACTINFO_LIST = "o_contractinfo_list";
	public static final String O_CONTRACTINFO_VALUES = "o_contractinfo_values";
	public static final String GET_CONTRACTINFOLIST_STATUS = "get_contractinfolist_status";
	public static final String GET_CONTRACTINFOLIST = "get_contractinfolist";
	public static final String GET_EXECUTECONTRACT_LIST = "get_executecontract_list";
	public static final String I_CONTRACT_ID = "i_contract_id";
	public static final String O_EXECUTE_CONTRACT_LIST = "o_execute_contract_list";
	
	// Recurring Paymentinfo
	public static final String I_CUSTOMERPAYTYPEID = "i_customerpaytypeid";

	public static final String I_CUSTOMERPAYMENTINFOKEY = "i_customerpaymentinfokey";
	public static final String I_TYPE = "i_type";
	public static final String I_NUMBERONCARD = "i_numberoncard";
	public static final String I_SECURITYCODE = "i_securitycode";
	public static final String I_EXPDATE = "i_expdate";
	public static final String I_NAMEONCARED = "i_nameoncared";
	public static final String I_CUSTOMERKEY = "i_customerkey";
	public static final String CUSTOMERKEY = "customerkey";

	// News
	public static final String GET_NEWSINFO = "GET_NEWSINFO";
	public static final String ADD_NEWSINFO = "ADD_NEWSINFO";
	public static final String UPDATE_NEWSINFO = "UPDATE_NEWSINFO";
	public static final String I_NEWSID = "I_NEWSID";
	public static final String I_NEWSHEADER = "I_NEWSHEADER";
	public static final String I_NEWSCONTENT = "I_NEWSCONTENT";
	public static final String I_DATE = "I_DATE";
	public static final String I_NEWSEXPDATE = "I_NEWSEXPDATE";
	public static final String O_NEWS_VALUES = "O_NEWS_VALUES";

	// Dash Board
	public static final int divisionby = 10000;

	// Authorisation Summary Report Constants
	public static final String DB_DATE_FORMAT1 = "db.date.format";
	public static final String JSP_DATE_FORMAT1 = "jsp.date.format";
	public static final String GRID_DATE_FORMAT = "grid.date.format";
	public static final String DATE_TIME_FORMAT = "date.time.format";
	public static final String GRID_DATE_TIME_FORMAT = "grid.datetime.format";
	public static final String GET_AUTH_SUMMARY_REPORT = "get_auth_summary_report";
	public static final String I_USERNAME_1 = "i_username";
	public static final String I_FROM_DATE = "i_from_date";
	public static final String I_FROM_DATE_1 = "I_FROM_DATE";
	public static final String I_TO_DATE = "i_to_date";
	public static final String I_TO_DATE_1 = "I_TO_DATE";
	public static final String O_AUTH_COUNTRY_SUMMARY = "o_auth_country_summary";
	public static final String O_AUTH_COUNTRY_SUMMARY_DATE = "o_auth_country_summary_date";

	// Authorisation Summary Report Constants
	public static final String GET_AUTH_COUNTRY_SUMMARY_REPORT = "get_auth_country_summary_report";

	// Payment Method Breakdown Constants
	public static final String GET_PAYMENT_METHOD_BREAKDOWN = "get_merchant_payment_method_break_down_report_update";
	public static final String I_COUNTRY_1 = "i_country";
	public static final String I_RADIOTYPE = "i_radiotype";
	public static final String I_START_DATE = "i_startdate";
	public static final String I_END_DATE = "i_enddate";
	public static final String O_OVERALL_SUMMARY_LIST = "o_overall_summary_list";

	// User Report
	public static final String GET_USER_REPORT = "get_user_report";
	public static final String I_ENTITY_TYPE_ID = "i_entity_type_id";
	public static final String I_ENTITY_ID1 = "i_entity_id";
	public static final String I_ROLE_ID1 = "i_role_id";
	public static final String O_GET_USER_INFO = "O_GET_USER_INFO";

	// Merchant Report
	public static final String GET_MERCHANT_REPORT = "get_merchant_report";
	public static final String I_COUNTRY1 = "i_country";
	public static final String I_USERNAME1 = "i_username";
	public static final String O_MERCHANT_LIST = "o_merchants_list";

	// Authorisation Breakdown Summary Report
	public static final String GET_AUTH_BREAK_SUMMARY_REPORT = "get_auth_break_summary_report";
	public static final String O_AUTH_BREAK_SUMMARY = "o_auth_break_summary";

	// Chargeback Detail Report
	public static final String GET_CB_DETAIL_REPORT = "get_cb_detail_report";
	public static final String I_START_AMOUNT = "i_start_amt";
	public static final String I_END_AMOUNT = "i_end_amt";
	public static final String I_CURRENCY1 = "currency";
	public static final String O_CB_DETAILS = "o_cb_details";

	// Chargeback Summary Report
	public static final String  GET_CB_SUMMARY_REPORT = "get_cb_summary_report";
	public static final String  I_CURRENCY2 = "i_currency";
	public static final String  Processor_Select_CardType ="script.error.ProcessorSelectCardType";
	public static final String  Respons_URL_Select ="script.error.ResponseUrl";
	public static final String  Valid_Response_Url ="script.error.ValidResponseUrl";
	public static final String  Enter_Email_Address ="script.error.EmailAddress";
	public static final String  Logo_Validation_Msg ="script.error.UploadImageFileFormat";
	public static final String  Footer_Validation_Msg ="script.error.UploadHTMLFile";
	public static final String  Footer_Size_Validation_Msg ="script.error.FooterFileSize";
	public static final String  Header_Validation_Msg ="script.error.UploadHTMLFile";
	public static final String  Header_Size_Validation_Msg ="script.error.HeaderFileSize";
	public static final String  Logo_Size_Validation_Msg ="script.error.LogoFileSize";
	public static final String  Logo_Mandatory ="script.error.UploadLogo";
	public static final String  Logo_Mandatory_Upload ="script.error.UploadLogo";
	public static final String  Invalid_Email_Address ="script.error.InvalidEmailAddress";
	public static final String  primary_And_BG_Colors_Should_same = "script.error.SameColorBackground";
	public static final String  primary_And_Header_Colors_Should_same = "script.error.SameColorHeader";
	public static final String  iFrame_width_height = "script.error.HeightAndWidthMinValue";

	// Manage Blocks
	public static final String GET_BLOCK_CRITERIA = "GET_BLOCK_CRITERIA";
	public static final String GET_ALL_BLOCK_INFO = "GET_ALL_BLOCK_INFO";
	public static final String ADD_BLOCK_INFO = "ADD_BLOCK_INFO";
	public static final String GET_BLOCK_INFO = "GET_BLOCK_INFO";
	public static final String UPDATE_BLOCK_INFO = "UPDATE_BLOCK_INFO";
	
	// Chargeback Rates Report
	public static final String GET_CB_RATES_REPORT = "get_cb_rates_report";
	public static final String O_CB_RATES_DETAILS = "o_cb_rates_details";
	
	// API User
	public static final String GET_API_USERS_LIST = "get_api_users_list";
	public static final String I_USR_ID = "i_usr_id";
	public static final String I_LOGGEDIN_USER1 = "i_loggedin_user";
	public static final String O_API_USERS_LIST = "o_manage_api_users_list";
	
	public static final String USER_ID1 = "USERID";
	public static final String API_USER_ID = "API_USR_ID";
	public static final String EMAIL_ID1 = "EMAILID";
	public static final String STATUS1 = "STATUS";
	public static final String I_EXIST_MID_CHECK = "i_exist_mid_check";
	public static final String GET_MERCHANT_MID = "get_merchant_midlist";
	public static final String O_MID_INFO = "o_mid_info";
	public static final String O_API_USER_ID = "o_api_user_id";
	
	public static final String ADD_API_USER = "add_api_user_sp";
	public static final String UPDATE_API_USER = "update_api_user";
	public static final String DELETE_API_USER = "delete_api_user";
	public static final String I_API_USER_ID = "i_api_usr_id";
	public static final String I_CRITERIA = "i_criteria";
	public static final String GET_API_USER_ID = "get_api_user_id";
	
	//Card Config properties constants
	public static String FILE_PATH_CARD_CONFIG_PROP = "/cardconfig.properties";
	public static String APC_CARD_TYPE_KEY = "card.type";
	public static String APC_CARD_IIN_RANGE_KEY = "iin.ranges";
	public static String APC_CARD_LENGTH_KEY = "card.length";
	public static String I_TXN_CURRENCY ="i_txn_currency";
	public static String O_PRCSR_CNFG_CNT ="o_prcsr_cnfg_cnt";
	public static String GET_MID_MOTO_COUNT ="get_mid_moto_count";
	
	public final String USER_ACC_LOCKED_ERC = "USR-ACC-LC-100";
	public final String USER_ACC_EXPIRED_ERC = "USR-ACC-EXP-100";
	public final String USER_CREDENTIAL_EXPIRED_ERC = "USR-CRD-EXP-100";

	public static final String O_CREDIT_LIST = "O_CREDIT_LIST";
	public static final String i_credit_data = "i_credit_data";
	public static final String I_CREDIT_VELOCITY_VAL = "I_CREDIT_VELOCITY_VAL";
	public static final String I_BIN_PK_ID = "i_bin_pk_id";
	public static final String GET_CARD_TYPE_DETAILS = "get_card_type_details";
	public static final String I_CARD_BIN = "i_card_bin";
	public static final String I_CARD_TYPE = "i_card_type";
	public static final String O_BIN_CURSOR = "o_bin_cursor";
	
	public static final String BIN = "BIN";
	public static final String CARD_BRAND = "CARD_BRAND";
	public static final String ISSUING_ORG = "ISSUING_ORG"; 
	public static final String BIN_CARD_TYPE= "CARD_TYPE"; 
	public static final String CARD_CATEGORY = "CARD_CATEGORY"; 
	public static final String BIN_COUNTRY_NAME = "COUNTRY_NAME"; 
	public static final String COUNTRY_A2_CODE= "COUNTRY_A2_CODE"; 
	public static final String COUNTRY_A3_CODE= "COUNTRY_A3_CODE";
	public static final String COUNTRY_ISO_NUM= "COUNTRY_ISO_NUM";
	public static final String ORG_URL = "ORG_URL";
	public static final String ORG_PHONE= "ORG_PHONE";
	public static final String GET_BINFILESTATUS_INFO = "GET_BINFILESTATUS_INFO";
	public static final String I_FILESTATUS = "i_filestatus";
	public static final String O_BILLSTATUS_LIST = "o_billstatus_list";

	public static final String GET_MERCHANT_BINS = "get_merchant_bins";
	public static final String I_PAYMENT_METHOD = "i_payment_method";
	public static final String I_AUTH_CURR = "i_auth_curr";
	public static final String I_SPONSOR_BANK_INFOID = "i_sponsor_bank_infoid";
	public static final String BIN_ID_LOCAL = "BIN_ID_LOCAL";
	public static final String O_SPONSOR_BIN_INFO = "o_sponsor_bin_info";
	public static final String SPONSOR_BIN_PK_ID = "sponsor_bin_pk_id";
	public static final String BIN_ID = "bin_id";
	public static final String PFX_CERTICATE = "pfx_certicate";
	public static final String PFX_CERT_NAME = "pfx_cert_name";
	public static final String PFX_CERT = "pfx_certicate";
	public static final String PFX_PASSWORD = "pfx_password";
	public static final String CERT_COMMON_NAME = "cert_common_name";
	public static final String CLIENT_SSL_CERTIFICATE = "client_ssl_certificate";
	public static final String ACQR_CA_CERT = "acqr_ca_cert";
	public static final String ISSUER_CA_CERT = "issuer_ca_cert";
	public static final String CARD_TYPE = "card_type";
	public static final String I_BIN_ID = "i_bin_id";
	public static final String I_PFX_CERTICATE = "i_pfx_certicate";
	public static final String I_PFX_PASSWORD = "i_pfx_password";
	public static final String I_CERT_COMMON_NAME = "i_cert_common_name";
	public static final String I_CLIENT_SSL_CERTIFICATE = "i_client_ssl_certificate";
	public static final String I_ACQR_CA_CERT = "i_acqr_ca_cert";
	public static final String I_ISSUER_CA_CERT = "i_issuer_ca_cert";
	public static final String I_SPONSOR_BANK_ID = "i_sponsor_bank_id";
	public static final String I_PFX_CERT_NAME = "i_pfx_cert_name";
	public static final String O_COMBINATION_CURRENCY = "o_combination_currency";
	public static final String I_SPONSOR_BIN_PK_ID = "i_sponsor_bin_pk_id";
	public static final String UPDATE_SPONSOR_BIN_CONFIG = "update_sponsor_bin_config";
	public static final String INSERT_SPONSER_BIN_DETAILS = "insert_sponser_bin_details";
	public static final String GET_SPONSER_BIN_DETAILS = "get_sponser_bin_details";
	public static final String SPONSOR_BANK_ID = "sponsor_bank_id";
	public static final String CARD_TYPE_ID = "card_type_id";
		
	// Sponsor Bank Config Report
	public static final String GET_SPONSOR_BANK_CB_REPORT = "GET_SPONSOR_BANK_CB_REPORT";
	public static final String O_SPONSOR_BANK_CB = "O_SPONSOR_BANK_CB";

	// Interchange Clearing Information
	public static final String GET_TXN_CLEARING_TAB_INFO = "get_txn_clearing_tab_info";
	public static final String O_TXN_CLEARING_TAB_INFO = "o_txn_clearing_tab_info";
	
	// Interchange Clearing QS Report
	public static final String GET_TXN_CLEARING_INFO_REPORT = "get_txn_clearing_info_report";
	public static final String O_TXN_CLEARING_INFO = "o_txn_clearing_info";
	
	public static final String I_FLAG = "i_flag";
	public static final String I_CHECKBOX = "i_checkbox";
	public static final String I_MID_FLAG = "i_mid_flag";
	public static final String O_CREDIT_CURSOR = "o_credit_cursor";
	public static final String I_FROMCODE = "i_fromcode";
	public static final String I_RECON_STATUS = "i_recon_status";
	
	// APC Constants for Txn Type SP
	public static final String GETTXNTYPELIST = "gettxntypelist";
	public static final String O_TXN_TYPE_INFO = "o_txn_type_info";
	public static final String O_TXN_TYPE_CHARGEBACK ="O_TXN_TYPE_CHARGEBACK";
	public static final String TXN_TYPE_NAME = "txn_type_name";
	public static final String TXN_TYPE_VALUE = "txn_type_value";
	public static final String TXN_TYPE_MAP = "txnTypeMap";

	public static final String GET_ALERT_CONFIG_MERCHANT = "GET_ALERT_CONFIG_MERCHANT";
	public static final String O_ALERT_CONFIG_MERCHANT = "O_ALERT_CONFIG_MERCHANT";
	public static final String UPDATE_ALERT_CONFIG_MERCHANT = "update_alert_config_merchant";
	public static final String I_ROW_DATA = "I_ROW_DATA";

	public static final String INDENTIFIER_NAME = "INDENTIFIER_NAME";
	public static final String IDENTIFIER_MSG = "IDENTIFIER_MSG";
	public static final String ENABLE_DISABLE = "ENABLE_DISABLE";
	public static final String IDENTIFIER_ID = "IDENTIFIER_ID";
	public static final String ENTITY_ALERT_ID = "ENTITY_ALERT_ID";

	public static final String GET_CARD_ORDER_LIST = "GET_CARD_ORDER_LIST";
	public static final String O_CARD_ORDER_LIST = "O_CARD_ORDER_LIST";
	public static final String CARD_ORDER_VALUE = "CARD_ORDER_VALUE";
	public static final String CARD_ORDER_ID = "CARD_ORDER_ID";
	public static final String DELETE_NEWSINFO = "delete_newsinfo";
	
	public static final String LAST_MODIFIED_DATE = "LAST_MODIFIED_DATE";

	public static final String GET_NEGATIVE_BLOCK_HIT_REPORT = "get_negative_block_hit_report";
	public static final String O_NEGATIVE_HIT_LIST = "o_negative_hit_list";
	public static final String I_BLOCK_TYPE = "i_block_type";
	public static final String I_BLOCK_CTGRY = "I_BLOCK_CTGRY";
	public static final String I_SCOPE = "I_SCOPE";
	public static final String I_VALUE = "i_value";
	public static final String O_BLOCK_LIST = "O_BLOCK_LIST";

	public static final String GET_SPONSOR_BANK_DETAIL_REPORT = "GET_SPONSOR_BANK_DETAIL_REPORT";
	public static final String O_SPONSOR_BANK_DETAIL = "O_SPONSOR_BANK_DETAIL";

	public static final String GET_SPONSOR_BANK_SUMMARY_REPORT = "GET_SPONSOR_BANK_SUMMARY_REPORT";
	public static final String O_SPONSOR_BANK_SUMMARY = "O_SPONSOR_BANK_SUMMARY";

	public static final String I_TIMESTAMP_TYPE = "i_timestamp_type";
	
	public static final String GET_REFUND_RATES_REPORT = "GET_REFUND_RATES_REPORT";
	public static final String O_REFUND_RATES_DETAILS = "O_REFUND_RATES_DETAILS";
	public static final String GET_ENTITY_FUND = "get_entity_fund";
	public static final String I_REFUND_TYPE = "I_REFUND_TYPE";
	public static final String ADD_ENTITY_FUND = "ADD_ENTITY_FUND";
	public static final String UPDATE_ENTITY_FUND = "UPDATE_ENTITY_FUND";
	public static final String O_FUNDDETAILS = "o_funddetails";
	
	public static final String ORDER_ID = "OrderId";
	public static final String TXN_ID = "TxnID";
	public static final String TRANS_TYPE = "TxnType";
	public static final String SETTLE_AMOUNT = "SettleAmount";
	public static final String REFUND_AMOUNT = "RefundAmount";
	public static final String PREF_OPTION = "PrefOption";
	
	public static final String GET_BULK_LIST = "get_bulk_list";
	public static final String O_GET_BULK_LIST = "o_get_bulk_list";

	public static final String BULK_TXN_ID_LIST = "TxnIdList";
	public static final String BULK_TXN_TYPE = "TxnType";
	public static final String BULK_CLIENT_DATE = "ClientDate";
	
	public static final String BULK_SERVICE_SERVER_URL = "bulk.service.server.url";
	
	public static final String GET_AUTH_BY_BIN_SUMMARY_REPORT = "get_auth_by_bin_summary_report";
	public static final String O_AUTH_BY_BIN_SUMMARY = "o_auth_by_bin_summary";
	
	public static final String TXN_DATE = "TXN_DATE";
	public static final String BIN_ID1 = "BIN_ID";
	public static final String BIN_COUNTRY = "BIN_COUNTRY";
	public static final String AUTH_COUNT = "AUTH_COUNT";
	public static final String DECLINE_COUNT = "DECLINE_COUNT";
	public static final String AUTH_RATE = "AUTH_RATE";
	public static final String DECLINE_RATE = "DECLINE_RATE";
	public static final String CHANNEL_TYPE = "CHANNEL_TYPE";
	public static final String I_MERCHANT_CROSS_REF = "i_merchant_cross_ref";
		
	public static final String I_AMOUNT_AVG_TICKET = "i_amount_Avg_Ticket";
	public static final String I_CURRENCY_AVG_TICKET = "i_currency_Avg_Ticket";
	public static final String I_AMOUNT_EXP_MONTHLY_TURNOVER = "i_amount_Exp_Monthly_TurnOver";
	public static final String I_CURRENCY_EXP_MONTHLY_TURNOVER = "i_currency_Exp_Monthly_TurnOver";
	public static final String I_AMOUNT_EXP_ANNUAL_TURNOVER = "i_amount_Exp_Annual_TurnOver";
	public static final String I_CURRENCY_EXP_ANNUAL_TURNOVER = "i_currency_Exp_Annual_TurnOver";
	
	public static final String ENTITY_ID_1 = "ENTITY_ID";
	public static final String FUND_TYPE = "FUND_TYPE";
	public static final String NAME1 = "NAME";
	public static final String EMAIL1 = "EMAIL";
	public static final String MOBILE_NUMBER = "MOBILE_NUMBER";
	public static final String PHONE_NUMBER1 = "PHONE_NUMBER";
	public static final String AMOUNT_AVG_TICKET = "AMOUNT_AVG_TICKET";
	public static final String CURRENCY_AVG_TICKET = "CURRENCY_AVG_TICKET";
	public static final String AMOUNT_EXP_MONTHLY_TURNOVER = "AMOUNT_EXP_MONTHLY_TURNOVER";
	public static final String CURRENCY_EXP_MONTHLY_TURNOVER = "CURRENCY_EXP_MONTHLY_TURNOVER";
	public static final String AMOUNT_EXP_ANNUAL_TURNOVER = "AMOUNT_EXP_ANNUAL_TURNOVER";
	public static final String CURRENCY_EXP_ANNUAL_TURNOVER = "CURRENCY_EXP_ANNUAL_TURNOVER";
	public static final String GET_AUTH_SETTLE_CUR_PM = "GET_AUTH_SETTLE_CUR_PM";
	public static final String BULK_SETTLEMENT_SUCCESS = "BULK_SETTLEMENT_SUCCESS";
	public static final String BULK_REFUND_SUCCESS = "BULK_REFUND_SUCCESS";
	public static final String ENTITY_STATUS = "entity_status";
	public static final String O_APPROVE_KYC = "O_APPROVE_KYC";
	public static final String VERIFIED = "Verified";
	public static final String DIRECTORNAMEANDADDRESS = "Director's Name and Address";
	public static final String BENEFICIALOWNERSIDENTIFIED = "Beneficial Owners Identified";
}
