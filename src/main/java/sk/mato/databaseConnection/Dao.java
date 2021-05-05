package sk.mato.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {
    private Connection connection = null;
    private PropertiesReader propertiesReader = new PropertiesReader();

    Dao() throws SQLException{
        String user = propertiesReader.getProperty(PropertiesReader.ORACLE_USER);
        String password = propertiesReader.getProperty(PropertiesReader.ORACLE_PASSWORD);
        connect(user, password);

    }

    private void connect (String user, String password) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = DriverManager.getConnection(getConnectionUrl(), user, password);

        if(connection != null){
            connection.setAutoCommit(false);
        }else{
            throw new SQLException("Connection is null");
        }
    }

    private String getConnectionUrl() {
        String host = propertiesReader.getProperty(PropertiesReader.ORACLE_HOST);
        String port = propertiesReader.getProperty(PropertiesReader.ORACLE_PORT);
        String dbname = propertiesReader.getProperty(PropertiesReader.ORACLE_DB_NAME);

        return String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, dbname);
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
          //  LOG.error("Cannot close connection. ",e);
        }
    }

    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            //LOG.error("Cannot close connection. ",e);
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            //LOG.error("Cannot close connection. ",e);
        }
    }

    public Connection getConnection() {

        return connection;
    }

}
