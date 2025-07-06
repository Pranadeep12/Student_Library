package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.BookRequestDto;
//import com.example.student_library_management_system.service.BookService;
import com.example.student_library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/book/apis")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/save")
    public String saveBook(@RequestBody BookRequestDto bookRequestDto){
        String response = bookService.saveBook(bookRequestDto);
        return response;
    }

    @GetMapping("/findAll")
    public List<Book> findAllStudents(){
        List<Book> bookList = bookService.findAllBooks();
        return bookList;
    }

    @GetMapping("/find/{id}")
    public Book findbookById(@PathVariable  int id){
       Book book = bookService.findBookByID(id);
       return book;
    }
}
