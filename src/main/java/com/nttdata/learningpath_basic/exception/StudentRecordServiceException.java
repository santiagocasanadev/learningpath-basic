package com.nttdata.learningpath_basic.exception;

public class StudentRecordServiceException extends RuntimeException {

  public StudentRecordServiceException(String message) {
    super(message);
  }

  public StudentRecordServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
