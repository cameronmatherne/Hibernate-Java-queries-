package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private EntityManagerFactory emf;
    private Configuration con;

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        con = new Configuration().configure("hibernate.cfg.xml");
        setUp();

        Scanner input = new Scanner(System.in);
        System.out.println("Which method you like to demonstrate? Enter the corresponding number.");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("(1): Search publisher name by first letter");
        System.out.println("(2): Search publisher by full or partial name");
        System.out.println("(3): Expanded search by all or partial book title");
        System.out.println("(4): Complete search by all or partial book title");
        System.out.println();
        Integer line = input.nextInt();

        input.nextLine();
        switch (line) {
            case 1:
                System.out.println("Enter a letter");
                String letter = input.nextLine();
                SearchPublisherByFirstLetter(letter);
                break;
            case 2:
                System.out.println("Enter letter(s)");
                String letters = input.nextLine();
                SearchByPartialName(letters);
                break;
            case 3:
                System.out.println("Enter any title letters");
                String title = input.nextLine();
                ExpandedSearchPartial(title);
                break;

            case 4:
                System.out.println("Enter any title letters");
                String titles = input.nextLine();
                ExpandedSearchComplete(titles);
                break;

        }
        tearDown();
    }

    protected void setUp() throws Exception {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            emf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    protected void tearDown() throws Exception {
        if (emf != null) {
            emf.close();
        }
    }

    // When the user enters a letter, find and display the names all
    // authors where the first letter of the author’s name matches
    // the letter the user entered.
    public void SearchPublisherByFirstLetter(String letter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String upperCaseLetter = letter.toUpperCase();
        List<String> names = em.createQuery("" +
                "select a.name " +
                "from PublishersEntity a " +
                "where SUBSTRING(cast(a.name as string), 1, 1)  = '" + upperCaseLetter + "'")
                .getResultList();

        System.out.println("Names of authors starting with: " + upperCaseLetter);
        for (var name : names) {
            System.out.println(name);
        }
        em.getTransaction().commit();
    }

    //When the user enters all or a portion of an author’s name, find
    // and display the names of all authors where the author’s name
    // contains a match for the data entered by the user.


    public void SearchByPartialName(String partialName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<String> names = em.createQuery(" " +
                "select a.name " +
                "from PublishersEntity a " +
                "where cast(a.name as string) like '%" + partialName + "%'"
        ).getResultList();

        System.out.println("Names of authors containing: " + partialName);
        for (var name : names) {
            System.out.println(name);
        }
        em.getTransaction().commit();
    }


    public void ExpandedSearchPartial(String partialTitle) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Object[] > results = em.createQuery(
                "select t.title, a.author " +
                        "from TitlesEntity t, TitleauthorsEntity ta, AuthorsEntity a " +
                        "where cast(t.title as string) like '%" + partialTitle + "%' " +
                        "and a.auId = ta.auId and trim(cast(ta.isbn as string)) = trim(cast(t.isbn as string)) "
        ).getResultList();

        System.out.println("Search results: ");
        results.forEach((result) -> {
            System.out.println("Title: " + result[0] + ", Author: " + result[1]);
        });
        em.getTransaction().commit();
    }


    // When the user enters all or a portion of a book title, list matching
    // book titles with information that includes the author, publisher and
    // publication date.
    public void ExpandedSearchComplete(String partialTitle) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Object[]> results = em.createQuery(
                "select t.title, a.author, p.name, t.yearPublished " +
                        "from TitlesEntity t, TitleauthorsEntity ta, AuthorsEntity a, PublishersEntity p " +
                        "where cast(t.title as string) like '%" + partialTitle + "%' " +
                        "and a.auId = ta.auId and trim(cast(ta.isbn as string)) = trim(cast(t.isbn as string)) " +
                        "and t.pubid = p.pubid"
        ).getResultList();

        System.out.println("Search results: ");
        results.forEach((result) -> {
            System.out.println("Title: " + result[0] + ", Author: " + result[1] + ", Publisher: " + result[2] + ", Publication Date: " + result[3]);
        });
        em.getTransaction().commit();
    }

}