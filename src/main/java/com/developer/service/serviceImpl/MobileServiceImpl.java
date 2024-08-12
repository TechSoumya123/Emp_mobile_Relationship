package com.developer.service.serviceImpl;

import static com.developer.Util.MobileMapper.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.developer.dto.requestDTO.MobileRequestDTO;
import com.developer.dto.responseDTO.MobileResponseDTO;
import com.developer.model.Mobile;
import com.developer.repository.MobileRepository;
import com.developer.service.Iservice.MobileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {

	private final MobileRepository mobileRepository;

	@Override
	public List<MobileResponseDTO> getAllMobile() {
		return mobileRepository.findAll().stream().map(mobile -> mobile_To_MobileResponseDTO(mobile))
				.collect(Collectors.toList());
	}

	@Override
	public MobileResponseDTO getMobileById(Long mobileId) {
		log.info("\n MOBILE request by id \n {}", mobileId);
		return getMobile(mobileId).map(mobile -> mobile_To_MobileResponseDTO(mobile)).get();
	}

	@Override
	public MobileResponseDTO saveMobile(MobileRequestDTO mobileRequestDTO) {
		var mobile = new Mobile().setMobileName(mobileRequestDTO.mobileName());
		log.info("\n Mobile Request with save \n {}", mobile);
		mobile = mobileRepository.save(mobile);
		log.info("\n Saved Mobile \n {}", mobile);
		return mobile_To_MobileResponseDTO(mobile);
	}

	@Override
	public MobileResponseDTO updateMobile(Long mobileId, MobileRequestDTO mobileRequestDTO) {
		return getMobile(mobileId).map(existingMobile -> {
			var updateMobile = existingMobile.setMobileName(mobileRequestDTO.mobileName());
			updateMobile = mobileRepository.save(updateMobile);
			return mobile_To_MobileResponseDTO(updateMobile);
		}).get();
	}

	@Override
	public MobileResponseDTO deleteMobile(Long mobileId) {
		return getMobile(mobileId).map(mobile -> {
			mobileRepository.delete(mobile);
			return mobile_To_MobileResponseDTO(mobile);
		}).get();

	}

	@Override
	public Optional<Mobile> getMobile(Long mobileId) {
		return Optional.ofNullable(mobileRepository.findById(mobileId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry , Mobile not found")));
	}

}
