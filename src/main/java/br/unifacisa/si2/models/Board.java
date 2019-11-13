package br.unifacisa.si2.models;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Document(collection = "board")
public class Board {
	public static final int BOARD_SIZE = 8;
	
	@Id
	private String id;
	
	private Piece[][] table = new Piece[BOARD_SIZE][BOARD_SIZE];
	
	private Player player1;
	
	private Player player2;
	
	private Player currentPlayer;
	
	private boolean status;
	
	public Board(Piece[][] board) {
		this.table = board;
	}
	
	public Board(Player pOne, Player pTwo) {
		this.player1 = pOne;
		this.player2 = pTwo;
		
		for (int i = 0; i < table.length; i++) {
			if( i  <= 2 ) {
				table[i] = createLine(i,TypePlayer.PLAYER2);
			}
			if (i >= 5) {
				table[i] = createLine(i,TypePlayer.PLAYER1);
			}
		}
		currentPlayer = pOne;
	}	
	
	private Piece[] createLine(int index,TypePlayer player) {
		
		Piece[] line = new Piece[8];
		for (int i = 0; i < line.length; i++) {
			if (index % 2 == 0) {
				line[i] = i % 2 == 0 ? new Piece(player,false) : null; 
			} else {
				line[i] = i % 2 == 0 ? null : new Piece(player,false); 
			}
		}
		return line;
		
	}
	
	public static void main(String[] args) {
		Board board = new Board(new Player("Hiago", TypePlayer.PLAYER1),new Player("pedrocas",TypePlayer.PLAYER2));
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
	
	private Piece createPiece() {
		return new Piece();
	}

	
	@Override
	public String toString() {
		return "Board [table=" + Arrays.toString(table) + ", status=" + status + "]";
	}
	
}
