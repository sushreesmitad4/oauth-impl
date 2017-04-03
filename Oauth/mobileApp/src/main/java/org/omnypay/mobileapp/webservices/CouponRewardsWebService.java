package org.omnypay.mobileapp.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.UserDTO;


@Path("/coupon")
public interface CouponRewardsWebService {

	
	
	@Path("/getUserCoupons")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getUserCoupon(BaseDTO baseDTO);
	
	
	
	
	@Path("/cc")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response calCoupons(CouponDTO couponDTO);
	
	
	@Path("/getUserRewards")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getUserReward(BaseDTO baseDTO);
	
	
	
	@Path("/cr")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response calReward(RewardDTO rewardDTO);
}
