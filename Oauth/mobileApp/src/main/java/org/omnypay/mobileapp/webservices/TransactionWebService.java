package org.omnypay.mobileapp.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.TransactionDTO;


/**
 * 
 * @author iliyasm
 *
 */
@Path("/transaction")
public interface TransactionWebService {

	@Path("/initiate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response initiate(TransactionDTO tranDTO);
	
/*	@Path("/process")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response process(TransactionDTO tranDTO);*/
	
	
		
	@Path("/history")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getHistory(TransactionDTO transDTO);
	
	
	@Path("/accBasedHistory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getAccountBasedTransactionSummary(TransactionDTO transDTO);
	
	
	@Path("/storeRequestOfAmountSave")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    Response storeRequestOfAmountSave(TransactionDTO transDTO);
	
	
	@Path("/pingRequestFromStore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    Response pingRequestFromStore(TransactionDTO transDTO);
	
	
	
	
	@Path("/timeOutReqFromUnicenta")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    Response getTimeOutReqFromUnicenta(TransactionDTO transDTO);

	
	
	@Path("/requestFromSwitch")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response switchUpdate(TransactionDTO transDTO);
	
	
	
	@Path("/amountRequestOfEcomm")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response amountRequestFromEcommerce(TransactionDTO transDTO);
	
	
	
	@Path("/pingRequestFromEcomm")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response pingRequestFromEcommerce(TransactionDTO transDTO);
	
	
	
	@Path("/processSplit")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	List<Response> processSplit(List<TransactionDTO> tranDTOList);
	//List<Response> processSplit(List<TransactionDTO> tranDTOList, boolean isSplit);
	
	
	

}
