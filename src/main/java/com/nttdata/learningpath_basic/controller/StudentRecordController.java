package com.nttdata.learningpath_basic.controller;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentRecordDto;
import com.nttdata.learningpath_basic.service.StudentRecordService;
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
@RequestMapping("/api/student_records")
public class StudentRecordController {

  @Autowired
  private StudentRecordService studentRecordService;

  @PostMapping
  public ResponseEntity<StudentRecordDto> createStudentRecord(@RequestBody StudentRecordDto studentRecordDto) {
    StudentRecordDto createdRecord = studentRecordService.createStudentRecord(studentRecordDto);
    return new ResponseEntity<>(createdRecord, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<StudentRecordDto>> getAllStudentRecords() {
    List<StudentRecordDto> studentRecords = studentRecordService.getAllStudentRecords();
    return new ResponseEntity<>(studentRecords, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentRecordDto> getStudentRecordById(@PathVariable Long id) {
    StudentRecordDto studentRecord = studentRecordService.getStudentRecordById(id);
    return new ResponseEntity<>(studentRecord, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentRecordDto> updateStudentRecord(@PathVariable Long id, @RequestBody StudentRecordDto studentRecordDto) {
    StudentRecordDto updatedRecord = studentRecordService.updateStudentRecord(id, studentRecordDto);
    return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudentRecord(@PathVariable Long id) {
    studentRecordService.deleteStudentRecordById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
