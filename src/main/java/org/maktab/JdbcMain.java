package org.maktab;

import java.sql.*;

public class JdbcMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // Registering postgresql jdbc driver
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5437/postgres", "postgres", "postgres");
        /*Statement statement = connection.createStatement();

        String sqlString = "INSERT INTO person (national_code, first_name, last_name, birthday, phone_number, age) " +
                "values ('4900000001', 'milad1', 'amery1', '1990-01-02', 09350000001, 29)";

        statement.execute(sqlString);*/

        /*String sqlString = "INSERT INTO person (nationalcode, first_name, last_name, birthday, phone_number, age) " +
                "values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setString(1, "4900000001");
        preparedStatement.setString(2, "milad1");
        preparedStatement.setString(3, "amery1");
        preparedStatement.setDate(4, new Date(1990, 1, 2));
        preparedStatement.setLong(5, Long.valueOf("09350000001"));
        preparedStatement.setInt(6, 29);
        preparedStatement.executeUpdate();*/

        System.out.println("Record Inserted successfully ..... ");

        String selectSql = "SELECT * FROM person";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Person p = new Person(
                    result.getString("nationalcode"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getDate("birthday"),
                    result.getLong("phone_number"),
                    result.getInt("age")
            );
            System.out.println(p);
        }

        connection.close();
    }
}
