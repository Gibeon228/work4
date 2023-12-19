package org.example.hw;
/**
 * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgres, mysql, sqllite, h2, ...)
 * 2. С помощью JPA(Hibernate) выполнить:
 * 2.1 Описать сущность Book из пункта 1.1
 * 2.2 Создать Session и сохранить в таблицу 10 книг
 * 2.3 Выгрузить список книг какого-то автора
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Jpa {

    public static void main(String[] args) throws SQLException {

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book book = new Book();
            book.setName("book1");
            book.setAuthor("author1");

            Book book1 = new Book();
            book1.setName("book2");
            book1.setAuthor("author2");

            Book book2 = new Book();
            book2.setName("book3");
            book2.setAuthor("author7");

            Book book3 = new Book();
            book1.setName("book4");
            book1.setAuthor("author4");

            Book book4 = new Book();
            book1.setName("book5");
            book1.setAuthor("author5");

            Book book5 = new Book();
            book1.setName("book6");
            book1.setAuthor("author6");

            Book book6 = new Book();
            book1.setName("book7");
            book1.setAuthor("author7");

            Book book7 = new Book();
            book1.setName("book8");
            book1.setAuthor("author8");

            Book book8 = new Book();
            book1.setName("book9");
            book1.setAuthor("author9");

            Book book9 = new Book();
            book1.setName("book10");
            book1.setAuthor("author7");

            session.persist(book);
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            session.persist(book4);
            session.persist(book5);
            session.persist(book6);
            session.persist(book7);
            session.persist(book8);
            session.persist(book9);
            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery("select b from Book b where author = 'author7' order by id desc", Book.class)
                    .getResultList();

            System.out.println(books);
        }

        sessionFactory.close();
    }

}
