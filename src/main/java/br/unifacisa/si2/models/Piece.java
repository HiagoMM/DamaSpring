package br.unifacisa.si2.models;

import lombok.Data;

@Data
public class Piece {
	
	private boolean isLady;
	private ColorEnum color;
	
	public Piece(ColorEnum color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return  color.toString() ;
	}
	
	
}
