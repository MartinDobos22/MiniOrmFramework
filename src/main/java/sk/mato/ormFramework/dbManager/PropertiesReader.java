package sk.mato.ormFramework.dbManager;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static final String DB_URL = "db.url";
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";

    public String getProperty(String property) throws Exception{
        String fileName = "orm.properties";
        Properties properties = new Properties();
        InputStream input = null;
        input = getClass().getClassLoader().getResourceAsStream(fileName);
        if(input == null){
            throw new Exception("Could not find orm.properties file");
        }
        properties.load(input);

        String propertyValue = properties.getProperty(property);
        if(propertyValue != null && !propertyValue.isEmpty()){
            return propertyValue;
        }else {
            throw new Exception ("Property  '"+property+"' not specified in orm.properties file");
        }
    }
}
