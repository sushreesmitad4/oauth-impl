/**
 * 
 */
package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.BaseDTO;



/**
 * @author iliyasm
 *
 */

@Path("/cust")
public interface CustomerWebService {
	
	
	
	
	@Path("/fetchCust")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response fetchCustomerDetails(BaseDTO baseDTO);

}
