package com.nttdata.learningpath_basic.service;

import java.util.List;
import java.util.stream.Collectors;

import com.nttdata.learningpath_basic.dto.StudentPaymentDto;
import com.nttdata.learningpath_basic.entity.StudentPayment;
import com.nttdata.learningpath_basic.mapper.StudentPaymentMapper;
import com.nttdata.learningpath_basic.repository.StudentPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentPaymentServiceImpl implements StudentPaymentService{

  @Autowired
  private StudentPaymentRepository studentPaymentRepository;


  @Override
  public List<StudentPaymentDto> getPaymentsByStudentId(Long studentId) {
    return studentPaymentRepository.findByStudentId(studentId)
        .stream()
        .map(StudentPaymentMapper.INSTANCE::studentPaymentToStudentPaymentDto)
        .collect(Collectors.toList());
  }

  @Override
  public StudentPaymentDto addStudentPayment(StudentPaymentDto studentPaymentDto) {
    StudentPayment studentPayment = StudentPaymentMapper.INSTANCE.studentPaymentDtoToStudentPayment(studentPaymentDto);
    studentPayment = studentPaymentRepository.save(studentPayment);
    return StudentPaymentMapper.INSTANCE.studentPaymentToStudentPaymentDto(studentPayment);
  }

  @Override
  public StudentPaymentDto updateStudentPayment(Long paymentId, StudentPaymentDto studentPaymentDto) {
    StudentPayment payment = studentPaymentRepository.findById(paymentId)
        .orElseThrow();

    payment.setPaymentDate(studentPaymentDto.getPaymentDate());
    payment.setAmountPaid(studentPaymentDto.getAmountPaid());
    payment.setAmountDue(studentPaymentDto.getMonthlyStandardAmount().subtract(studentPaymentDto.getAmountPaid()));
    payment.setMonthlyStandardAmount(studentPaymentDto.getMonthlyStandardAmount());

    payment = studentPaymentRepository.save(payment);
    return StudentPaymentMapper.INSTANCE.studentPaymentToStudentPaymentDto(payment);
  }

  @Override
  public void deleteStudentPayment(Long paymentId) {
    StudentPayment payment = studentPaymentRepository.findById(paymentId)
        .orElseThrow();
    studentPaymentRepository.delete(payment);
  }
}
