package com.developer.controller;

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

import com.developer.dto.requestDTO.MobileRequestDTO;
import com.developer.dto.responseDTO.MobileResponseDTO;
import com.developer.service.Iservice.MobileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = { "/api/v1/mobile" })
@RequiredArgsConstructor
public class MobileController {

	private final MobileService mobileService;

	@GetMapping("/getAllMobile")
	public ResponseEntity<?> getAllMobile() {
		try {
			return mobileService.getAllMobile().isEmpty()
					? ResponseEntity.status(HttpStatus.NO_CONTENT).body("List does not contain any data")
					: ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = { "/By/{id}" })
	public ResponseEntity<?> getMobileById(@PathVariable("id") Long mobileId) {
		try {
			return ResponseEntity.ofNullable(mobileService.getMobileById(mobileId));
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		}
	}

	@PostMapping(path = { "/save" })
	public ResponseEntity<?> saveMobile(@RequestBody MobileRequestDTO mobileRequestDTO) {
		try {
			MobileResponseDTO saveMobile = mobileService.saveMobile(mobileRequestDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveMobile);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@PutMapping(path = { "/update/{id}" })
	public ResponseEntity<?> updateMobile(@PathVariable("id") Long mobileId,
			@RequestBody MobileRequestDTO mobileRequestDTO) {
		try {
			MobileResponseDTO updateMobile = mobileService.updateMobile(mobileId, mobileRequestDTO);
			return ResponseEntity.status(HttpStatus.OK).body(updateMobile);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<?> deleteMobileById(@PathVariable("id") Long mobileId) {
		try {
			return ResponseEntity.ofNullable(mobileService.deleteMobile(mobileId));
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
