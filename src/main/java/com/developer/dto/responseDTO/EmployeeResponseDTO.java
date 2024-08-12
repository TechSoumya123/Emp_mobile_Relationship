package com.developer.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class EmployeeResponseDTO {

	@JsonProperty(value = "emp_id")
	private Long id;

	@JsonProperty(value = "emp_name")
	private String name;

	@JsonProperty(value = "emp_mobile")
	private MobileResponseDTO mobileResponseDTO;

}
