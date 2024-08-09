package com.nttdata.learningpath_basic.controller;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentPaymentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface StudentPaymentApi {

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  StudentPaymentDto addStudentPayment(@RequestBody StudentPaymentDto request);

  @GetMapping(value = "/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  List<StudentPaymentDto> getPaymentsByStudentId(@PathVariable Long studentId);

  @PutMapping(value = "/{paymentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  StudentPaymentDto updateStudentPayment(@PathVariable Long paymentId, @RequestBody StudentPaymentDto request);

  @DeleteMapping(value = "/{paymentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteStudentPayment(@PathVariable Long paymentId);
}
