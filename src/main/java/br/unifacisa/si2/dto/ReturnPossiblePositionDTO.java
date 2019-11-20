package br.unifacisa.si2.dto;

import java.util.List;

import br.unifacisa.si2.models.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnPossiblePositionDTO {
	
	private Game board;
	
	private List<PositionDTO> position;
	
}
