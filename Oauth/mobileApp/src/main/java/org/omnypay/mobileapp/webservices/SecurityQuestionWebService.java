package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.SecurityQuestionsDTO;



@Path("/securityQuestion")
public interface SecurityQuestionWebService {
	
	@POST
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response fetchSecurityQuestionList(BaseDTO svrUser);
	
	
	@POST
	@Path("/secQuestionList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSecurityQuestions(BaseDTO baseDTO);
	
	@POST
	@Path("/setQuestionList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO);
	
	
}
