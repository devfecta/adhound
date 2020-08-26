package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
@Generated("com.robohorse.robopojogenerator")
public class PricingScheme{

	@JsonProperty("update_time")
	private String updateTime;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("fixed_price")
	private FixedPrice fixedPrice;

	@JsonProperty("version")
	private int version;

    /**
     * Set update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

    /**
     * Get update time string.
     *
     * @return the string
     */
    public String getUpdateTime(){
		return updateTime;
	}

    /**
     * Set create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

    /**
     * Get create time string.
     *
     * @return the string
     */
    public String getCreateTime(){
		return createTime;
	}

    /**
     * Set fixed price.
     *
     * @param fixedPrice the fixed price
     */
    public void setFixedPrice(FixedPrice fixedPrice){
		this.fixedPrice = fixedPrice;
	}

    /**
     * Get fixed price fixed price.
     *
     * @return the fixed price
     */
    public FixedPrice getFixedPrice(){
		return fixedPrice;
	}

    /**
     * Set version.
     *
     * @param version the version
     */
    public void setVersion(int version){
		this.version = version;
	}

    /**
     * Get version int.
     *
     * @return the int
     */
    public int getVersion(){
		return version;
	}

	@Override
 	public String toString(){
		return 
			"PricingScheme{" + 
			"update_time = '" + updateTime + '\'' + 
			",create_time = '" + createTime + '\'' + 
			",fixed_price = '" + fixedPrice + '\'' + 
			",version = '" + version + '\'' + 
			"}";
		}
}