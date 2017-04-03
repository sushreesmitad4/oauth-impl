package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.UserDTO;


@Path("/coupon")
public interface CouponWebService {

	
	
	@Path("/getUserCoupons")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getUserCoupon(RewardDTO rewardDTO);
	
	
	@Path("/getUserRewards")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getUserReward(RewardDTO rewardDTO);
	
	
	
	@Path("/updateUserRewards")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateUserRewards(RewardDTO rewardDTO);
}
