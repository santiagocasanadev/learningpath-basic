package com.nttdata.learningpath_basic.controller;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentPaymentDto;
import com.nttdata.learningpath_basic.service.StudentPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = ("/api/studentpayments"))
public class StudentPaymentController implements StudentPaymentApi{

  @Autowired
  private StudentPaymentService studentPaymentService;

  @Override
  public StudentPaymentDto addStudentPayment(StudentPaymentDto request) {
    return studentPaymentService.addStudentPayment(request);
  }

  @Override
  public List<StudentPaymentDto> getPaymentsByStudentId(Long studentId) {
    return studentPaymentService.getPaymentsByStudentId(studentId);
  }

  @Override
  public StudentPaymentDto updateStudentPayment(Long paymentId, StudentPaymentDto request) {
    return studentPaymentService.updateStudentPayment(paymentId, request);
  }

  @Override
  public void deleteStudentPayment(Long paymentId) {
    studentPaymentService.deleteStudentPayment(paymentId);
  }
}
