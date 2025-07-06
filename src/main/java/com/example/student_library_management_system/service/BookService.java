package com.example.student_library_management_system.service;

import com.example.student_library_management_system.Repository.AuthorRepository;
import com.example.student_library_management_system.Repository.BookRepository;
import com.example.student_library_management_system.Repository.CardRepository;
import com.example.student_library_management_system.converters.BookConverter;
import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CardRepository cardRepository;

//    public String saveBook(BookRequestDto bookRequestDto){
//        Book book = BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);
//
//        // using authorid fetch the complete object of author from authorrepository
//        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
//        if(author!=null){
//            book.setAuthor(author);
//        } else{
//            book.setAuthor(null);
//        }
//
//        // using cardid fetch the complete object of card from cardrepository
//        Card card = cardRepository.findById(bookRequestDto.getCardId()).get();
//        if(card!=null){
//            book.setCard(card);
//        }else{
//            book.setCard(null);
//        }
//
//
//        bookRepository.save(book);
//        return "Book saved successfully!";
//    }
public String saveBook(BookRequestDto bookRequestDto){
    Book book = BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);

    // using authorid fetch the complete object of author from authorrepository
    Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
    if(author!=null){
        book.setAuthor(author);
    } else{
        book.setAuthor(null);
    }

    // using cardid fetch the complete object of card from cardrepository
    Card card = cardRepository.findById(bookRequestDto.getCardId()).get();
    if(card!=null){
        book.setCard(card);
    }else{
        book.setCard(null);
    }


    bookRepository.save(book);
    return "Book saved successfully!";
}


    public List<Book> findAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    public Book findBookByID(int id){
      Optional<Book> bookOptional = bookRepository.findById(id);
      if(bookOptional.isPresent()){
          return bookOptional.get();
      }
      else{
//          return "student not present";
          throw new RuntimeException("student not found");
      }
    }
}
