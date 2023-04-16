package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    private EntityManagerFactory emf;
    private Configuration con;

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        con = new Configuration().configure("hibernate.cfg.xml");

        setUp();
        //searchPublisherByFirstLetter("J");
        SearchByPartialName("O");
        //ExpandedSearchPartial("D");
        // ExpandedSearchComplete();

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
    public void searchPublisherByFirstLetter(String letter) {
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

        List<Object[]> results = em.createQuery("" +
                        "select a.author, t.title " +
                        "from TitlesEntity t, TitleauthorsEntity ta, AuthorsEntity a " +
                        "where cast(t.title as string) like '%" + partialTitle + "%'" +
                        "and a.auId = ta.auId and t.isbn = ta.isbn "
        ).getResultList();

        results.forEach((result) -> {
            System.out.println(result[0] + "----" + result[1]);
        });

        em.getTransaction().commit();
    }








    // When the user enters all or a portion of a book title, list matching
    // book titles with information that includes the author, publisher and
    // publication date.
    public void ExpandedSearchComplete() {

    }




    /*
        public void showStudents() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<String> students = em.createQuery(
                "select s.name from StudentEntity s"
        ).getResultList();

        students.forEach(System.out::println);

        em.getTransaction().commit();
    }

    public void showStudentsSchedules() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Object[]> schedules = em.createQuery("" +
                "select s.name, c.coursedesc " +
                "from StudentEntity s, CourseEntity c, EnrolledEntity e " +
                "where s.id=e.id and c.courseid=e.courseid").getResultList();

        schedules.forEach((record) -> {
            System.out.println(record[0] + ", " + record[1]);
        });

        em.getTransaction().commit();
    }

    public void showStudentSchedule(String name) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<String> schedule = em.createQuery(
                "select c.coursedesc " +
                        "from StudentEntity s, CourseEntity c, EnrolledEntity e " +
                        "where s.name='" + name + "' and s.id=e.id and c.courseid=e.courseid"
        ).getResultList();

        System.out.println("Schedule of " + name);
        for (var course : schedule) {
            System.out.println(course);
        }

        em.getTransaction().commit();
    }

     */
}