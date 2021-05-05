package sk.mato.ormFramework.sql;

import java.util.List;

public class SqlBuilder {


    public static String buildQuery(Long id, String tableName, String idColumnNames, List<String> columnNames) {

        StringBuilder column = new StringBuilder();
        for(String s : columnNames){
            column.append(s).append(",");
        }
         String columnQuery = column.toString().substring(0,column.lastIndexOf(","));

        return String.format("SELECT %s FROM %s WHERE %s = %d", columnQuery, tableName, idColumnNames, id);
    }
}
