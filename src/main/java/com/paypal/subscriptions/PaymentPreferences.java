package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
@Generated("com.robohorse.robopojogenerator")
public class PaymentPreferences{

	@JsonProperty("setup_fee")
	private SetupFee setupFee;

	@JsonProperty("setup_fee_failure_action")
	private String setupFeeFailureAction;

	@JsonProperty("auto_bill_outstanding")
	private boolean autoBillOutstanding;

	@JsonProperty("payment_failure_threshold")
	private int paymentFailureThreshold;

	@JsonProperty("service_type")
	private String serviceType;

    /**
     * Set setup fee.
     *
     * @param setupFee the setup fee
     */
    public void setSetupFee(SetupFee setupFee){
		this.setupFee = setupFee;
	}

    /**
     * Get setup fee setup fee.
     *
     * @return the setup fee
     */
    public SetupFee getSetupFee(){
		return setupFee;
	}

    /**
     * Set setup fee failure action.
     *
     * @param setupFeeFailureAction the setup fee failure action
     */
    public void setSetupFeeFailureAction(String setupFeeFailureAction){
		this.setupFeeFailureAction = setupFeeFailureAction;
	}

    /**
     * Get setup fee failure action string.
     *
     * @return the string
     */
    public String getSetupFeeFailureAction(){
		return setupFeeFailureAction;
	}

    /**
     * Set auto bill outstanding.
     *
     * @param autoBillOutstanding the auto bill outstanding
     */
    public void setAutoBillOutstanding(boolean autoBillOutstanding){
		this.autoBillOutstanding = autoBillOutstanding;
	}

    /**
     * Is auto bill outstanding boolean.
     *
     * @return the boolean
     */
    public boolean isAutoBillOutstanding(){
		return autoBillOutstanding;
	}

    /**
     * Set payment failure threshold.
     *
     * @param paymentFailureThreshold the payment failure threshold
     */
    public void setPaymentFailureThreshold(int paymentFailureThreshold){
		this.paymentFailureThreshold = paymentFailureThreshold;
	}

    /**
     * Get payment failure threshold int.
     *
     * @return the int
     */
    public int getPaymentFailureThreshold(){
		return paymentFailureThreshold;
	}

    /**
     * Set service type.
     *
     * @param serviceType the service type
     */
    public void setServiceType(String serviceType){ this.serviceType = serviceType; }

    /**
     * Get service type string.
     *
     * @return the string
     */
    public String getServiceType(){
		return serviceType;
	}

	@Override
 	public String toString(){
		return 
			"PaymentPreferences{" + 
			"setup_fee = '" + setupFee + '\'' + 
			",setup_fee_failure_action = '" + setupFeeFailureAction + '\'' + 
			",auto_bill_outstanding = '" + autoBillOutstanding + '\'' + 
			",payment_failure_threshold = '" + paymentFailureThreshold + '\'' + 
			"}";
		}
}