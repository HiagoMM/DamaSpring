package br.unifacisa.si2.models;

import java.util.Arrays;

import lombok.Data;

@Data
public class Board {
	public static final int BOARD_SIZE = 8;
	
	private Piece[][] table = new Piece[BOARD_SIZE][BOARD_SIZE];
	
	private Player player1;
	
	private Player player2;
	
	private Player currentPlayer;
	
	private boolean status;
	
	public Board(Piece[][] board) {
		this.table = board;
		
	}
	
	public Board(Player pOne, Player pTwo) {
		for (int i = 0; i < table.length; i++) {
			if( i  <= 2 ) {
				table[i] = createLine(i);
			}
			if (i >= 5) {
				table[i] = createLine(i);
			}
		}
		currentPlayer = pOne;
	}	
	
	private PieceComun[] createLine(int index) {
		
		PieceComun[] line = new PieceComun[8];
		for (int i = 0; i < line.length; i++) {
			if (index % 2 == 0) {
				line[i] = i % 2 == 0 ? new PieceComun(TipoPlayer.PLAYER2) : null; 
			} else {
				line[i] = i % 2 == 0 ? null : new PieceComun(TipoPlayer.PLAYER1); 
			}
		}
		return line;
		
	}
	
	public static void main(String[] args) {
		Board board = new Board(new Player("Hiago", TipoPlayer.PLAYER1),new Player("pedrocas",TipoPlayer.PLAYER2));
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
	
	public void reversePlayer() {
		if(currentPlayer.equals(player1)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	
	@Override
	public String toString() {
		return "Board [table=" + Arrays.toString(table) + ", status=" + status + "]";
	}
	
}
