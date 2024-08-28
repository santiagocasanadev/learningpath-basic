package com.nttdata.learningpath_basic.controller;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentDto;
import com.nttdata.learningpath_basic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDTO) {
    StudentDto createdStudent = studentService.createStudent(studentDTO);
    return new ResponseEntity<>(createdStudent, HttpStatus.OK);
  }

  @GetMapping("/{studentId}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable String studentId) {
    StudentDto student = studentService.getStudentById(studentId);
    return new ResponseEntity<>(student, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<StudentDto>> getAllStudents() {
    List<StudentDto> students = studentService.getAllStudents();
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @PutMapping("/{studentId}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable String studentId, @RequestBody StudentDto studentDTO) {
    StudentDto updatedStudent = studentService.updateStudent(studentId, studentDTO);
    return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
  }

  @DeleteMapping("/{studentId}")
  public ResponseEntity<Void> deleteStudentByCode(@PathVariable String studentId) {
    studentService.deleteStudentByCode(studentId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
