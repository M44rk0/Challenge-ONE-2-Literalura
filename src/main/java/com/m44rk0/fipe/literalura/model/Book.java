package com.m44rk0.fipe.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String language;
    private Integer downloadCount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    public Book() {
    }

    public Book(BookData bookData){
        this.name = bookData.bookName();
        this.language = bookData.languages().getFirst();
        this.downloadCount = bookData.downloadCount();
        this.author = new Author(bookData.authors().getFirst());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", downloadCount=" + downloadCount;
    }
}
