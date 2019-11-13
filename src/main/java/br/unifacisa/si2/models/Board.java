package br.unifacisa.si2.models;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.qos.logback.core.subst.Token.Type;
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
	
	private Integer tamanho;
	
	public Board(Piece[][] board) {
		this.table = board;
	}
	
	public Board(Player pOne, Player pTwo) {
		this.player1 = pOne;
		this.player2 = pTwo;
		
		for (int i = 0; i < table.length; i++) {
			if (i <= 2) {
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
				line[i] = i % 2 == 0 ? createPiece(player) : null; 
			} else {
				line[i] = i % 2 == 0 ? null : createPiece(player); 
			}
		}
		return line;
		
	}
	
	public void reversePlayer() {
		if(currentPlayer.equals(player1)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}
	
	private Piece createPiece(TypePlayer player) {
		return new Piece(player, false);
	}

	
	@Override
	public String toString() {
		return "Board [table=" + Arrays.toString(table) + ", status=" + status + "]";
	}
	
}
