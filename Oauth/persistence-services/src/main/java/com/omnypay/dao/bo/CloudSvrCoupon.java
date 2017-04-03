package com.omnypay.dao.bo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


	/**
	 * @author sushreesmita
	 *
	 */
	@Entity
	@Table(name="COUPON")
	@NamedQuery(name = "CloudSvrCoupon.findAll", query="SELECT c FROM CloudSvrCoupon c")
	public class CloudSvrCoupon {
		
		@Id
	 	@Column(name = "COUPON_ID")
	 	@GeneratedValue
	 	private long couponId;
		
	
		@Column(name = "MAX_REDEMPTION")
	    private long maxRedemption;
		
		@Column(name = "COUPON_ISSUER_ID")
	    private String couponIssuerId;
		
		
		@Column(name = "COUPON_ISSUER_NAME")
	    private String couponIssuerName;
		
		
		@Column(name = "COUPON_TITLE")
	    private String couponTitle;
		
		@Column(name = "VALIDITY_START_DATE")
	    private Timestamp validityStartDate;
		
	
	
		
		
	
		@Column(name = "VALIDITY_END_DATE")
	    private Timestamp validityEndDate;
		
		
		
		@Column(name = "CATEGORY_NAME")
	    private String catagoryName;
		
		
		@Column(name = "TERMS_AND_CONDITIONS")
	    private String termsAndCondition;
		
		
		@Column(name = "COUNTRY_CODE")
	    private String countryCode;
		
		
		@Column(name = "REDEMPTION_CHANNEL")
	    private String redemptionChannel;
		
		
		
		@Column(name = "URL_IDENTIFIER")
	    private String urlIdentifier;
		
		
		@Column(name = "SKU_ID")
	    private String skuId;
		
		
		
		@Column(name = "EXCEPTION_ID")
	    private String exceptionId;
		
		
		
		@Column(name = "SUB_CATEGORY")
	    private String subCatagory;
		
		
		@Column(name = "COUPON_DESCRIPTION")
	    private String couponDescription;
		
		
		@Column(name = "STORE_ID")
	    private String storeId;
		
		
		
		@Column(name = "DISCOUNT_ID")
	    private long discountId;
		
		
		@Column(name = "MERCHANT_ID")
	    private String merchantId;
		
		
		@Column(name = "IS_OFFER")
	    private String isOffer;
		
		
		
		
	/*	@OneToMany(mappedBy = "userCoupon", fetch=FetchType.EAGER,cascade=CascadeType.ALL)	
		private List<CloudSvrUserAssociateCoupon> listUserCoupon;*/
		
		
	
		
		
		/**
		 * @return the isOffer
		 */
		public String getIsOffer() {
			return isOffer;
		}

		/**
		 * @param isOffer the isOffer to set
		 */
		public void setIsOffer(String isOffer) {
			this.isOffer = isOffer;
		}

		
		

		/**
		 * @return the couponId
		 */
		public long getCouponId() {
			return couponId;
		}

		/**
		 * @param couponId the couponId to set
		 */
		public void setCouponId(long couponId) {
			this.couponId = couponId;
		}

		/**
		 * @return the maxRedemption
		 */
		public long getMaxRedemption() {
			return maxRedemption;
		}

		/**
		 * @param maxRedemption the maxRedemption to set
		 */
		public void setMaxRedemption(long maxRedemption) {
			this.maxRedemption = maxRedemption;
		}

	

		/**
		 * @return the couponIssuerName
		 */
		public String getCouponIssuerName() {
			return couponIssuerName;
		}

		/**
		 * @param couponIssuerName the couponIssuerName to set
		 */
		public void setCouponIssuerName(String couponIssuerName) {
			this.couponIssuerName = couponIssuerName;
		}

		/**
		 * @return the couponTitle
		 */
		public String getCouponTitle() {
			return couponTitle;
		}

		/**
		 * @param couponTitle the couponTitle to set
		 */
		public void setCouponTitle(String couponTitle) {
			this.couponTitle = couponTitle;
		}

	

		/**
		 * @return the discountId
		 */
		public long getDiscountId() {
			return discountId;
		}

		/**
		 * @param discountId the discountId to set
		 */
		public void setDiscountId(long discountId) {
			this.discountId = discountId;
		}

		/**
		 * @return the validityStartDate
		 */
		public Timestamp getValidityStartDate() {
			return validityStartDate;
		}

		/**
		 * @param validityStartDate the validityStartDate to set
		 */
		public void setValidityStartDate(Timestamp validityStartDate) {
			this.validityStartDate = validityStartDate;
		}

		/**
		 * @return the validityEndDate
		 */
		public Timestamp getValidityEndDate() {
			return validityEndDate;
		}

		/**
		 * @param validityEndDate the validityEndDate to set
		 */
		public void setValidityEndDate(Timestamp validityEndDate) {
			this.validityEndDate = validityEndDate;
		}

		/**
		 * @return the catagoryName
		 */
		public String getCatagoryName() {
			return catagoryName;
		}

		/**
		 * @param catagoryName the catagoryName to set
		 */
		public void setCatagoryName(String catagoryName) {
			this.catagoryName = catagoryName;
		}

		/**
		 * @return the termsAndCondition
		 */
		public String getTermsAndCondition() {
			return termsAndCondition;
		}

		/**
		 * @param termsAndCondition the termsAndCondition to set
		 */
		public void setTermsAndCondition(String termsAndCondition) {
			this.termsAndCondition = termsAndCondition;
		}

		/**
		 * @return the countryCode
		 */
		public String getCountryCode() {
			return countryCode;
		}

		/**
		 * @param countryCode the countryCode to set
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		/**
		 * @return the redemptionChannel
		 */
		public String getRedemptionChannel() {
			return redemptionChannel;
		}

		/**
		 * @param redemptionChannel the redemptionChannel to set
		 */
		public void setRedemptionChannel(String redemptionChannel) {
			this.redemptionChannel = redemptionChannel;
		}

		/**
		 * @return the urlIdentifier
		 */
		public String getUrlIdentifier() {
			return urlIdentifier;
		}

		/**
		 * @param urlIdentifier the urlIdentifier to set
		 */
		public void setUrlIdentifier(String urlIdentifier) {
			this.urlIdentifier = urlIdentifier;
		}

		/**
		 * @return the skuId
		 */
		public String getSkuId() {
			return skuId;
		}

		/**
		 * @param skuId the skuId to set
		 */
		public void setSkuId(String skuId) {
			this.skuId = skuId;
		}

		/**
		 * @return the subCatagory
		 */
		public String getSubCatagory() {
			return subCatagory;
		}

		/**
		 * @param subCatagory the subCatagory to set
		 */
		public void setSubCatagory(String subCatagory) {
			this.subCatagory = subCatagory;
		}

		/**
		 * @return the couponDescription
		 */
		public String getCouponDescription() {
			return couponDescription;
		}

		/**
		 * @param couponDescription the couponDescription to set
		 */
		public void setCouponDescription(String couponDescription) {
			this.couponDescription = couponDescription;
		}

		/**
		 * @return the merchantId
		 */
		public String getMerchantId() {
			return merchantId;
		}

		/**
		 * @param merchantId the merchantId to set
		 */
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}

/*		*//**
		 * @return the listUserCoupon
		 *//*
		public List<CloudSvrUserAssociateCoupon> getListUserCoupon() {
			return listUserCoupon;
		}

		*//**
		 * @param listUserCoupon the listUserCoupon to set
		 *//*
		public void setListUserCoupon(List<CloudSvrUserAssociateCoupon> listUserCoupon) {
			this.listUserCoupon = listUserCoupon;
		}
		*/
		
		
		
		
		
		
	
}
