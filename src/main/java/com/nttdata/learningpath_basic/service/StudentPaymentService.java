package com.nttdata.learningpath_basic.service;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentPaymentDto;

public interface StudentPaymentService {

  List<StudentPaymentDto> getPaymentsByStudentId(Long studentId);

  StudentPaymentDto addStudentPayment(StudentPaymentDto studentPayment);

  StudentPaymentDto updateStudentPayment(Long paymentId, StudentPaymentDto studentPayment);

  void deleteStudentPayment(Long id);
}
