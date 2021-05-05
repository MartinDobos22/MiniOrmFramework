package sk.mato.ormFramework.dbManager;

import java.sql.*;

public class DatabaseAccess {

    private Connection connection;
    private PropertiesReader propertiesReader = new PropertiesReader();

    DatabaseAccess() throws Exception {
        String user = propertiesReader.getProperty(PropertiesReader.DB_USER);
        String password = propertiesReader.getProperty(PropertiesReader.DB_PASSWORD);
        String driver = propertiesReader.getProperty(PropertiesReader.DB_DRIVER);
        String url = propertiesReader.getProperty(PropertiesReader.DB_URL);
        connect(user, password, driver, url);
    }


    private void connect(String user, String password, String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user,password);

        if(connection != null){
            connection.setAutoCommit(false);
        }else {
            throw new SQLException("Connection is null");
        }
    }


    public void disconnect() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            //  LOG.error("Cannot close connection. ",e);
            throw new Exception("Cannot close connection");
        }
    }

    public void commit() throws Exception {
        try {
            connection.commit();
        } catch (SQLException e) {
            //LOG.error("Cannot close connection. ",e);
            throw new Exception("Cannot commit transaction in connection");
        }
    }

    public void rollback() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //LOG.error("Cannot close connection. ",e);
            throw new Exception("Cannot rollback transaction in connection");
        }
    }

    public Connection getConnection() {

        return connection;
    }



    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.close();
        return resultSet;
    }
}
