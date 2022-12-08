package de.mybooks.bookslibrary.reop;

import de.mybooks.bookslibrary.model.Art;
import de.mybooks.bookslibrary.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class BookReop {

    List<Book> books = new ArrayList<>(List.of(
            new Book("8123","Dogs","Muslim", Art.E_BOOK),
            new Book("887","Cats","Muslim", Art.HARD_COVER),
            new Book("98998","Hors","Muslim", Art.SOFT_COVER)

    ));

    public Book getBookByID(String id){
        for (Book book :
                books) {
            if (book.getIsbn().equals(id)) {
                return book;
            }
        }
        throw new NoSuchElementException("Book is Not Found");
    }

    public List<Book>  insertBook(Book theBook){
        books.add(theBook);
        return books;
    }

    public List<Book>  deleteBook(String id){
        books.remove(getBookByID(id));
        return books;
    }

    public List<Book> updateBook(String id ,Book newBook){
        Book theBook = getBookByID(id);
        newBook.setIsbn(id);
        int idx = books.indexOf(theBook);

        books.set(idx,newBook);

        return books;
    }

}
