package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentRequestDto studentRequestDto) {
       try {
           String response = studentService.addStudent(studentRequestDto);
           return response;
       } catch (Exception e) {
           System.out.println("exception occured :"+e.getMessage());
           return "exception occured : "+e.getMessage();
       }
    }

    @GetMapping("/findAll")
    public List<Student> findAllstudents() {
        List<Student> studentList = studentService.findAllstudents();
        return studentList;
    }

    @GetMapping("/find/{id}")
    public Student findStudentById(@PathVariable int id) {
        try {
            Student student = studentService.findStudentById(id);
            // studentresponsedto.setname( student.getName()); - response dto used to send only required response in output
            return student;
        } catch (Exception e) {
            System.out.println("Exception occurred : " + e.getMessage());
            throw new RuntimeException("student not found");
        }
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody StudentRequestDto studentRequestDto) {
        String response = studentService.updateStudent(id, studentRequestDto);
        return response;
    }

    @GetMapping("/count")
    public String countStudents() {
        String response = studentService.countStudents();
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id) {
        String response = studentService.deleteStudentById(id);
        return response;
    }

    @GetMapping("/findPage")
    public List<Student> getStudentByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        List<Student> studentList = studentService.getAllStudentsUsingPage(pageNo,pageSize);
        return studentList;
    }

    @GetMapping("findByEmail")
    public Student findStudentBYEmail(@RequestParam String email) {
        Student student = studentService.findStudentbyemail(email);
        return student;

    }
}
