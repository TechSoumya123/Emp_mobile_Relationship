package com.developer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.server.ResponseStatusException;

import com.developer.dto.requestDTO.EmployeeRequestDTO;
import com.developer.dto.responseDTO.EmployeeResponseDTO;
import com.developer.service.Iservice.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = { "/api/v1/employee" })
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping(path = { "/save" })
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		var employee = employeeService.saveEmployee(employeeRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}

	@GetMapping(path = { "/allEmployee" })
	public ResponseEntity<?> getAllEmployee() {
		var allEmployee = employeeService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(allEmployee);
	}

	@GetMapping(path = { "/get_EmployeeAndMobile_ById/{id}" })
	public ResponseEntity<?> get_EmployeeAndMobile_ById(@PathVariable("id") Long empId) {
		try {
			var employeeWithMobileById = employeeService.findEmployeeWithMobileById(empId);
			return ResponseEntity.status(HttpStatus.OK).body(employeeWithMobileById);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		}
	}

	@GetMapping(path = { "/get_EmployeeWithOutMobile_ById/{id}" })
	public ResponseEntity<?> get_EmployeeWithOutMobile_ById(@PathVariable("id") Long empId) {
		try {
			var employeeWithMobileById = employeeService.findEmployeeWithoutMobile(empId);
			return ResponseEntity.status(HttpStatus.OK).body(employeeWithMobileById);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		}
	}

	@PutMapping(path = { "/update/{id}" })
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Long empId,
			@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		try {
			var employeeResponseDTO = employeeService.updateEmployee(empId, employeeRequestDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDTO);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long empId) {
		try {
			var deleteEmployee = employeeService.deleteEmployee(empId);
			return ResponseEntity.status(HttpStatus.CREATED).body(deleteEmployee);
		} catch (ResponseStatusException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
	}

	@PostMapping(path = { "/add/{id}/{id1}" })
	public ResponseEntity<?> addMobileToEmployee(@PathVariable("id") Long empId, @PathVariable("id1") Long mobileId) {
		try {
			EmployeeResponseDTO mobileToEmployee = employeeService.addMobileToEmployee(empId, mobileId);
			return ResponseEntity.status(HttpStatus.OK).body(mobileToEmployee);
		} catch (ResponseStatusException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}
	
	@PostMapping(path = { "/remove/{id}/{id1}" })
	public ResponseEntity<?> removeMobileToEmployee(@PathVariable("id") Long empId, @PathVariable("id1") Long mobileId) {
		try {
			 EmployeeResponseDTO deleteMobileToEmployee = employeeService.deleteMobileToEmployee(empId, mobileId);
			return ResponseEntity.status(HttpStatus.OK).body(deleteMobileToEmployee);
		} catch (ResponseStatusException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
