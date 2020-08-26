package com.adhound.service;

import com.adhound.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.subscriptions.Plan;
import com.paypal.subscriptions.Subscribe;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

/**
 * Gets and processes PayPal data to AdHound can communicate with PayPal.
 *
 * @author kkelm
 */
public class PayPal implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String accessToken;
    private Properties properties;

    /**
     * Instantiates a new Pay pal.
     */
    public PayPal() {}

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public String getAccessToken() { return accessToken; }

    /**
     * Sets access token.
     */
    public void setAccessToken() {

        try {

            properties = this.loadProperties("/adhound.properties");
            // Parameters needed to get token
            String tokenParameters = "grant_type=client_credentials";
            System.setProperty("https.protocols", "TLSv1.1,TLSv1.2,SSLv3,SSLv2Hello");
            URL url = new URL(properties.getProperty("paypalTokenUrl"));
            // Establishes a connection to PayPal
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // Gets and formats the PayPal credentials to send to PayPal
            String credentials = properties.getProperty("clientId")+":"+properties.getProperty("secret");
            // Encodes the credentials before sending
            String encoded = Base64.encodeBase64String(credentials.getBytes());
            // Sets Header information
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            conn.setRequestProperty("Content-Length", Integer.toString(tokenParameters.length()));
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Language", "en_US");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            // Gets the response from PayPal
            DataOutputStream response = new DataOutputStream(conn.getOutputStream());
            response.write(tokenParameters.getBytes());
            response.close();
            // Reads the response from PayPal
            BufferedReader responseData = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String jsonString = responseData.readLine();
            responseData.close();

            // Maps JSON to a HashMap
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> jsonMap;
            jsonMap = mapper.readValue(jsonString, HashMap.class);

            accessToken = "Bearer " + jsonMap.get("access_token").toString();

        }

        catch (IOException ioException) {
            logger.error(ioException.getMessage());
            logger.error(ioException.getStackTrace());
        }

    }

    /**
     * Gets plan.
     *
     * @return the plan
     */
    public Plan getPlan()  {
        // Get subscription information
        /**
         * .path("{userName}")
         * .resolveTemplate("userName", "janedoe")
         * .queryParam("chapter", "1")
         * // http://example.com/webapi/read/janedoe?chapter=1
         * Response response = myResource.request(...)        .get();
         */
        Plan plan = new Plan();

        this.setAccessToken();



        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(properties.getProperty("paypalPlansUrl")).path("{plan}").resolveTemplate("plan", properties.getProperty("paypalPlan"));

            String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, this.getAccessToken()).get(String.class);

            ObjectMapper mapper = new ObjectMapper();

            plan = mapper.readValue(response, Plan.class);
        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            logger.error(jsonException.getStackTrace());
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }


        return plan;
    }

    /**
     * Gets subscription.
     *
     * @param user the user
     * @return the subscription
     */
    public Subscribe getSubscription(User user) {

        Subscribe subscription = null;

        try {

            properties = this.loadProperties("/adhound.properties");

            Plan plan = this.getPlan();

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(properties.getProperty("paypalSubscriptionsUrl"));

            String subscriber = "{" +
                    " \"plan_id\": \"" + plan.getId() + "\"," +
                    "\"subscriber\": {" +
                        "\"name\": {" +
                            "\"given_name\": \"" + user.getFirstName() + "\"," +
                            "\"surname\": \"" + user.getLastName() + "\"" +
                        "}," +
                        "\"email_address\": \"" + user.getEmail() + "\"," +
                        "\"shipping_address\": {" +
                            "\"name\": {" +
                                "\"full_name\": \"" + user.getFirstName() + " " + user.getLastName() + "\"" +
                            "}," +
                            "\"address\": {" +
                                "\"address_line_1\": \"" + user.getAddress() + "\"," +
                                "\"address_line_2\": \"\"," +
                                "\"admin_area_2\": \"" + user.getCity() + "\"," +
                                "\"admin_area_1\": \"" + user.getState().getAbbreviation() + "\"," +
                                "\"postal_code\": \"" + user.getZipcode() + "\"," +
                                "\"country_code\": \"US\"" +
                            "}" +
                        "}" +
                    "}," +

                    "\"application_context\": {" +
                        "\"shipping_preference\": \"SET_PROVIDED_ADDRESS\"," +
                        "\"return_url\": \"" + properties.getProperty("returnUrl").toString() + "\"," +
                        "\"cancel_url\": \"" + properties.getProperty("cancelUrl").toString() + "\"" +
                    "}" +

                "}";

                /*
                * URLs must be a valid domain name or IP address URL with ports didn't work
                */

            String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, this.getAccessToken()).post(Entity.json(subscriber), String.class);

            ObjectMapper mapResponse = new ObjectMapper();

            subscription = mapResponse.readValue(response, Subscribe.class);

        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            logger.error(jsonException.getStackTrace());
        }
        catch (IOException ioException ) {
            logger.error(ioException.getMessage());
            logger.error(ioException.getStackTrace());
        }

        return subscription;
    }

}
