package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
@Generated("com.robohorse.robopojogenerator")
public class FixedPrice{

	@JsonProperty("value")
	private String value;

	@JsonProperty("currency_code")
	private String currencyCode;

    /**
     * Set value.
     *
     * @param value the value
     */
    public void setValue(String value){
		this.value = value;
	}

    /**
     * Get value string.
     *
     * @return the string
     */
    public String getValue(){
		return value;
	}

    /**
     * Set currency code.
     *
     * @param currencyCode the currency code
     */
    public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

    /**
     * Get currency code string.
     *
     * @return the string
     */
    public String getCurrencyCode(){
		return currencyCode;
	}

	@Override
 	public String toString(){
		return 
			"FixedPrice{" + 
			"value = '" + value + '\'' + 
			",currency_code = '" + currencyCode + '\'' + 
			"}";
		}
}