package ru.Robar3;


import java.sql.*;

public class SQlHomework {
    Connection connection;

    public static void main(String[] args) {
        SQlHomework sQlHomework = new SQlHomework();
        if (sQlHomework.open()) {
            sQlHomework.select();
            sQlHomework.end();
        }
    }

    public boolean open() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/people.db");
            System.out.println("connection");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void select() {
        try {
            Statement statement = connection.createStatement();
            String query =
                    "SELECT * FROM students ";
            ResultSet resultSet = statement.executeQuery(query);
            int column = resultSet.getMetaData().getColumnCount();
            String tableName = resultSet.getMetaData().getTableName(column);
            System.out.println("| " + tableName + " |");
            for (int a = 1; a <= column-1; a++) {
                System.out.print("|" + resultSet.getMetaData().getColumnName(a) + "\t");
            }
            System.out.printf("%8s",("|"+resultSet.getMetaData().getColumnName(3)));
            System.out.println();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.printf("|%d \t|%-7s \t|%d\n",id,name,age);
            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void end() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
