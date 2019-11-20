package br.unifacisa.si2.dto;

import br.unifacisa.si2.models.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionAndBoardDTO {
	
	private Game game;
	
	private PositionDTO position;	
	
	
}
