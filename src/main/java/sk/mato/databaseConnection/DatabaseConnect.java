package sk.mato.databaseConnection;

import java.sql.*;

public class DatabaseConnect {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }

        String user = "mato";
        String password = "heslo";
        String host = "localhost";
        String port = "3306";
        String dbName = "databaseName";
        Connection connection = null;
        //natvrdo napísaný príkaz bez otazníkov
        Statement statement = null;
        PreparedStatement prepStatement = null;

        try {
             connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseName?characterEncoding=UTF-8", user, password);

            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM osoba");

            prepStatement = connection.prepareStatement("INSERT INTO Osoba(Meno, Vek, DatumNarodenia) VALUES (?,?,?);");
            prepStatement.setString(1, "Mato");
            prepStatement.setInt(2, 22);
            prepStatement.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            prepStatement.executeQuery();

            while(resultSet.next()){
                String name = resultSet.getString(1);
                int vek = resultSet.getInt("AGE");
            }

            connection.commit();

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
