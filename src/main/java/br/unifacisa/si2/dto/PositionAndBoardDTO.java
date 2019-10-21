package br.unifacisa.si2.dto;

import br.unifacisa.si2.models.Board;
import lombok.Data;

@Data
public class PositionAndBoardDTO {
	
	private Board board;
	
	private PositionDTO position;
	
	private String tipoPlayer;
	
}
