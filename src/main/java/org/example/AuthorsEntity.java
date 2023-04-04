package org.example;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AUTHORS", schema = "PUBLIC", catalog = "BOOKSDB")
public class AuthorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "AU_ID")
    private int auId;
    @Basic
    @Column(name = "AUTHOR")
    private Object author;
    @Basic
    @Column(name = "YEAR_BORN")
    private Object yearBorn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuId() {
        return auId;
    }

    public void setAuId(int auId) {
        this.auId = auId;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public Object getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(Object yearBorn) {
        this.yearBorn = yearBorn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorsEntity that = (AuthorsEntity) o;
        return id == that.id && auId == that.auId && Objects.equals(author, that.author) && Objects.equals(yearBorn, that.yearBorn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auId, author, yearBorn);
    }
}
