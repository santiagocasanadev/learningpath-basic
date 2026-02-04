package com.nttdata.learningpath_basic.repository;

import com.nttdata.learningpath_basic.model.StudentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRecordRepository extends JpaRepository<StudentRecord, Long> {

}
