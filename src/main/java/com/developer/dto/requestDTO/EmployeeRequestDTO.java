package com.developer.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeRequestDTO(

		@JsonProperty(value = "emp_name") String name,

		@JsonProperty(value = "emp_mobile") Long mobileId) {
}
