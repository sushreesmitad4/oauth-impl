/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class SecurityQuestionsDTO extends BaseDTO {

	private String questionId1;

	private String questionId2;

	private String questionId3;

	private String questionId4;

	private String questionId5;

	private String answer1;

	private String answer2;

	private String answer3;

	private String answer4;

	private String answer5;

	/**
	 * @return the questionId1
	 */
	public String getQuestionId1() {
		return questionId1;
	}

	/**
	 * @param questionId1
	 *            the questionId1 to set
	 */
	public void setQuestionId1(String questionId1) {
		this.questionId1 = questionId1;
	}

	/**
	 * @return the questionId2
	 */
	public String getQuestionId2() {
		return questionId2;
	}

	/**
	 * @param questionId2
	 *            the questionId2 to set
	 */
	public void setQuestionId2(String questionId2) {
		this.questionId2 = questionId2;
	}

	/**
	 * @return the questionId3
	 */
	public String getQuestionId3() {
		return questionId3;
	}

	/**
	 * @param questionId3
	 *            the questionId3 to set
	 */
	public void setQuestionId3(String questionId3) {
		this.questionId3 = questionId3;
	}

	/**
	 * @return the questionId4
	 */
	public String getQuestionId4() {
		return questionId4;
	}

	/**
	 * @param questionId4
	 *            the questionId4 to set
	 */
	public void setQuestionId4(String questionId4) {
		this.questionId4 = questionId4;
	}

	/**
	 * @return the questionId5
	 */
	public String getQuestionId5() {
		return questionId5;
	}

	/**
	 * @param questionId5
	 *            the questionId5 to set
	 */
	public void setQuestionId5(String questionId5) {
		this.questionId5 = questionId5;
	}

	/**
	 * @return the answer1
	 */
	public String getAnswer1() {
		return answer1;
	}

	/**
	 * @param answer1
	 *            the answer1 to set
	 */
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	/**
	 * @return the answer2
	 */
	public String getAnswer2() {
		return answer2;
	}

	/**
	 * @param answer2
	 *            the answer2 to set
	 */
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	/**
	 * @return the answer3
	 */
	public String getAnswer3() {
		return answer3;
	}

	/**
	 * @param answer3
	 *            the answer3 to set
	 */
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	/**
	 * @return the answer4
	 */
	public String getAnswer4() {
		return answer4;
	}

	/**
	 * @param answer4
	 *            the answer4 to set
	 */
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	/**
	 * @return the answer5
	 */
	public String getAnswer5() {
		return answer5;
	}

	/**
	 * @param answer5
	 *            the answer5 to set
	 */
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ", questionId1 : " + getQuestionId1()
				+ " , answer1 : " + "xxxx" + " , questionId2 : "
				+ getQuestionId2() + " , answer2 : " + "xxxx"
				+ ", questionId3 : " + getQuestionId3() + " , answer3 : "
				+ "xxxx" + " , questionId4 : " + getQuestionId4()
				+ " , answer4 : " + "xxxx" + " , questionId5 : "
				+ getQuestionId5() + " , answer5 : " + "xxxx" + " , merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}

}
