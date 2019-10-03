package br.unifacisa.si2.models;

import java.util.Arrays;

import lombok.Data;

@Data
public class Board {
	public static final int BOARD_SIZE = 8;
	
	private Piece[][] table = new Piece[BOARD_SIZE][BOARD_SIZE];
	private boolean status;
	
	public Board(Player pOne, Player pTwo) {
		for (int i = 0; i < table.length; i++) {
			if( i  <= 2 ) {
				table[i] = createLine(pOne.getPieceColor(),i);
			}
			if (i >= 5) {
				table[i] = createLine(pTwo.getPieceColor(),i);
			}
		}
	}	
	
	private Piece[] createLine(ColorEnum color, int index) {
		Piece[] line = new Piece[8];
		for (int i = 0; i < line.length; i++) {
			if (index % 2 == 0) {
				line[i] = i % 2 == 0 ? new Piece(color) : null; 
			} else {
				line[i] = i % 2 == 0 ? null : new Piece(color); 
			}
		}
		return line;
	}
	
	public static void main(String[] args) {
		Board board = new Board(new Player("Hiago", ColorEnum.RED),new Player("pedrocas", ColorEnum.BLUE));
		for (Piece[] l : board.getTable()) {
			for (Piece p : l) {
				if ( p != null) {
					System.out.print(p + " ");
				} else {
					System.out.print("      ");
				}
			}
			System.out.println();
		}
	}

	
	@Override
	public String toString() {
		return "Board [table=" + Arrays.toString(table) + ", status=" + status + "]";
	}
	
}
