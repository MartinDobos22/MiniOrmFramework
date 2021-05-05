package sk.mato.databaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaDao extends Dao {

    OsobaDao() throws SQLException {
        super();
    }

   public long create(Osoba osoba) throws Exception{
        long id = -1L;
        PreparedStatement preparedStatement = null;
        try {
            String[] generatedId = {"ID"};
            preparedStatement = getConnection().prepareStatement("INSERT INTO Osoba (MENO, VEK, DATUM_NARODENIA) VALUES (?,?,?);");
            preparedStatement.setString(1, osoba.getMeno());
            preparedStatement.setInt(2, osoba.getVek());
            preparedStatement.setDate(3, (Date) osoba.getDatumNarodenia());
            preparedStatement.execute();

            try(ResultSet generatedColumn = preparedStatement.getGeneratedKeys()) {
                if(generatedColumn.next()){
                    id = generatedColumn.getLong(1);
                }else {
                    throw new SQLException("Vytvorenie osoby sa nepodarilo. Nevrátilo sa žiadne ID");
                }
            }

        }catch(Exception e){
            throw new Exception(e);
        }finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                System.err.println("....");
            }
       }
        return id;
   }

    public static void main(String[] args) {
        OsobaDao dao = null;

        try {
            dao = new OsobaDao();
            long id = dao.create(null);
            dao.commit();
        }catch(Exception e){
            if(dao != null) {
                dao.rollback();
            }
        }finally{
            if(dao != null){
                dao.disconnect();
            }
        }
    }

}
