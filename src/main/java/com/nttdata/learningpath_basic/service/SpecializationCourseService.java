package com.nttdata.learningpath_basic.service;

import java.util.List;

import com.nttdata.learningpath_basic.model.SpecializationCourse;

public interface SpecializationCourseService {

  SpecializationCourse createCourse(SpecializationCourse course);

  SpecializationCourse updateCourse(Long id, SpecializationCourse course);

  void deleteCourse(Long id);

  List<SpecializationCourse> getAllCourses();
}