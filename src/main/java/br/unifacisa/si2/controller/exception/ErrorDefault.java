package br.unifacisa.si2.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDefault {

	private int status;
	
	private long timestamp;
	
	private String msg;
	
	
	
	
}
