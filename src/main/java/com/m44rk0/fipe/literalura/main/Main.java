package com.m44rk0.fipe.literalura.main;

import com.m44rk0.fipe.literalura.model.Author;
import com.m44rk0.fipe.literalura.model.Book;
import com.m44rk0.fipe.literalura.model.BookData;
import com.m44rk0.fipe.literalura.model.Language;
import com.m44rk0.fipe.literalura.repository.BookRepository;
import com.m44rk0.fipe.literalura.service.APIConsumer;
import com.m44rk0.fipe.literalura.service.DataConverter;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private final String API_URL = "https://gutendex.com/books/?search=";

    private final Scanner sc = new Scanner(System.in);
    private final APIConsumer APIConsumer = new APIConsumer();
    private final DataConverter dataConverter = new DataConverter();
    private final BookRepository bookRepository;

    public Main(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void showMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """
                    *** --API-- Consulta de Livros ***
                                        
                    1- Buscar livros por título
                    2- Listar livros registrados
                    3- Listar autores resgistrados
                    4- Listar autores por um ano escolhido
                    5- Listar livros em dada linguagem
                                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsInYear();
                    break;
                case 5:
                    listBooksInCertainLanguage();
                    break;
                case 0:
                    System.out.println("Saindo...!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private void searchBooksByTitle() {
        System.out.print("Digite o título de um livro: ");
        var bookName = sc.nextLine();
        String searchUrl = API_URL.concat(bookName.replace(" ", "+").toLowerCase().trim());

        String json = APIConsumer.getData(searchUrl);
        String jsonBook = dataConverter.extractObjectFromJson(json);

        List<BookData> booksDTO = dataConverter.getList(jsonBook, BookData.class);

        if (!booksDTO.isEmpty()) {
            BookData bookData = booksDTO.getFirst();
            Book book = new Book(bookData);

            // Verifique se o livro já existe no repositório
            Optional<Book> existingBook = bookRepository.findByName(book.getName());
            if (existingBook.isPresent()) {
                System.out.println(existingBook.get());
            } else {
                Author author = bookRepository.findAuthorByName(book.getAuthor().getName());
                if (author != null) {
                    book.setAuthor(null);
                    bookRepository.save(book);
                    book.setAuthor(author);
                }
                book = bookRepository.save(book);
                System.out.println(book);
            }
        } else {
            System.out.println("Livro não encontrado!");
        }
    }


    private void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void listRegisteredAuthors() {
        List<Author> authors = bookRepository.searchAuthors();
        authors.forEach(System.out::println);
    }

    private void listLivingAuthorsInYear() {
        try {
            System.out.println("Digite um ano: ");
            var year = sc.nextInt();
            sc.nextLine();

            List<Author> authors = bookRepository.searchLivingAuthors(year);
            authors.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Entrada Inválida");
            sc.nextLine();
        }
    }

    private void listBooksInCertainLanguage() {
        System.out.println("Digite a Linguagem: ");
        var lang = Language.convert(sc.nextLine());
        List<Book> books = bookRepository.findBooksByLanguages(lang);
        if (!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("Não há livros na linguagem dada");
        }
    }


}
