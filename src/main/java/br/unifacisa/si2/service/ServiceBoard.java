package br.unifacisa.si2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.PlayersDTO;
import br.unifacisa.si2.models.Board;

@Service
public class ServiceBoard {


	public Board createBoard(PlayersDTO players) {
		return new Board(players.getPlayer1(), players.getPlayer2());
	}

	public Object saveBoard(Board board) {
		//TODO IMPLEMENTAR CONEÇãO COM O BANCO MONGO
		return null;
	}

	public List<Board> getBoard() {
		//TODO IMPLEMENTAR CONEÇãO COM O BANCO MONGO
		return null;
	}
	
}
