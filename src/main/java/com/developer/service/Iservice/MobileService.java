package com.developer.service.Iservice;

import java.util.List;
import java.util.Optional;

import com.developer.dto.requestDTO.MobileRequestDTO;
import com.developer.dto.responseDTO.MobileResponseDTO;
import com.developer.model.Mobile;

public interface MobileService {

	List<MobileResponseDTO> getAllMobile();

	MobileResponseDTO getMobileById(Long mobileId);

	MobileResponseDTO saveMobile(MobileRequestDTO mobileRequestDTO);

	MobileResponseDTO updateMobile(Long mobileId, MobileRequestDTO mobileRequestDTO);

	MobileResponseDTO deleteMobile(Long mobileId);
	
	Optional<Mobile> getMobile(Long mobileId);
}
