package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.AuthorRequestDto;
import com.example.student_library_management_system.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author/apis")
public class AuthorController {
        @Autowired
        AuthorService authorService;

        @PostMapping("/save")
        public String saveAuthor(@RequestBody AuthorRequestDto authorRequestDto){
            String response = authorService.saveAuthor(authorRequestDto);
            return response;
        }

    @GetMapping("/findAll")
    public List<Author> findAllauthors(){
        List<Author> studentList = authorService.findAllauthors();
        return studentList;
    }

    @GetMapping("/find/{id}")
    public  Author findAuthorbyId(int id){
            Author author = authorService.findauthorById(id);
            return author;
    }
}
