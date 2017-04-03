package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.AccountDTO;



@Path("/Account")
public interface AccountWebService {

	@Path("/addCard")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response addCard(AccountDTO accountDTO);
	
	@Path("/addBank")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response addBank(AccountDTO accountDTO);
	
	@Path("/deleteAcct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response deleteAccount(AccountDTO accountDTO);
	
	@Path("/getMoreToken")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMoreOffLineToken(AccountDTO accountDTO);
	
	
	@Path("/getDataForCheckScan")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkScanRequest(AccountDTO accountDTO);
}
