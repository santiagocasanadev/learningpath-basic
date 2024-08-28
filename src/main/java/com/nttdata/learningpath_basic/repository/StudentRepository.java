package com.nttdata.learningpath_basic.repository;

import java.util.Optional;

import com.nttdata.learningpath_basic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  Optional<Student> findByStudentId(String studentId);
}
