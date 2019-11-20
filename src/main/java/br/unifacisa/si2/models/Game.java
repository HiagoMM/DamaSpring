package br.unifacisa.si2.models;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.unifacisa.si2.models.exceptions.SizeBoardException;
import lombok.Data;

@Data
@Document(collection = "Game")
public class Game {
	public static final List<Integer> BOARD_SIZE = Arrays.asList(8, 10, 12);

	@Id
	private String id;

	private Board board;

	private Player player1;

	private Player player2;

	private int p1Counter;

	private int p2Counter;

	private Player currentPlayer;

	private boolean status;

	private Integer size;

	public Game(Piece[][] board) {
		this.board.setBoard(board);
	}
	
	public Game() {}

	public Game(Player pOne, Player pTwo, Integer size) throws SizeBoardException {
		this.player1 = pOne;
		this.player2 = pTwo;
		this.size = size;
		this.currentPlayer = pOne;
		BoardContext bc = new BoardContext();

		switch (size) {
		case 8:
			bc.set8x8();
			break;
		case 10:
			bc.set10x10();
			break;
		case 12:
			bc.set12x12();
			break;
		default:
			throw new SizeBoardException("Tamanho não aceito tente 8, 10 ou 12");
		}
		board = bc.getBoard();
	}

	public void reversePlayer() {
		if (currentPlayer.equals(player1)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	@Override
	public String toString() {
		return "Board [table=" + Arrays.toString(board.getBoard()) + ", status=" + status + "]";
	}

}