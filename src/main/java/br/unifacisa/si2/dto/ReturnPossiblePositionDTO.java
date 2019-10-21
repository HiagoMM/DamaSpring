package br.unifacisa.si2.dto;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.models.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnPossiblePositionDTO {
	
	private Board board;
	
	private List<PositionDTO> position;
	
}
