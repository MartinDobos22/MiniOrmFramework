package sk.mato.databaseConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader {

    public static final String ORACLE_HOST = "oracledb.host";
    public static final String ORACLE_PORT = "oracledb.port";
    public static final String ORACLE_DB_NAME = "oracledb.dbname";
    public static final String ORACLE_USER = "oracledb.user";
    public static final String ORACLE_PASSWORD = "oracledb.password";

    public String getProperty(String property) {

        String filename = "application.properties";
        String propertyValue = "";
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(filename);
            if(inputStream == null){
              //  LOG.error("sorry, unable to find " + filename);
                return "Sorry, unable to find " + filename;
            }
            properties.load(inputStream);
            propertyValue = properties.getProperty(property);
            if(propertyValue != null && !propertyValue.isEmpty()){
                return propertyValue;
            }else {
             //   LOG.error("property '"+property+"' not specified in config.properties file");
                return null;
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // LOG.error("return empty property value without taking from " + filename);
        return propertyValue;
    }
}
