package com.omnypay.common.services.dto;

public class TranSubFieldsDTO {

	private int transAmout;

	public TranSubFieldsDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getTransAmout() {
		return transAmout;
	}

	public void setTransAmout(int transAmout) {
		this.transAmout = transAmout;
	}

	public String toString() {

		return "[ Request Message=> transAmout : " + getTransAmout() + "]";
	}
}
