package com.nttdata.learningpath_basic.service;

import java.util.List;

import com.nttdata.learningpath_basic.dto.StudentRecordDto;

public interface StudentRecordService {

  StudentRecordDto createStudentRecord(StudentRecordDto studentRecordDto);

  StudentRecordDto updateStudentRecord(Long id, StudentRecordDto studentRecordDto);

  void deleteStudentRecordById(Long id);

  StudentRecordDto getStudentRecordById(Long id);

  List<StudentRecordDto> getAllStudentRecords();
}
