package com.nttdata.learningpath_basic.mapper;

import com.nttdata.learningpath_basic.dto.StudentRecordDto;
import com.nttdata.learningpath_basic.model.StudentRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentRecordMapper {

  @Mapping(source = "student.studentId", target = "studentId")
  @Mapping(source = "course.id", target = "specializationCourseId")
  StudentRecordDto mapToDto(StudentRecord studentRecord);

  @Mapping(source = "studentId", target = "student.studentId")
  StudentRecord mapToEntity(StudentRecordDto studentRecordDto);
}
