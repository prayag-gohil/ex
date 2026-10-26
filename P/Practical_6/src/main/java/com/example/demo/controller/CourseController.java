package com.example.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course)
	{
		Course savedCourse = courseRepository.save(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
	}
	
	@GetMapping("/{id}/students")
	public ResponseEntity<Set<Student>> getCourse(@PathVariable int id)
	{
		Course course = courseRepository.findById(id).get();
		return ResponseEntity.ok(course.getStudent());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable int id,@RequestBody Course course)
	{
		Course existingCourse = courseRepository.findById(id).get();
		existingCourse.setCourseName(course.getCourseName());
		Course updatedCourse = courseRepository.save(existingCourse);
		return ResponseEntity.ok(updatedCourse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable int id)
	{
		Course course = courseRepository.findById(id).get();
		Set<Student> students = course.getStudent();
		
		for(Student student : students)
		{
			student.getCourses().remove(course);
			studentRepository.save(student);
		}
		courseRepository.delete(course);
		return ResponseEntity.noContent().build();
	}

}
