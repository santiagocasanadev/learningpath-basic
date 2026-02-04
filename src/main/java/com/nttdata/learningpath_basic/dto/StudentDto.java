package com.nttdata.learningpath_basic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

  private Long id;

  private String studentId;

  private String studentName;

  private String email;
}
