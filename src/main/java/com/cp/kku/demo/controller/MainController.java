package com.cp.kku.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cp.kku.demo.IEmployeeService;
import com.cp.kku.demo.model.Employee;

@Controller
public class MainController {
	
	@Autowired
	private IEmployeeService employeeRepository;
	
	@RequestMapping("/")
	public String userForm(Model model) {
		return "index";
	}
	
	@GetMapping("/buy")
	public String showForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "EmployeeForm";
	}
	
	@PostMapping("/buy")
	public String submitForm(Model model, @ModelAttribute("employee") Employee employee){
		employeeRepository.save(employee);
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		return "showEmployee";
	}
	
	@RequestMapping("/showBuy")
	public String showEmployee(Model model){
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		return "showBuy";
	}
	
	@GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Employee employee = employeeRepository.findById(id);
		model.addAttribute("employee", employee);
        return "updateEmployee";
	}
	
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employee employee, Model model) {
        employee.setId(id);
        employeeRepository.save(employee);
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "showEmployee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id, Model model) {
        employeeRepository.delete(id);
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "showEmployee";
    }
    
    

}
