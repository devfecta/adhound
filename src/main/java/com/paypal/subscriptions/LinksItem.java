package com.paypal.subscriptions;

/**
 * PayPal object based off of their API.
 *
 * @author kkelm
 */
public class LinksItem{
	private String method;
	private String rel;
	private String href;
	private String encType;

    /**
     * Set method.
     *
     * @param method the method
     */
    public void setMethod(String method){
		this.method = method;
	}

    /**
     * Get method string.
     *
     * @return the string
     */
    public String getMethod(){
		return method;
	}

    /**
     * Set rel.
     *
     * @param rel the rel
     */
    public void setRel(String rel){
		this.rel = rel;
	}

    /**
     * Get rel string.
     *
     * @return the string
     */
    public String getRel(){
		return rel;
	}

    /**
     * Set href.
     *
     * @param href the href
     */
    public void setHref(String href){
		this.href = href;
	}

    /**
     * Get href string.
     *
     * @return the string
     */
    public String getHref(){
		return href;
	}

    /**
     * Set enc type.
     *
     * @param encType the enc type
     */
    public void setEncType(String encType){
		this.encType = encType;
	}

    /**
     * Get enc type string.
     *
     * @return the string
     */
    public String getEncType(){
		return encType;
	}

	@Override
 	public String toString(){
		return 
			"LinksItem{" + 
			"method = '" + method + '\'' + 
			",rel = '" + rel + '\'' + 
			",href = '" + href + '\'' + 
			"}";
		}
}
