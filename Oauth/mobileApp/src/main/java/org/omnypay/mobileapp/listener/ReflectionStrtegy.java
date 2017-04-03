package org.omnypay.mobileapp.listener;


import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.omnypay.common.services.dto.Status;
import com.omnypay.merchant.services.MerchantUserServices;


public class ReflectionStrtegy {

 public static Status getMethod(MerchantUserServices merchantUserServices,Object omnypayServices,Object merchantDto,Object dtoObject, String methodname,boolean isExternal)
 {
		try {
			Class<?> cls  ;
			 if (isExternal){
			 cls = Class.forName(merchantUserServices.getClass().getName());
		
			 }
			 else {
				 cls = Class.forName(omnypayServices.getClass().getName());
				
			 }
				Object obj = cls.newInstance();
				Method m[] = cls.getDeclaredMethods();	 
			
			for (Method mm : m) {

				if (mm.getName().equals(methodname)) {
									 
						 return (Status) mm.invoke(obj,merchantDto, dtoObject);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 return null;
	}
 

	/*public static void main(String[] args) {
		ReflectionStrtegy reflectionvalid=new ReflectionStrtegy();
		CommonServiceImpl commonServiceImpl=new CommonServiceImpl();
		reflectionvalid.getMethod(commonServiceImpl, "getAccess");
	}*/
	
 
 
 
 

	
}
