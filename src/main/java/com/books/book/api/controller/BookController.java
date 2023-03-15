package com.books.book.api.controller;

import com.books.book.api.model.Book;
import com.books.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/library/search")
    public Book getBook(@RequestParam String title){
        Optional<Book> book=bookService.getBook(title);
        if (book.isPresent()){
            return (Book) book.get();
        }
        else return null;
    }

    @GetMapping("/library/add")
    public String addBook(@RequestParam String title, String author){
        bookService.addBook(title, author);
        return "Book added: Title:"+title+" Author:"+author;
    }

    @GetMapping("/library/books")
    public List<Book> listAllBooks(){
        return bookService.listAllBooks();
    }

    @GetMapping("/library/update")
    public String updateAuthor(@RequestParam String title, String newAuthor){
        bookService.updateAuthor(title,newAuthor);
        return "Book author updated: Title:"+title+" Author:"+newAuthor;
    }

    @GetMapping("/library/delete")
    public String deleteBook(@RequestParam String title){
        if (bookService.deleteBook(title))
            return "Book deleted: Title:"+title;
        else return "Book does not exist";
    }
}
