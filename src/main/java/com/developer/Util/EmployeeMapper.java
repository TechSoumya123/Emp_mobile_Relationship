package com.developer.Util;

import org.springframework.stereotype.Component;

import com.developer.dto.requestDTO.EmployeeRequestDTO;
import com.developer.dto.requestDTO.MobileRequestDTO;
import com.developer.dto.responseDTO.EmployeeResponseDTO;
import com.developer.dto.responseDTO.MobileResponseDTO;
import com.developer.model.Employee;
import com.developer.model.Mobile;

@Component
public class EmployeeMapper {

	public static EmployeeResponseDTO employee_To_EmployeeResponseDTO(Employee employee) {
		if (employee.getMobile() == null) {
			return new EmployeeResponseDTO().setId(employee.getId()).setName(employee.getName());
		} else {
			return new EmployeeResponseDTO().setId(employee.getId()).setName(employee.getName())
					.setMobileResponseDTO(mobile_To_MobileResponseDTO(employee.getMobile()));
		}
	}

	public static MobileResponseDTO mobile_To_MobileResponseDTO(Mobile mobile) {
		return new MobileResponseDTO(mobile.getId(), mobile.getMobileName());
	}

	public static Employee employeeRequestDTO_To_Employee(EmployeeRequestDTO employeeRequestDTO) {
		Employee employee = new Employee();
		employee.setName(employeeRequestDTO.name());
		return employee;
	}

	public static Mobile mobileRequestDto_To_Mobile(MobileRequestDTO mobileRequestDTO) {
		Mobile mobile = new Mobile();
		if (mobileRequestDTO.mobileName() != null) {
			mobile.setMobileName(mobileRequestDTO.mobileName());
		}
		return mobile;
	}

	public static EmployeeResponseDTO emp_To_EmployeeResponseDTOWithOutMobile(Employee emp) {
		return new EmployeeResponseDTO().setId(emp.getId()).setName(emp.getName());
	}

}
