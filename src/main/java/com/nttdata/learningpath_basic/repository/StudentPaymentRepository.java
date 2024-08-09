package com.nttdata.learningpath_basic.repository;

import java.util.List;

import com.nttdata.learningpath_basic.entity.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {

  List<StudentPayment> findByStudentId(Long studentId);
}
