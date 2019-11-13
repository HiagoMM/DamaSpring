package br.unifacisa.si2.dto;

import br.unifacisa.si2.models.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionAndBoardDTO {
	
	private Board board;
	
	private PositionDTO position;	
	
	
}
