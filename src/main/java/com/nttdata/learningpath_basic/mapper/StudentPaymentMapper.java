package com.nttdata.learningpath_basic.mapper;

import com.nttdata.learningpath_basic.dto.StudentPaymentDto;
import com.nttdata.learningpath_basic.entity.StudentPayment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentPaymentMapper {

  StudentPaymentMapper INSTANCE = Mappers.getMapper(StudentPaymentMapper.class);

  StudentPaymentDto studentPaymentToStudentPaymentDto(StudentPayment studentPayment);

  StudentPayment studentPaymentDtoToStudentPayment(StudentPaymentDto studentPaymentDto);
}
