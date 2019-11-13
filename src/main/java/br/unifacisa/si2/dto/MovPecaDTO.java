package br.unifacisa.si2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovPecaDTO {
	
	private PositionAndBoardDTO positionAndBoardDTO;
	
	private PositionDTO end;
	
}
