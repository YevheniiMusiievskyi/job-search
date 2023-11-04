package com.kpi.social_network.exceptions.advice;

import com.kpi.social_network.exceptions.ForbiddenException;
import com.kpi.social_network.exceptions.NotFoundException;
import com.kpi.social_network.exceptions.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedException() {
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<?> handleForbiddenException(ForbiddenException exception) {
		return getDefaultResponse(exception, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
		return getDefaultResponse(exception, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<ResponseBody> getDefaultResponse(Exception exception, HttpStatus status) {
		return getDefaultResponse(exception.getMessage(), status);
	}

	private ResponseEntity<ResponseBody> getDefaultResponse(String message, HttpStatus status) {
		ResponseBody body = null;
		if (StringUtils.isNotBlank(message)) {
			body = new ResponseBody(message);
		}

		return new ResponseEntity<>(body, status);
	}
}
