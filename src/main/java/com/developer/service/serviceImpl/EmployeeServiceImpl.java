package com.developer.service.serviceImpl;

import static com.developer.Util.EmployeeMapper.emp_To_EmployeeResponseDTOWithOutMobile;
import static com.developer.Util.EmployeeMapper.employeeRequestDTO_To_Employee;
import static com.developer.Util.EmployeeMapper.employee_To_EmployeeResponseDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.developer.dto.requestDTO.EmployeeRequestDTO;
import com.developer.dto.responseDTO.EmployeeResponseDTO;
import com.developer.exception.EmployeeNotFoundException;
import com.developer.exception.NoMobileFoundException;
import com.developer.model.Employee;
import com.developer.model.Mobile;
import com.developer.repository.EmployeeRepository;
import com.developer.service.Iservice.EmployeeService;
import com.developer.service.Iservice.MobileService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final MobileService mobileService;

	final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public List<EmployeeResponseDTO> getAllEmployee() {
		return employeeRepository.findAll().stream().map(emp -> employee_To_EmployeeResponseDTO(emp))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeResponseDTO findEmployeeWithMobileById(Long empId) {
		logger.info("\n SERVICE CLASS \n " + String.valueOf(empId));
		return employeeRepository.findById(empId).map(emp -> employee_To_EmployeeResponseDTO(emp))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Sorry, Employee not found with this id " + empId));
	}

	@Override
	public EmployeeResponseDTO findEmployeeWithoutMobile(Long empId) {
		return employeeRepository.findById(empId).map(emp -> emp_To_EmployeeResponseDTOWithOutMobile(emp))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Sorry, Employee not found with this id " + empId));
	}

	@Transactional
	@Override
	public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
		Employee employee = employeeRequestDTO_To_Employee(employeeRequestDTO);
		if (!Objects.nonNull(employeeRequestDTO.mobileId())) {
			employee = employeeRepository.save(employee);
			return employee_To_EmployeeResponseDTO(employee);
		}
		Mobile mobile = mobileService.getMobile(employeeRequestDTO.mobileId()).get();
		employee.setMobile(mobile);
		employee = employeeRepository.save(employee);
		return employee_To_EmployeeResponseDTO(employee);
	}

	@Transactional
	@Override
	public EmployeeResponseDTO updateEmployee(Long empId, EmployeeRequestDTO employeeRequestDTO) {
		return employeeRepository.findById(empId).map(existingEmployee -> {
			existingEmployee.setName(employeeRequestDTO.name());
			Employee employee = employeeRepository.save(existingEmployee);
			return employee_To_EmployeeResponseDTO(employee);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry no employee found"));
	}

	@Override
	public EmployeeResponseDTO deleteEmployee(Long empId) {
		return employeeRepository.findById(empId).map(emp -> {
			emp.setMobile(null);
			employeeRepository.delete(emp);
			return emp_To_EmployeeResponseDTOWithOutMobile(emp);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, employee not found"));
	}

	@Transactional
	@Override
	public EmployeeResponseDTO addMobileToEmployee(Long empId, Long mobileId) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
		return optionalEmployee.map(emp -> {
			if (Objects.nonNull(emp.getMobile())) {
				throw new EmployeeNotFoundException("Employee already assigned a mobile");
			}
			mobileService.getMobile(mobileId).map(mobile -> {
				Optional.ofNullable(mobile).ifPresent(emp::setMobile);
				return mobile;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, mobile not found"));
			return employee_To_EmployeeResponseDTO(emp);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, employee not found"));
	}

	@Transactional
	@Override
	public EmployeeResponseDTO deleteMobileToEmployee(Long empId, Long mobileId) {
		return employeeRepository.findById(empId).map(emp -> {
			if (Objects.isNull(emp.getMobile())) {
				throw new NoMobileFoundException("This employee does not have any mobile phone. ");
			}
			emp.setMobile(null);
			return employee_To_EmployeeResponseDTO(emp);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, employee not found"));
	}

}
