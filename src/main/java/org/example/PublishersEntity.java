package org.example;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PUBLISHERS", schema = "PUBLIC", catalog = "BOOKSDB")
public class PublishersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "PUBID")
    private int pubid;
    @Basic
    @Column(name = "NAME")
    private Object name;
    @Basic
    @Column(name = "COMPANY_NAME")
    private Object companyName;
    @Basic
    @Column(name = "ADDRESS")
    private Object address;
    @Basic
    @Column(name = "CITY")
    private Object city;
    @Basic
    @Column(name = "STATE")
    private Object state;
    @Basic
    @Column(name = "ZIP")
    private Object zip;
    @Basic
    @Column(name = "TELEPHONE")
    private Object telephone;
    @Basic
    @Column(name = "FAX")
    private Object fax;
    @Basic
    @Column(name = "COMMENTS")
    private Object comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPubid() {
        return pubid;
    }

    public void setPubid(int pubid) {
        this.pubid = pubid;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Object companyName) {
        this.companyName = companyName;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getZip() {
        return zip;
    }

    public void setZip(Object zip) {
        this.zip = zip;
    }

    public Object getTelephone() {
        return telephone;
    }

    public void setTelephone(Object telephone) {
        this.telephone = telephone;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
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
        PublishersEntity that = (PublishersEntity) o;
        return id == that.id && pubid == that.pubid && Objects.equals(name, that.name) && Objects.equals(companyName, that.companyName) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zip, that.zip) && Objects.equals(telephone, that.telephone) && Objects.equals(fax, that.fax) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pubid, name, companyName, address, city, state, zip, telephone, fax, comments);
    }
}
