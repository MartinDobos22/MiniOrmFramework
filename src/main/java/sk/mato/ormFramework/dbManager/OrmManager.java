package sk.mato.ormFramework.dbManager;

import sk.mato.ormFramework.Exception.AnnotationNotFoundException;
import sk.mato.ormFramework.Exception.MissingColumnAnnotationException;
import sk.mato.ormFramework.reflection.ObjectReflector;
import sk.mato.ormFramework.sql.SqlBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrmManager {

    public <T> T getEntityById(Long id, Class<T> clazz) throws Exception {

        T obj = null;

        if(id == null){
            throw new IllegalArgumentException("ID cannot be empty!!");
        }

        if(!ObjectReflector.isTable(clazz)){
            throw new AnnotationNotFoundException("Object doesn't have a annotation Table");
        }else{
            System.out.println("is table: " + clazz.getName());
        }

        //load data from db
        ResultSet resultSet = loadData(id,clazz);


        //sets data to object

        obj = ObjectReflector.getFilledData(resultSet, clazz);

        return obj;
    }

    private <T> ResultSet loadData(Long id, Class<T> clazz) throws Exception {
        String tableName = ObjectReflector.getTableName(clazz);
        List<String> columnNames = ObjectReflector.getColumnName(clazz);
        String idColumnNames = ObjectReflector.getIdColumnName(clazz);

        String query = SqlBuilder.buildQuery(id, tableName,idColumnNames, columnNames);
        System.out.println(query);

        DatabaseAccess databaseAccess = null;
        ResultSet resultSet = null;
        try{
            databaseAccess = new DatabaseAccess();
            resultSet = databaseAccess.executeQuery(query);
            databaseAccess.commit();
        }catch(Exception e){
            if(databaseAccess != null){
                databaseAccess.rollback();
            }
            throw new Exception(e);
        }finally {
            if(databaseAccess != null){
                databaseAccess.disconnect();
            }
        }
        return databaseAccess.executeQuery(query);
    }

    public <T> void insertEntity(T object){
        Class<?> clazz = object.getClass();
        String tableName = ObjectReflector.getTableName(clazz);
        List<String> tableColumns = ObjectReflector.getColumnName(clazz);
    }

}
