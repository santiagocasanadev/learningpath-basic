package com.nttdata.learningpath_basic.service;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentDto;

public interface StudentService {

  StudentDto createStudent(StudentDto studentDto);

  StudentDto getStudentById(String studentId);

  List<StudentDto> getAllStudents();

  StudentDto updateStudent(String studentId, StudentDto studentDto);

  void deleteStudentByCode(String studentId);
}