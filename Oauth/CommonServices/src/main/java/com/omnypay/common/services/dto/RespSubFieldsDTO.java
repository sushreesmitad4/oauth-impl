package com.omnypay.common.services.dto;

public class RespSubFieldsDTO {

	private long questionId;
	private String questionName;
	private String quesAnswer;
	private String respString;

	public RespSubFieldsDTO() {
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getQuesAnswer() {
		return quesAnswer;
	}

	public void setQuesAnswer(String quesAnswer) {
		this.quesAnswer = quesAnswer;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	
	
	

	public String getRespString() {
		return respString;
	}

	public void setRespString(String respString) {
		this.respString = respString;
	}

	public String toString() {

		return "[ Request Message=> questionId : " + getQuestionId()
				+ " , questionName : " + getQuestionName() + " , quesAnswer : "
				+ getQuesAnswer() + "]";
	}
}
