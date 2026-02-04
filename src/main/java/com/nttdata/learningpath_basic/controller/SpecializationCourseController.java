package com.nttdata.learningpath_basic.controller;

import java.util.List;

import com.nttdata.learningpath_basic.model.SpecializationCourse;
import com.nttdata.learningpath_basic.service.SpecializationCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class SpecializationCourseController {

  @Autowired
  private SpecializationCourseService courseService;

  @PostMapping
  public SpecializationCourse createCourse(@RequestBody SpecializationCourse course) {
    return courseService.createCourse(course);
  }

  @GetMapping
  public List<SpecializationCourse> getAllCourses() {
    return courseService.getAllCourses();
  }

  @PutMapping("/{id}")
  public SpecializationCourse updateCourse(@PathVariable Long id, @RequestBody SpecializationCourse course) {
    return courseService.updateCourse(id, course);
  }

  @DeleteMapping("/{id}")
  public void deleteCourse(@PathVariable Long id) {
    courseService.deleteCourse(id);
  }
}
