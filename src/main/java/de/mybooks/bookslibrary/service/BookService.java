package de.mybooks.bookslibrary.service;

import de.mybooks.bookslibrary.model.Book;
import de.mybooks.bookslibrary.repo.BookReop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BookService {

    private  final BookReop repo;

    public Book getBookByID(String id){
        for (Book book :
                repo.getBooks()) {
            if (book.getIsbn().equals(id)) {
                return book;
            }
        }
        throw new NoSuchElementException("Book is Not Found");
    }

    public List<Book> insertBook(Book theBook){
        repo.getBooks().add(theBook);
        return repo.getBooks();
    }

    public List<Book>  deleteBook(String id){
        repo.getBooks().remove(getBookByID(id));
        return repo.getBooks();
    }

    public List<Book> getAllBooks(){
        return repo.getBooks();
    }

    public List<Book> updateBook(String id,Book newBook){
        repo.updateBook(id,newBook);
        return repo.getBooks();
    }


}
