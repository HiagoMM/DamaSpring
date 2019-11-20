package br.unifacisa.si2.models;

import lombok.Data;

@Data
public class Board {

	private Piece[][] table = new Piece[getSize()][getSize()];

	public Board() {
		
		for (int i = 0; i < table.length; i++) {
			if (i <= (getSize()/2) -2 ) {
				table[i] = createLine(i,TypePlayer.PLAYER2);
			}
			if (i >= (getSize()/2) +1) {
				table[i] = createLine(i,TypePlayer.PLAYER1);
			}
		}
	}
	 
	private Piece[] createLine(int index,TypePlayer player) {
		
		Piece[] line = new Piece[getSize()];
		for (int i = 0; i < line.length; i++) {
			if (index % 2 == 0) {
				line[i] = i % 2 == 0 ? createPiece(player) : null; 
			} else {
				line[i] = i % 2 == 0 ? null : createPiece(player); 
			}
		}
		return line;
		
	}
	
	private Piece createPiece(TypePlayer player) {
		return new Piece(player, false);
	}
	
	public Integer getSize() {
		return 8;
	}
	
}	
