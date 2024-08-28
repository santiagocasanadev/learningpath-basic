package com.nttdata.learningpath_basic.service;

import java.time.LocalDate;
import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentRecordDto;
import com.nttdata.learningpath_basic.exception.StudentRecordServiceException;
import com.nttdata.learningpath_basic.mapper.StudentRecordMapper;
import com.nttdata.learningpath_basic.model.SpecializationCourse;
import com.nttdata.learningpath_basic.model.Student;
import com.nttdata.learningpath_basic.model.StudentRecord;
import com.nttdata.learningpath_basic.repository.SpecializationCourseRepository;
import com.nttdata.learningpath_basic.repository.StudentRecordRepository;
import com.nttdata.learningpath_basic.repository.StudentRepository;
import com.nttdata.learningpath_basic.utils.StudentRecordUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRecordServiceImpl implements StudentRecordService {

  @Autowired
  private StudentRecordRepository studentRecordRepository;

  @Autowired
  private SpecializationCourseRepository courseRepository;

  @Autowired
  private StudentRecordMapper studentRecordMapper;

  @Autowired
  private StudentRepository studentRepository;

  @Override
  @Transactional
  public StudentRecordDto createStudentRecord(StudentRecordDto studentRecordDto) {
    try {
      Student student = studentRepository.findByStudentId(studentRecordDto.getStudentId())
          .orElseThrow(() -> new StudentRecordServiceException("Estudiante no encontrado con el ID: " + studentRecordDto.getStudentId()));

      Long courseId = studentRecordDto.getSpecializationCourseId();
      SpecializationCourse course = courseRepository.findById(courseId)
          .orElseThrow(() -> new StudentRecordServiceException("Curso de especialización no encontrado con el ID: " + courseId));

      StudentRecord studentRecord = studentRecordMapper.mapToEntity(studentRecordDto);
      studentRecord.setStudent(student);
      studentRecord.setCourse(course);

      double paymentAmount = studentRecordDto.getAmount();
      double totalCourseCost = course.getCost();

      StudentRecordUtils.setRecordStatusAndPayment(studentRecord, paymentAmount, totalCourseCost);
      studentRecord.setPaymentDate(LocalDate.now());

      StudentRecord savedRecord = studentRecordRepository.save(studentRecord);
      return studentRecordMapper.mapToDto(savedRecord);

    } catch (StudentRecordServiceException e) {
      throw e;
    } catch (Exception e) {
      throw new StudentRecordServiceException("Error al crear el registro del estudiante", e);
    }
  }

  @Override
  @Transactional
  public StudentRecordDto updateStudentRecord(Long id, StudentRecordDto studentRecordDto) {
    try {
      StudentRecord studentRecord = studentRecordRepository.findById(id)
          .orElseThrow(() -> new StudentRecordServiceException("StudentRecord no encontrado con el ID: " + id));
      if (Boolean.TRUE.equals(studentRecord.getIsFullPayment())) {
        throw new StudentRecordServiceException("La matrícula ya está completada");
      }
      SpecializationCourse course = studentRecord.getCourse();
      double totalCourseCost = course.getCost();
      double amountPaid = studentRecord.getAmount();
      double additionalPayment = studentRecordDto.getAmount();
      double remainingAmount = totalCourseCost - amountPaid;
      if (additionalPayment > remainingAmount) {
        throw new StudentRecordServiceException("El monto pagado es mayor al costo restante del curso");
      }
      studentRecord.setAmount(amountPaid + additionalPayment);
      studentRecord.setPaymentDate(LocalDate.now());

      StudentRecordUtils.setRecordStatusAndPayment(studentRecord, studentRecord.getAmount(), totalCourseCost);
      StudentRecord updatedRecord = studentRecordRepository.save(studentRecord);

      return StudentRecordUtils.buildStudentRecordDto(updatedRecord, totalCourseCost);
    } catch (StudentRecordServiceException e) {
      throw e;
    } catch (Exception e) {
      throw new StudentRecordServiceException("Error al actualizar el registro del estudiante", e);
    }
  }

  @Override
  public void deleteStudentRecordById(Long id) {
    if (!studentRecordRepository.existsById(id)) {
      throw new StudentRecordServiceException("Registro no encontrado con el ID: " + id);
    }
    studentRecordRepository.deleteById(id);
  }

  @Override
  public StudentRecordDto getStudentRecordById(Long id) {
    StudentRecord studentRecord = studentRecordRepository.findById(id)
        .orElseThrow(() -> new StudentRecordServiceException("Registro no encontrado con el ID: " + id));
    if (Boolean.FALSE.equals(studentRecord.getIsFullPayment())) {
      StudentRecordUtils.updateRecordStatus(studentRecord);
    }
    return studentRecordMapper.mapToDto(studentRecord);
  }

  @Override
  public List<StudentRecordDto> getAllStudentRecords() {
    return studentRecordRepository.findAll().stream()
        .map(record -> StudentRecordUtils.buildStudentRecordDto(record, record.getCourse().getCost()))
        .toList();
  }
}