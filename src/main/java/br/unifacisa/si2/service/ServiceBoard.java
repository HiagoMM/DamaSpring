package br.unifacisa.si2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.PlayersDTO;
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.models.TypePlayer;
import br.unifacisa.si2.repository.BoardRepository;
import br.unifacisa.si2.service.exception.NotFoundBoardException;

@Service
public class ServiceBoard {
	
	@Autowired
	private BoardRepository repository;

	public Board createBoard(PlayersDTO players) {
		players.getPlayer1().setType(TypePlayer.PLAYER1);
		players.getPlayer2().setType(TypePlayer.PLAYER2);
		return new Board(players.getPlayer1(), players.getPlayer2());
	}

	public Board saveBoard(Board board) {
		return repository.insert(board);
	}

	public List<Board> getBoards() {
		return repository.findAll();
	}

	public Board getBoardsById(String id) throws NotFoundBoardException {
		return repository.findById(id).orElseThrow(() -> new NotFoundBoardException("Jogo de Id: " + id + " não encontrado"));
	}
	
}
