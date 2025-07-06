package com.example.student_library_management_system.service;

import com.example.student_library_management_system.Repository.StudentRepository;
import com.example.student_library_management_system.converters.StudentConverter;
import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(StudentRequestDto studentRequestDto){
        Student student = StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);
        //whenever student is created the card also will be created
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setExpiryDate(LocalDate.now().plusYears(4).toString());
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);
        return "Student saved successfully!";
    }
    public List<Student> findAllstudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }
    public Student findStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else{
            throw new RuntimeException("student not found");
        }
    }
    public String updateStudent( int id, StudentRequestDto newStudentRequestDto){
        Student existingStudent = findStudentById(id);
        if(existingStudent!=null){
            existingStudent.setName(newStudentRequestDto.getName());
            existingStudent.setMobile(newStudentRequestDto.getMobile());
            existingStudent.setDob(newStudentRequestDto.getDob());
            existingStudent.setSem(newStudentRequestDto.getSem());
            existingStudent.setDept(newStudentRequestDto.getDept());
            existingStudent.setGender(newStudentRequestDto.getGender());
            existingStudent.setAddress(newStudentRequestDto.getAddress());
            existingStudent.setEmail(newStudentRequestDto.getEmail());

            studentRepository.save(existingStudent);
            return "student updated successfully";
        }
        else{
            return "student cannot be updated because it is not present";
        }
    }

    public String countStudents(){
        long totalCount = studentRepository.count();
        return "Total students present are : "+totalCount;
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student with id :"+id+"deleted successfully";
    }



    public Student findStudentbyemail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        return student;
    }

    public List<Student> getAllStudentsUsingPage(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("name").ascending())).getContent();
        return studentList;
    }
}
