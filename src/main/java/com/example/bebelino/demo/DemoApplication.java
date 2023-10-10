package com.example.bebelino.demo;

import com.example.bebelino.demo.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	private final StudentRepository studentRepository;

	public DemoApplication(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("api/v1/customers")
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	record NewStudentRequest(
			Integer age,
			String email,
			String firstName,
			String lastName
	){

	}

	@PostMapping("api/v1/addstudent")
	public void addStudent(@RequestBody NewStudentRequest request) {
		Student student = new Student();
		student.setAge(request.age);
		student.setEmail(request.email);
		student.setFirstName(request.firstName);
		student.setLastName(request.lastName);
		studentRepository.save(student);
	}


	@DeleteMapping("api/v1/{id}")
	public void deleteStudent(@PathVariable("id") Integer id) {
		studentRepository.deleteById(id);
	}
}
