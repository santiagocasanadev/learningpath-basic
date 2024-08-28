package com.nttdata.learningpath_basic.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRecordDto {

  private Long id;

  private String studentId;

  private Long specializationCourseId;

  private Double amount;

  private LocalDate paymentDate;

  private Boolean isFullPayment;

  private String recordStatus;

  private double amountPaid;

  private double remainingAmount;

  private Double totalCost;
}
