package com.m44rk0.fipe.literalura.repository;

import com.m44rk0.fipe.literalura.model.Author;
import com.m44rk0.fipe.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

    @Query("SELECT a FROM Book b JOIN b.author a")
    List<Author> searchAuthors();

    @Query("SELECT a FROM Book b JOIN b.author a WHERE a.birthYear <= :year and a.deathYear >= :year")
    List<Author> searchLivingAuthors(Integer year);

    @Query("SELECT a FROM Book b JOIN b.author a WHERE a.name = :name")
    Author findAuthorByName(String name);

    @Query("SELECT b FROM Book b WHERE b.language LIKE %:language%")
    List<Book> findBooksByLanguages(String language);

}
