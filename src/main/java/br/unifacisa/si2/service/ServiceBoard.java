package br.unifacisa.si2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.PlayersDTO;
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.models.TypePlayer;

@Service
public class ServiceBoard {


	public Board createBoard(PlayersDTO players) {
		players.getPlayer1().setType(TypePlayer.PLAYER1);
		players.getPlayer2().setType(TypePlayer.PLAYER2);
		return new Board(players.getPlayer1(), players.getPlayer2());
	}

	public Object saveBoard(Board board) {
		//TODO IMPLEMENTAR CONEÇãO COM O BANCO MONGO
		return null;
	}

	public List<Board> getBoards() {
		//TODO IMPLEMENTAR CONEÇãO COM O BANCO MONGO
		return null;
	}
	
}
