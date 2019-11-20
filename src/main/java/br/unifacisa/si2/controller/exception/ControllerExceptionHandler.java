package br.unifacisa.si2.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.unifacisa.si2.models.exceptions.InvalidPieceException;
import br.unifacisa.si2.models.exceptions.SizeBoardException;
import br.unifacisa.si2.service.exception.*;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(InvalidPieceException.class)
	public ResponseEntity<ErrorDefault> pieceValidException(InvalidPieceException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorDefault(HttpStatus.BAD_REQUEST.value(),System.currentTimeMillis(),e.getMessage())
				);
	}
	
	@ExceptionHandler(InvalidPositionException.class)
	public ResponseEntity<ErrorDefault> positionValidException(InvalidPositionException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorDefault(HttpStatus.BAD_REQUEST.value(),System.currentTimeMillis(),e.getMessage())
				);
	}
	
	@ExceptionHandler(NotFoundBoardException.class)
	public ResponseEntity<ErrorDefault> positionValidException(NotFoundBoardException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ErrorDefault(HttpStatus.NOT_FOUND.value(),System.currentTimeMillis(),e.getMessage())
				);
	}
	
	@ExceptionHandler(SizeBoardException.class)
	public ResponseEntity<ErrorDefault> positionValidException(SizeBoardException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorDefault(HttpStatus.BAD_REQUEST.value(),System.currentTimeMillis(),e.getMessage())
				);
	}
}
