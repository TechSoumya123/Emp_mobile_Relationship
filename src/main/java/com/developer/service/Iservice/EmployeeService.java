package com.developer.service.Iservice;

import java.util.List;

import com.developer.dto.requestDTO.EmployeeRequestDTO;
import com.developer.dto.responseDTO.EmployeeResponseDTO;

public interface EmployeeService {

	List<EmployeeResponseDTO> getAllEmployee();

	EmployeeResponseDTO findEmployeeWithMobileById(Long empId);

	EmployeeResponseDTO findEmployeeWithoutMobile(Long empId);

	EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO);

	EmployeeResponseDTO updateEmployee(Long empId, EmployeeRequestDTO employeeRequestDTO);

	EmployeeResponseDTO deleteEmployee(Long empId);

	EmployeeResponseDTO addMobileToEmployee(Long empId, Long mobileId);

	EmployeeResponseDTO deleteMobileToEmployee(Long empId, Long mobileId);

}
