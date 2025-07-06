package com.example.student_library_management_system.Repository;

import com.example.student_library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    // write user defined customized queries
//    @Query(nativeQuery = true, value = "select * from student where email= :inputEmail")
    @Query(nativeQuery = true, value="select * from student where email= :inputEmail")
    public Student getStudentByEmail(String inputEmail);




}
