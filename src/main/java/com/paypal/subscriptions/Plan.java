package com.paypal.subscriptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Generated;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
@Generated("com.robohorse.robopojogenerator")
public class Plan {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@JsonProperty("quantity_supported")
	private boolean quantitySupported;

	@JsonProperty("update_time")
	private String updateTime;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("payment_preferences")
	private PaymentPreferences paymentPreferences;

	@JsonProperty("product_id")
	private String productId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("billing_cycles")
	private List<BillingCyclesItem> billingCycles;

	@JsonProperty("description")
	private String description;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("status")
	private String status;

	@JsonProperty("usage_type")
	private String usageType;


    /**
     * Set quantity supported.
     *
     * @param quantitySupported the quantity supported
     */
    public void setQuantitySupported(boolean quantitySupported){
		this.quantitySupported = quantitySupported;
	}

    /**
     * Is quantity supported boolean.
     *
     * @return the boolean
     */
    public boolean isQuantitySupported(){
		return quantitySupported;
	}

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
     * Set payment preferences.
     *
     * @param paymentPreferences the payment preferences
     */
    public void setPaymentPreferences(PaymentPreferences paymentPreferences){
		this.paymentPreferences = paymentPreferences;
	}

    /**
     * Get payment preferences payment preferences.
     *
     * @return the payment preferences
     */
    public PaymentPreferences getPaymentPreferences(){
		return paymentPreferences;
	}

    /**
     * Set product id.
     *
     * @param productId the product id
     */
    public void setProductId(String productId){
		this.productId = productId;
	}

    /**
     * Get product id string.
     *
     * @return the string
     */
    public String getProductId(){
		return productId;
	}

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name){
		this.name = name;
	}

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){
		return name;
	}

    /**
     * Sets billing cycles.
     *
     * @param billingCycles the billing cycles
     */
    public void setBillingCycles(List<BillingCyclesItem> billingCycles) {

		this.billingCycles = billingCycles;
	}

    /**
     * Get billing cycles list.
     *
     * @return the list
     */
    public List<BillingCyclesItem> getBillingCycles(){
		return billingCycles;
	}

    /**
     * Set description.
     *
     * @param description the description
     */
    public void setDescription(String description){
		this.description = description;
	}

    /**
     * Get description string.
     *
     * @return the string
     */
    public String getDescription(){
		return description;
	}

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(List<LinksItem> links) { this.links = links; }

    /**
     * Get links list.
     *
     * @return the list
     */
    public List<LinksItem> getLinks(){
		return links;
	}

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(String id){
		this.id = id;
	}

    /**
     * Get id string.
     *
     * @return the string
     */
    public String getId(){
		return id;
	}

    /**
     * Set status.
     *
     * @param status the status
     */
    public void setStatus(String status){
		this.status = status;
	}

    /**
     * Get status string.
     *
     * @return the string
     */
    public String getStatus(){
		return status;
	}

    /**
     * Set usage type.
     *
     * @param usageType the usage type
     */
    public void setUsageType(String usageType){
		this.usageType = usageType;
	}

    /**
     * Get usage type string.
     *
     * @return the string
     */
    public String getUsageType(){
		return usageType;
	}

	@Override
 	public String toString(){
		return 
			"Plan{" +
			"quantity_supported = '" + quantitySupported + '\'' +
			",update_time = '" + updateTime + '\'' +
			",create_time = '" + createTime + '\'' +
			",payment_preferences = '" + paymentPreferences + '\'' +
			",product_id = '" + productId + '\'' +
			",name = '" + name + '\'' +
			",billing_cycles = '" + billingCycles + '\'' +
			",description = '" + description + '\'' +
			",links = '" + links + '\'' +
			",id = '" + id + '\'' +
			",status = '" + status + '\'' +
			",usageType = '" + usageType + '\'' +
			"}";
		}


}