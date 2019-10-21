package com.hyo.smart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyo.smart.domain.ButtonDTO;

/**
 * Handles requests for Kakao.
 */
@RestController
public class KakaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
		
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public ButtonDTO keyboard() throws Exception {
		logger.info("[KakaoController] keyboard.");
		
		return new ButtonDTO(new String[] {"Select1", "Select2", "Select3"});
	}
	
}
