package com.omnypay.merchant.kohls.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author jagdishm
 *
 */
public class MerchantDetailValidator {

	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static boolean validateEmailAddress(String emailAddress) {

		String emailpattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		return validatePattern(emailpattern, emailAddress);

	}

	/**
	 * 
	 * @param phoneNo
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNo) {
		// validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}"))
			return true;
		// validating phone number with -, . or spaces
		else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return true;
		// validating phone number with extension length from 3 to 5
		else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;

	}

	/**
	 * 
	 * @param passcode
	 * @return
	 */
	public static Boolean validatePasscode(String passcode) {
		String passcodePattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";
		if (passcode != null) {
			return validatePattern(passcodePattern, passcode);
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param zip
	 * @return
	 */
	public static Boolean validateZipCode(String zip) {

		if (zip != null) {
			String zipcodePattern = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
			return validatePattern(zipcodePattern, zip);

		} else {
			return false;

		}

	}

	/**
	 * 
	 * @param imeiNo
	 * @return
	 */
	public static Boolean validateImeiNo(String imeiNo) {
		Boolean isvalidImeiNo = null;
		String regeximei = "[0-9]+";
		isvalidImeiNo = validatePattern(regeximei, imeiNo);

		if (imeiNo.length() == 10 && isvalidImeiNo) {
			return isvalidImeiNo;
		}

		return isvalidImeiNo;

	}

	/***
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNoNumberAtBeginning(String s) {
		Boolean isvalidNumber = false;
		if (s != null) {

			String regeximei = "^[^\\d].*";
			isvalidNumber = validatePattern(regeximei, s);
			return isvalidNumber;
		}
		return isvalidNumber;
	}

	/**
	 * 
	 * @param nonNumeric
	 * @return
	 */
	public static boolean isCharString(String nonNumeric) {
		Boolean isvalidString = false;
		String strPattern = "^[a-zA-Z]+$";
		if (nonNumeric != null) {

			isvalidString = validatePattern(strPattern, nonNumeric);

		}

		return isvalidString;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Boolean validateAccountNo(String number) {
		return !isCharString(number);

	}

	/**
	 * 
	 * @param username
	 * @return
	 */

	public static Boolean validateUserName(String username) {
		Boolean isValiduser = false;
		String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

		if (username != null) {

			isValiduser = validatePattern(USERNAME_PATTERN, username);
		}

		return isValiduser;
	}

	/**
	 * 
	 * @param accountHolderName
	 * @return
	 */
	public static Boolean validateAccountHolderName(String accountHolderName) {
		if (accountHolderName != null) {
			return validateUserName(accountHolderName);
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isValidIP(String ipAddr) {
		Boolean isvalidIp = false;

		String pattern = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
		if (ipAddr != null) {
			isvalidIp = validatePattern(pattern, ipAddr);
		}

		return isvalidIp;
	}

	/**
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean validateStringLength(String str, int length) {
		if (str != null && str.length() <= length) {

			return true;

		} else {

			return false;
		}

	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isValidAlphaNumeric(String input) {
		String alphaNumeric = "^[A-Za-z0-9]+$";

		if (input != null) {
			return validatePattern(alphaNumeric, input);
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param pattern
	 * @param tomatch
	 * @return
	 */

	public static Boolean validatePattern(String pattern, String tomatch) {

		Pattern ptn = Pattern.compile(pattern);
		Matcher mtch = ptn.matcher(tomatch);
		return mtch.find();

	}

	/***
	 * 
	 * @param dateToValidate
	 * @param dateFromat
	 * @return
	 */
	public boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param expirydate
	 * @return
	 */
	public static boolean validateCardExpiryDate(Timestamp expirydate) {
		Timestamp cardExpDate = expirydate;
		Timestamp currentDate = getCurrentTimeStamp();
		long cardExpDateInLong = cardExpDate.getTime();
		long currentTimeInInLong = currentDate.getTime();
		long diffInMilis = currentTimeInInLong - cardExpDateInLong;
		if (diffInMilis > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */

	public static Timestamp getCurrentTimeStamp() {

		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
				now.getTime());
		return currentTimestamp;
	}

	/**
	 * 
	 * @param cardExpDate
	 * @return
	 */
	public static Integer getMonth(Timestamp cardExpDate) {

		long tokenReqDateInLong = cardExpDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(tokenReqDateInLong);
		StringBuilder tokenmonth = new StringBuilder();
		tokenmonth.append(String.valueOf(cal.get(Calendar.MONTH) + 1));
		return Integer.parseInt(tokenmonth.toString());

	}
	
	/**
	 * 
	 * @param expyear
	 * @return
	 */
	
	public static Boolean validateCardExpYear(String expyear)
	{		
	int currentYear=getYear(new Timestamp(System.currentTimeMillis()));
	int expireyear=Integer.parseInt(expyear);
	if(expireyear>currentYear){
		return true;
	}else{
		return false;
	}
	
	}


	/**
	 * 
	 * @param cardExpYear
	 * @return
	 */
	public static Integer getYear(Timestamp cardExpYear) {

		long cardExpReqDateInLong = cardExpYear.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cardExpReqDateInLong);
		StringBuilder tokenminute = new StringBuilder();
		tokenminute.append(String.valueOf(cal.get(Calendar.YEAR)));
		return Integer.parseInt(tokenminute.toString());
	}

}
