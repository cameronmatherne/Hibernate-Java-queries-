package org.example;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TITLEAUTHORS", schema = "PUBLIC", catalog = "BOOKSDB")
public class TitleauthorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "ISBN")
    private Object isbn;
    @Basic
    @Column(name = "AU_ID")
    private int auId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getIsbn() {
        return isbn;
    }

    public void setIsbn(Object isbn) {
        this.isbn = isbn;
    }

    public int getAuId() {
        return auId;
    }

    public void setAuId(int auId) {
        this.auId = auId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleauthorsEntity that = (TitleauthorsEntity) o;
        return id == that.id && auId == that.auId && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, auId);
    }
}
