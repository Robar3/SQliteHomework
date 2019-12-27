package ru.Robar3;


import java.sql.*;
import java.util.Scanner;

public class SQlHomework {
    Connection connection;
    int a;

    public static void main(String[] args) {
        SQlHomework sQlHomework = new SQlHomework();
        if (sQlHomework.open()) {
            System.out.println("Если нужно внести данные в таблицу введите 1," +
                    "если нужно показать данные введите 2");
            switch (sQlHomework.choice()) {
                case (1):
                    sQlHomework.insert();
                    break;
                case (2):
                    sQlHomework.select();
                    break;
                default:
                    System.out.println("Такой команды не существует");
                    break;
            }
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

    public int choice() {
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        return a;
    }

    public void insert() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name");
            String name = scanner.nextLine();
            System.out.println("Enter age");
            int age = scanner.nextInt();

            String query = "INSERT INTO students (name,age) VALUES ('" + name + "','" + age + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows add");
        } catch (Exception e) {
            e.printStackTrace();
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
            for (int a = 1; a <= column - 1; a++) {
                System.out.print("|" + resultSet.getMetaData().getColumnName(a) + "\t");
            }
            System.out.printf("%8s", ("|" + resultSet.getMetaData().getColumnName(3)));
            System.out.println();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.printf("|%d \t|%-7s \t|%d\n", id, name, age);
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
