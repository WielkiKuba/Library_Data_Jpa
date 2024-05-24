package com.Library.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> bookList(){
        return bookRepository.findAll();
    }
    public Optional<Book> bookList(long bookId){
        return bookRepository.findById(bookId);
    }
    public List<Book> bookListByName(String name){
        return bookRepository.findByName(name);
    }
    public List<Book> bookListByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    public List<Book> bookListByFree(int free){
        return bookRepository.findByFree(free);
    }
    public void bookSave(Book book){
        bookRepository.save(book);
    }
    public void bookSave(String name,String author,String year,String publisher){
        Book book = new Book();
        book.setName(name);
        book.setFree(0);
        book.setAuthor(author);
        book.setYear(year);
        book.setPublisher(publisher);
        bookSave(book);
    }
}
