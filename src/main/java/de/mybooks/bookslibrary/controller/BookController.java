package de.mybooks.bookslibrary.controller;


import de.mybooks.bookslibrary.model.Book;
import de.mybooks.bookslibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping("/books")
    public List<Book> getBooks(){
        return service.getAllBooks();
    }


    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id){
        return service.getBookByID(id);
    }


    @PutMapping("/books/{id}")
    public List<Book> update(@PathVariable String id,@RequestBody Book newBook){
        newBook.setIsbn(id);
        service.updateBook(id,newBook);
        return service.getAllBooks();
    }



    @DeleteMapping("/books/{id}")
    public List<Book> deletBook(@PathVariable String id){
        service.deleteBook(id);
        return service.getAllBooks();
    }


}
