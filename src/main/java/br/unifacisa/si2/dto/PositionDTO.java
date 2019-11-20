package br.unifacisa.si2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionDTO {
	
	private int positionY;
	
	private int positionX;
	
	private PositionDTO eat;

	public PositionDTO(int positionY, int positionX) {
		this.positionY = positionY;
		this.positionX = positionX;
	}
	
	
}
