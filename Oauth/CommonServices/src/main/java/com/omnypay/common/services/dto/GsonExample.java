package com.omnypay.common.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class GsonExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//boolean isSplit = true ;
		
		List<TransactionDTO> listObje = new ArrayList<TransactionDTO>();

		TransactionDTO obj = new TransactionDTO();
		TransactionDTO obj1 = new TransactionDTO();
		
		obj.setAccId("111");
		obj.setPosId("44444444444");
		obj.setEmailId("aa@aa.aa");
		obj.setMerchantAccessKey("xwh-nxdee6tdh4yf8a6sx0zq6ru-s2ph");
		obj.setPhoneNumber("9666666666666");
		obj.setImeiNo("495052015072815493749505");
		obj.setSplit(true);
		obj.setAmount("80");
		obj.setTotalAmount("100");
		obj1.setAccId("111");
		obj1.setPosId("111111111");
		obj1.setEmailId("sdghsdgh");
		obj1.setMerchantAccessKey("11111111111111111");
		obj1.setPhoneNumber("22222222");
		obj1.setImeiNo("123456789");
		obj1.setSplit(true);
		obj1.setAmount("20");
		obj1.setTotalAmount("100");
		
		listObje.add(obj);
		listObje.add(obj1);
		
		Gson gson = new Gson();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		String json1 = gson.toJson(listObje);
		
		//String json1 = gson.toJson(isSplit);
		System.out.println("json1"+json1);

	}

}
