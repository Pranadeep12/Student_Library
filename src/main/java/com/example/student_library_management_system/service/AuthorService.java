package com.example.student_library_management_system.service;

import com.example.student_library_management_system.Repository.AuthorRepository;
import com.example.student_library_management_system.converters.AuthorConverter;
import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.AuthorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
        @Autowired
        AuthorRepository authorRepository;

        public String saveAuthor( AuthorRequestDto authorRequestDto){
            Author author = AuthorConverter.convertAuthorRequestDtoIntoAuthor(authorRequestDto);
            authorRepository.save(author);
            return "Author saved successfully!";
        }

    public List<Author> findAllauthors(){
        List<Author> authorList = authorRepository.findAll();
        return authorList;
    }

    public Author findauthorById(int id){
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isPresent()){
            return authorOptional.get();
        } else{
            throw new RuntimeException("Author not found");
        }
    }
}
