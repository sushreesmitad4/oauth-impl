package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.PasswordDTO;


@Path("/password")
public interface PasswordWebService {

	@POST
	@Path("/forgot")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response forgotPassword(PasswordDTO dto);
	
	@POST
	@Path("/change")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response changePassword(PasswordDTO dto);
    
	

	
}
 