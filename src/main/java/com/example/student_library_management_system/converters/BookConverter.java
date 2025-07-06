package com.example.student_library_management_system.converters;

import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.requestdto.BookRequestDto;

public class BookConverter {
    public static Book convertBookRequestDtoIntoBook(BookRequestDto bookRequestDto){
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setRackNo(bookRequestDto.getRackNo());
        book.setCategory(bookRequestDto.getCategory());
        book.setAvailability(bookRequestDto.isAvailability());
        book.setPages(bookRequestDto.getPages());
        book.setPublishedDate(bookRequestDto.getPublishedDate());
        book.setPublisherName(bookRequestDto.getPublisherName());

        return book;

    }
}
