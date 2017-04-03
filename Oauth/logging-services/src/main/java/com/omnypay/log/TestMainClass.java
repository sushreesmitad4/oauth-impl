/**
 * 
 */
package com.omnypay.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//import org.apache.http.HttpResponse;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.entity.ContentType;
//import org.apache.http.HttpResponse;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.JSONObject;
///import org.json.simple.parser.ParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
/*import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/







/**
 * @author iliyasm
 *
 */
public class TestMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Long totalRewardpoint = 65l; 
		Long rwrdpoint=3l;
		Long  addRewardPoint = 0l;
		
		Long mydedectedRewardPoint =0l;
		 mydedectedRewardPoint = totalRewardpoint-rwrdpoint;
		System.out.println("deducted" +mydedectedRewardPoint);
		addRewardPoint  = (long) (627.0*0.1);
		System.out.println("add" +addRewardPoint);
		Long totalRewardCalc = (totalRewardpoint - mydedectedRewardPoint) + addRewardPoint;
		System.out.println(totalRewardCalc);
				
		//TransactionDTO obj = new TransactionDTO();
		
		//Gson gson = new Gson();
		
		/*String json = gson.toJson(obj);
		
		System.out.println(json);*/
		
	}
	
}

