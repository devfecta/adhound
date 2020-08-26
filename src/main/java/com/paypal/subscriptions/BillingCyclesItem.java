package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
@Generated("com.robohorse.robopojogenerator")
public class BillingCyclesItem{

	@JsonProperty("sequence")
	private int sequence;

	@JsonProperty("tenure_type")
	private String tenureType;

	@JsonProperty("total_cycles")
	private int totalCycles;

	@JsonProperty("pricing_scheme")
	private PricingScheme pricingScheme;

	@JsonProperty("frequency")
	private Frequency frequency;

    /**
     * Set sequence.
     *
     * @param sequence the sequence
     */
    public void setSequence(int sequence){
		this.sequence = sequence;
	}

    /**
     * Get sequence int.
     *
     * @return the int
     */
    public int getSequence(){
		return sequence;
	}

    /**
     * Set tenure type.
     *
     * @param tenureType the tenure type
     */
    public void setTenureType(String tenureType){
		this.tenureType = tenureType;
	}

    /**
     * Get tenure type string.
     *
     * @return the string
     */
    public String getTenureType(){
		return tenureType;
	}

    /**
     * Set total cycles.
     *
     * @param totalCycles the total cycles
     */
    public void setTotalCycles(int totalCycles){
		this.totalCycles = totalCycles;
	}

    /**
     * Get total cycles int.
     *
     * @return the int
     */
    public int getTotalCycles(){
		return totalCycles;
	}

    /**
     * Set pricing scheme.
     *
     * @param pricingScheme the pricing scheme
     */
    public void setPricingScheme(PricingScheme pricingScheme){
		this.pricingScheme = pricingScheme;
	}

    /**
     * Get pricing scheme pricing scheme.
     *
     * @return the pricing scheme
     */
    public PricingScheme getPricingScheme(){
		return pricingScheme;
	}

    /**
     * Set frequency.
     *
     * @param frequency the frequency
     */
    public void setFrequency(Frequency frequency){
		this.frequency = frequency;
	}

    /**
     * Get frequency frequency.
     *
     * @return the frequency
     */
    public Frequency getFrequency(){
		return frequency;
	}

	@Override
 	public String toString(){
		return 
			"BillingCyclesItem{" + 
			"sequence = '" + sequence + '\'' + 
			",tenure_type = '" + tenureType + '\'' + 
			",total_cycles = '" + totalCycles + '\'' + 
			",pricing_scheme = '" + pricingScheme + '\'' + 
			",frequency = '" + frequency + '\'' + 
			"}";
		}
}