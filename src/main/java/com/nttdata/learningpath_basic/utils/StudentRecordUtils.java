package com.nttdata.learningpath_basic.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.nttdata.learningpath_basic.dto.StudentRecordDto;
import com.nttdata.learningpath_basic.model.StudentRecord;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentRecordUtils {

  /**
   * Method to update the student's record status based on the payment date.
   *
   * @param studentRecord The student record to update.
   */
  public static void updateRecordStatus(StudentRecord studentRecord) {
    long daysBetween = ChronoUnit.DAYS.between(studentRecord.getPaymentDate(), LocalDate.now());
    studentRecord.setRecordStatus(daysBetween > 15 ? StudentRecordStatus.MOROSO : StudentRecordStatus.PAGO_PENDIENTE);
  }

  /**
   * Creates a StudentRecordDto from a student record and course cost.
   *
   * @param studentRecord The student record.
   * @param totalCourseCost The total course cost.
   * @return The corresponding StudentRecordDto.
   */
  public static StudentRecordDto buildStudentRecordDto(StudentRecord studentRecord, double totalCourseCost) {
    return StudentRecordDto.builder()
        .id(studentRecord.getId())
        .studentId(studentRecord.getStudent().getStudentId())
        .specializationCourseId(studentRecord.getCourse().getId())
        .amount(studentRecord.getAmount())
        .amountPaid(studentRecord.getAmount())
        .remainingAmount(totalCourseCost - studentRecord.getAmount())
        .recordStatus(String.valueOf(studentRecord.getRecordStatus()))
        .paymentDate(studentRecord.getPaymentDate())
        .isFullPayment(studentRecord.getIsFullPayment())
        .build();
  }

  /**
   * Generates a unique code with a prefix and a random number.
   *
   * @return A unique student code.
   */
  public static String generarStudentId() {
    int numero = StudentRecordConstants.RANDOM.nextInt(1000);
    return String.format("%s%03d", StudentRecordConstants.PREFIJO, numero);
  }

  /**
   * Method to update the record status and payment flag.
   *
   * @param studentRecord The student record to update.
   * @param paymentAmount The payment amount.
   * @param totalCourseCost The total course cost.
   */
  public static void setRecordStatusAndPayment(StudentRecord studentRecord, double paymentAmount, double totalCourseCost) {
    if (paymentAmount >= totalCourseCost) {
      studentRecord.setRecordStatus(StudentRecordStatus.PAGO_COMPLETADO);
      studentRecord.setIsFullPayment(true);
    } else {
      studentRecord.setRecordStatus(StudentRecordStatus.PAGO_NO_COMPLETADO);
      studentRecord.setIsFullPayment(false);
    }
  }
}
