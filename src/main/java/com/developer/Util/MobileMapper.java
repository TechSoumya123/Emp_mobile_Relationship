package com.developer.Util;

import com.developer.dto.responseDTO.MobileResponseDTO;
import com.developer.model.Mobile;

public class MobileMapper {

	public static MobileResponseDTO mobile_To_MobileResponseDTO(Mobile mobile) {	
		return new MobileResponseDTO(mobile.getId(), mobile.getMobileName());
	}

}
