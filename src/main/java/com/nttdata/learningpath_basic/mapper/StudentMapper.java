package com.nttdata.learningpath_basic.mapper;

import com.nttdata.learningpath_basic.dto.StudentDto;
import com.nttdata.learningpath_basic.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  StudentDto mapToDto(Student student);

  Student mapToEntity(StudentDto studentDTO);
}
