package com.books.book.service;

import com.books.book.api.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private List<Book> bookList;

    public BookService(List<Book> booksList) {
        this.bookList = new ArrayList<>();

        Book book1=new Book("titlu1", "autor1");
        Book book2=new Book("titlu2", "autor2");
        Book book3=new Book("titlu3", "autor3");
        Book book4=new Book("titlu4", "autor4");
        Book book5=new Book("titlu5", "autor5");

        bookList.addAll(Arrays.asList(book1,book2,book3,book4,book5));
    }

    public Optional<Book> getBook(String title) {
        Optional optional=Optional.empty();
        for (Book book:bookList){
            if (Objects.equals(title, book.getTitle())){
                optional=Optional.of(book);
                return optional;
            }
        }
        return optional;
    }

    public void addBook(String title, String author){
        Book book=new Book(title, author);
        bookList.add(book);
    }

    public void updateAuthor(String title, String newAuthor){
        Book book1=new Book("","");
        for (Book book:bookList){
            if (Objects.equals(title, book.getTitle())){
                book1=book;
            }
        }
        bookList.remove(book1);
        book1.setAuthor(newAuthor);
        bookList.add(book1);
    }

    public boolean deleteBook(String title){
        Book book1=new Book(title, "");
        for (Book book:bookList){
            if (Objects.equals(book1.getTitle(), book.getTitle())){
                return bookList.remove(book);
            }
        }
        return false;
    }

    public List<Book> listAllBooks(){
        List<Book> bookList1=bookList;
        bookList1.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle));
        return bookList1;
    }
}
