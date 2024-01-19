package com.example.temp.DB_HANDLER;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    public static <T> List<T> convertResultSetToList(ResultSet resultSet, Class<T> clazz) {
        List<T> resultList = new ArrayList<>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                T object = clazz.getDeclaredConstructor().newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);

                    String setterMethodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    clazz.getMethod(setterMethodName, columnValue.getClass()).invoke(object, columnValue);
                }

                resultList.add(object);
            }

        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet in a finally block to ensure closure even if an exception occurs
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }
}
