package com.nttdata.learningpath_basic.service;

import java.util.List;
import java.util.Optional;

import com.nttdata.learningpath_basic.model.SpecializationCourse;
import com.nttdata.learningpath_basic.repository.SpecializationCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecializationCourseServiceImpl implements SpecializationCourseService {

  @Autowired
  private SpecializationCourseRepository courseRepository;

  @Override
  public SpecializationCourse createCourse(SpecializationCourse course) {
    return courseRepository.save(course);
  }

  @Override
  public SpecializationCourse updateCourse(Long id, SpecializationCourse course) {
    Optional<SpecializationCourse> existingCourse = courseRepository.findById(id);
    if (existingCourse.isPresent()) {
      SpecializationCourse updatedCourse = existingCourse.get();
      updatedCourse.setCourseName(course.getCourseName());
      updatedCourse.setCost(course.getCost());
      return courseRepository.save(updatedCourse);
    } else {
      throw new RuntimeException("SpecializationCourse not found");
    }
  }

  @Override
  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }

  @Override
  public List<SpecializationCourse> getAllCourses() {
    return courseRepository.findAll();
  }
}
