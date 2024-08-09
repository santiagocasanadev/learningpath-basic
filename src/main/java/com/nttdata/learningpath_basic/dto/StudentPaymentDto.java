package com.nttdata.learningpath_basic.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentPaymentDto {

  private Long id;
  private Long studentId;
  private LocalDate paymentDate;
  private BigDecimal amountPaid;
  private BigDecimal amountDue;
  private BigDecimal monthlyStandardAmount;
}
