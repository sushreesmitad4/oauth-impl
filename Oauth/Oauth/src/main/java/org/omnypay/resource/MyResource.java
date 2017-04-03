package org.omnypay.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;


@Path("/istokenvalid")
public class MyResource {
	
	
	@GET
	@Produces(value="application/json")
	public String createInfo(){
		System.out.println("inside of resource");
		String responseValid= "{\"token_valid\":\"true\"}";
		
		return responseValid;

		
		
	}
	@POST
	@Produces(value="application/json")
	public String tokenTest(){
		System.out.println("inside of resource");
		String responseValid= "{\"token_valid\":\"true\"}";
		
		return responseValid;

		
		
	}
}
