package br.unifacisa.si2.models;

import lombok.Data;

@Data
public abstract class Board {

	private Piece[][] board = new Piece[getSize()][getSize()];

	public Board() {
		
		for (int i = 0; i < board.length; i++) {
			if (i <= 2) {
				board[i] = createLine(i,TypePlayer.PLAYER2);
			}
			if (i >= 5) {
				board[i] = createLine(i,TypePlayer.PLAYER1);
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
	
	
	
	public abstract Integer getSize();
	
}	
