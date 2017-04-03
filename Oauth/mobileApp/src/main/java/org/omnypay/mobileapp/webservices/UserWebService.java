package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;



/**
 * 
 * @author iliyasm
 *
 */
@Path("/user")
public interface UserWebService {

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response register(UserDTO userDTO);
	
	@Path("/registerInStore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response registerInStore(BaseDTO baseDTO);
	
		
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginDTO loginDTO);
	
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(BaseDTO baseDTO);
	
	
	
	@POST
	@Path("/updateUserPro")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateUserProfile(UpdateUserDTO updateUserDTO);
	
	
}
