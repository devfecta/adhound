package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
@Generated("com.robohorse.robopojogenerator")
public class Frequency{

	@JsonProperty("interval_count")
	private int intervalCount;

	@JsonProperty("interval_unit")
	private String intervalUnit;

    /**
     * Set interval count.
     *
     * @param intervalCount the interval count
     */
    public void setIntervalCount(int intervalCount){
		this.intervalCount = intervalCount;
	}

    /**
     * Get interval count int.
     *
     * @return the int
     */
    public int getIntervalCount(){
		return intervalCount;
	}

    /**
     * Set interval unit.
     *
     * @param intervalUnit the interval unit
     */
    public void setIntervalUnit(String intervalUnit){
		this.intervalUnit = intervalUnit;
	}

    /**
     * Get interval unit string.
     *
     * @return the string
     */
    public String getIntervalUnit(){
		return intervalUnit;
	}

	@Override
 	public String toString(){
		return 
			"Frequency{" + 
			"interval_count = '" + intervalCount + '\'' + 
			",interval_unit = '" + intervalUnit + '\'' + 
			"}";
		}
}