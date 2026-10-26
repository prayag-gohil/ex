package com.example.demo.controller;

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
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public StudentController(StudentRepository studentRepository, CourseRepository courseRepository)
	{
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}
	
	//create student
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student)
	{
		Student savedStudent = studentRepository.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}
	
	@PostMapping("/{id}/enroll/{courseId}")
	public ResponseEntity<Student> enrollCourse(@PathVariable int id,@PathVariable int courseId)
	{
		Student student = studentRepository.findById(id).get();
		Course course = courseRepository.findById(courseId).get();
		student.getCourses().add(course);
		Student savedStudent = studentRepository.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}
	
	//Show student by id
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id)
	{
		Student student = studentRepository.findById(id).get();
		return ResponseEntity.ok(student);
	}
	
	//Put
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student student)
	{
		Student existingStudent = studentRepository.findById(id).get();
		existingStudent.setStudentName(student.getStudentName());
		existingStudent.setCourses(student.getCourses());
		Student updateStudent = studentRepository.save(existingStudent);
		return ResponseEntity.ok(updateStudent);
	}
	
	//Delete
	@DeleteMapping("{studentId}/unenroll/{courseId}")
	public ResponseEntity<String> unenrollCourse(@PathVariable int studentId,@PathVariable int courseId)
	{
		Student student = studentRepository.findById(studentId).get();
		Course course = courseRepository.findById(courseId).get();
		student.getCourses().remove(course);
		studentRepository.save(student);
		return ResponseEntity.ok("Student Deleted Successfully");
	}

}
