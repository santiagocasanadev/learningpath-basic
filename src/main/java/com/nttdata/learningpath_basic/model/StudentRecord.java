package com.nttdata.learningpath_basic.model;

import java.time.LocalDate;

import com.nttdata.learningpath_basic.utils.StudentRecordStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_records")
public class StudentRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private SpecializationCourse course;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "payment_date")
  private LocalDate paymentDate;

  @Column(name = "full_payment")
  private Boolean isFullPayment;

  @Column(name = "record_status")
  @Enumerated(EnumType.STRING)
  private StudentRecordStatus recordStatus;

  @Column(name = "amount_paid")
  private Double amountPaid;
}
