package org.example;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TITLES", schema = "PUBLIC", catalog = "BOOKSDB")
public class TitlesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "TITLE")
    private Object title;
    @Basic
    @Column(name = "YEAR_PUBLISHED")
    private int yearPublished;
    @Basic
    @Column(name = "ISBN")
    private Object isbn;
    @Basic
    @Column(name = "PUBID")
    private int pubid;
    @Basic
    @Column(name = "DESCRIPTION")
    private Object description;
    @Basic
    @Column(name = "NOTES")
    private Object notes;
    @Basic
    @Column(name = "SUBJECT")
    private Object subject;
    @Basic
    @Column(name = "COMMENTS")
    private Object comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Object getIsbn() {
        return isbn;
    }

    public void setIsbn(Object isbn) {
        this.isbn = isbn;
    }

    public int getPubid() {
        return pubid;
    }

    public void setPubid(int pubid) {
        this.pubid = pubid;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitlesEntity that = (TitlesEntity) o;
        return id == that.id && yearPublished == that.yearPublished && pubid == that.pubid && Objects.equals(title, that.title) && Objects.equals(isbn, that.isbn) && Objects.equals(description, that.description) && Objects.equals(notes, that.notes) && Objects.equals(subject, that.subject) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, yearPublished, isbn, pubid, description, notes, subject, comments);
    }
}
