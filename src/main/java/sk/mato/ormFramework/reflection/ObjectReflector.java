package sk.mato.ormFramework.reflection;

import sk.mato.ormFramework.Exception.MissingColumnAnnotationException;
import sk.mato.ormFramework.Exception.MissingIdException;
import sk.mato.ormFramework.anotacie.Column;
import sk.mato.ormFramework.anotacie.Id;
import sk.mato.ormFramework.anotacie.Table;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObjectReflector {
    public static <T> boolean isTable(Class<T> clazz) {
      return clazz.isAnnotationPresent(Table.class);
    }

    public static <T> String getTableName(Class<T> clazz) {
        return clazz.getAnnotation(Table.class).value();
    }

    public static <T> List<String> getColumnName(Class<T> clazz) {
        List<String> columns = new ArrayList<>();
        for(Field f : clazz.getDeclaredFields()){
            if(f.isAnnotationPresent(Column.class)){
                Column column = f.getAnnotation(Column.class);
                columns.add(column.value());
                System.out.println("Column: " + column.value());
            }
        }
        return columns;
    }

    public static <T> String getIdColumnName(Class<T> clazz) throws MissingColumnAnnotationException {
        String idColumnName = null;
        for(Field f : clazz.getDeclaredFields()){
            if(f.isAnnotationPresent(Id.class)){
                if(f.isAnnotationPresent(Column.class)){
                    idColumnName = f.getAnnotation(Column.class).value();
                } else {
                    throw new MissingColumnAnnotationException("When searching for ID, no column with column annotation was found");
                }
            }
        }
        if(idColumnName == null){
            throw new MissingIdException("Missing ID annotation in entits " + clazz.getName());
        }
        System.out.println("Column with ID: " + idColumnName);
        return idColumnName;
    }

    public static <T> T getFilledData(ResultSet resultSet, Class<T> clazz) throws Exception {

        T object = null;

        object = clazz.getDeclaredConstructor().newInstance();
        for(Field f : object.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.isAnnotationPresent(Column.class)){
                String elementType = f.getType().getName();
                String columnName = f.getAnnotation(Column.class).value();
                if(elementType.equals(String.class.getName())){
                    f.set(object, resultSet.getString(columnName));
                }else if(elementType.equals(Long.class.getName())){
                    f.set(object, resultSet.getLong(columnName));
                }else if(elementType.equals(Integer.class.getName())){
                    f.set(object, resultSet.getInt(columnName));
                }
            }
        }

        return object;
    }
}
