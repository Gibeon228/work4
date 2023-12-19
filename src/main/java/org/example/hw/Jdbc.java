package org.example.hw;

import java.sql.*;


/**
 * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgres, mysql, sqllite, h2, ...)
 * 1. С помощью JDBC выполнить:
 * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
 * 1.2 Добавить в таблицу 10 книг
 * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
 */

public class Jdbc {

    public static void main(String[] args) throws SQLException {
        // RDBMS (SQL) Structure Query Language
        // postgres, oracle, mysql, h2, sqllite, ...
        // in-memory h2

        // JDBC Java DataBase Connectivity - набора классов в стандартной библиотеке,
        // которая предназначена для работы с реляционными БД


        // java.sql.Driver
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db");
        prepareTables(connection);
        insertData(connection);
        executeSelect(connection);

//        try (PreparedStatement preparedStatement = connection.prepareStatement("select id, name from users where name = ?")) {
//            preparedStatement.setString(1, "Igor");
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            int counter = 0;
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//
//                System.out.println("[" + counter++ + "] id = " + id + ", name = " + name);
//            }
//        }


        connection.close();
    }

    private static void executeSelect(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, name, author from book where author = 'author7'");
            int counter = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");

                System.out.println("id = " + id + ", name = " + name + ", author = " + author);
            }
        }
    }

    private static void prepareTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table book (
                      id bigint,
                      name varchar(255),
                      author varchar(255)
                    )
                    """);
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    insert into book(id, name, author) 
                    values(1, 'Book1', 'author1'),
                          (2, 'Book #2', 'Author2'),
                          (3, 'Book #3', 'author7'),
                          (4, 'Book #4', 'author4'),
                          (5, 'Book #5', 'author5'),
                          (6, 'Book #6', 'author6'),
                          (7, 'Book #7', 'author7'),
                          (8, 'Book #8', 'author8'),
                          (9, 'Book #9', 'author9'),
                          (10, 'Book #10', 'author10')""");
        }
    }
}
