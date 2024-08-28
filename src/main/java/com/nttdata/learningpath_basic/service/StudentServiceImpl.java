package com.nttdata.learningpath_basic.service;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentDto;
import com.nttdata.learningpath_basic.exception.StudentServiceException;
import com.nttdata.learningpath_basic.mapper.StudentMapper;
import com.nttdata.learningpath_basic.model.Student;
import com.nttdata.learningpath_basic.repository.StudentRepository;
import com.nttdata.learningpath_basic.utils.StudentRecordUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentMapper studentMapper;

  @Override
  @Transactional
  public StudentDto createStudent(StudentDto studentDto) {
    try {
      Student student = studentMapper.mapToEntity(studentDto);
      student.setStudentId(StudentRecordUtils.generarStudentId());
      Student savedStudent = studentRepository.save(student);
      return studentMapper.mapToDto(savedStudent);
    } catch (Exception e) {
      throw new StudentServiceException("Error al crear el estudiante: " + e.getMessage());
    }
  }

  @Override
  public StudentDto getStudentById(String studentId) {
    try {
      Student student = studentRepository.findByStudentId(studentId)
          .orElseThrow(() -> new StudentServiceException("No se econtró estudiante con el código: " + studentId));
      return studentMapper.mapToDto(student);
    } catch (Exception e) {
      throw new StudentServiceException("Error al obtener el estudiante: " + e.getMessage());
    }
  }

  @Override
  public List<StudentDto> getAllStudents() {
    try {
      List<Student> students = studentRepository.findAll();
      return students.stream().map(studentMapper::mapToDto).toList();
    } catch (Exception e) {
      throw new StudentServiceException("Error al obtener la lista de estudiantes: " + e.getMessage());
    }
  }

  @Override
  @Transactional
  public StudentDto updateStudent(String studentId, StudentDto studentDto) {
    try {
      Student existingStudent = studentRepository.findByStudentId(studentId)
          .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con el código: " + studentId));

      existingStudent.setStudentName(studentDto.getStudentName());
      existingStudent.setEmail(studentDto.getEmail());

      Student updatedStudent = studentRepository.save(existingStudent);
      return studentMapper.mapToDto(updatedStudent);
    } catch (Exception e) {
      throw new StudentServiceException("Error al actualizar el estudiante: " + e.getMessage());
    }
  }

  @Override
  public void deleteStudentByCode(String studentId) {
    try {
      Student student = studentRepository.findByStudentId(studentId)
          .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con el código: " + studentId));
      studentRepository.delete(student);
    } catch (Exception e) {
      throw new StudentServiceException("Error al eliminar el estudiante: " + e.getMessage());
    }
  }
}
