package com.adhound.test.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Database.
 */
public class Database {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    /**
     * Private constructor prevents instantiating this class anywhere else, and calls the loadProperties method.
     */
    public Database() {
        loadProperties();
    }

    /**
     * Runs a SQL file to create the AdHound database.
     *
     * @param sqlFile the sql file
     */

    public void createDatabase(String sqlFile) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String sql =  String.valueOf(classLoader.getResource(sqlFile));


        try {

            Runtime rt = Runtime.getRuntime();
            String executeSqlCommand = "sudo mysql -u root adhound < " + sql;
            Process pr = rt.exec(executeSqlCommand);
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);
            /*
            this.setConnection();

            ScriptRunner scriptRunner = new ScriptRunner(this.getConnection());

            Reader reader = new BufferedReader(new FileReader(sql));

            scriptRunner.runScript(reader);
            */
        } catch (FileNotFoundException e) {

            System.out.println("Database.createDatabase() : Cannot load the SQL file.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Instantiates an object of the Properties class, and loads the database properties file.
     */
    private void loadProperties() {

        System.out.println("Properties File");

        logger.info("Properties File Loaded");

        properties = new Properties();

        try {

            properties.load(this.getClass().getResourceAsStream("/database.properties"));

        } catch (IOException e) {

            logger.info("Properties File Did Not Load: ", e);

            System.out.println("Database.loadProperties() : Cannot load the properties file.");

        }

    }

    /**
     * Gets the only Database object available
     *
     * @return Database object
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets the Connection object to the database
     *
     * @return Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Makes a connection to the database using properties.
     *
     * @throws SQLException       the sql exception
     * @throws ClassCastException the class cast exception
     */
    public void setConnection() throws SQLException, ClassCastException {

        if (connection != null) { return; }

        try {

            Class.forName(properties.getProperty("driver"));

        } catch (ClassNotFoundException e) {

            throw new ClassCastException("Database.setConnection() : MySQL Driver not found");

        }

        String url = properties.getProperty("url");

        connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));

    }

    /**
     * Disconnects the connection to the database.
     */
    public void disconnect() {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException e) {

                System.out.println("Cannot close connection" + e);

            }

        }

        connection = null;

    }
/*
    public void runSQL(String sqlFile) {



        Statement statement = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sqlFile);

        //// InputStream inputStream = getClass().getClassLoader().getResourceAsStream(sqlFile);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            this.setConnection();

            Class.forName("com.mysql.cj.jdbc.Driver");
            statement = getConnection().createStatement();

            while (reader.ready()) {

                String sql = reader.readLine();
                if ((sql != null) && (!sql.equals("")) && (sql.matches("^[a-zA-Z]*$"))) {
                    logger.info(sql);
                    statement.executeUpdate(sql);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }
*/
}
