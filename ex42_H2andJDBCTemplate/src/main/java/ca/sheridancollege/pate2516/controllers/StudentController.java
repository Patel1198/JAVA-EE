package ca.sheridancollege.pate2516.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.pate2516.beans.Student;
import ca.sheridancollege.pate2516.database.DatabaseAccess;
@Controller
public class StudentController {
	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String homeMethod(Model model) {
		//da.insertStudent();
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.getStudents());
		return "index";
	}
	
	@PostMapping("/insertStudent")
	public String insertStudent(Model model, @ModelAttribute Student student) {
		da.insertStudent(student.getName());
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.getStudents());
		return "index";
		}
}
