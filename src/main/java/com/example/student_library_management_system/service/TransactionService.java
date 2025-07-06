package com.example.student_library_management_system.service;

import com.example.student_library_management_system.Repository.BookRepository;
import com.example.student_library_management_system.Repository.CardRepository;
import com.example.student_library_management_system.Repository.TransactionRepository;
import com.example.student_library_management_system.converters.TransactionConverter;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Transaction;
import com.example.student_library_management_system.requestdto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String saveTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = TransactionConverter.convertTransactionRequestDtoIntoTransaction(transactionRequestDto);

        Book book = bookRepository.findById(transactionRequestDto.getBookId()).get();
        if(book!=null){
            transaction.setBook(book);
        } else{
            transaction.setBook(null);
        }

        Card card = cardRepository.findById(transactionRequestDto.getCardId()).get();
        if(card!=null){
            transaction.setCard(card);
        }else{
            transaction.setCard(null);
        }

        transactionRepository.save(transaction);
        return "Transaction saved successfully!";

    }
}