package br.unifacisa.si2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.PlayersDTO;
import br.unifacisa.si2.models.Game;
import br.unifacisa.si2.models.TypePlayer;
import br.unifacisa.si2.models.exceptions.SizeBoardException;
import br.unifacisa.si2.repository.GameRepository;
import br.unifacisa.si2.service.exception.NotFoundBoardException;

@Service
public class ServiceBoard {
	
	@Autowired
	private GameRepository repository;

	public Game createBoard(PlayersDTO players) throws SizeBoardException {
		players.getPlayer1().setType(TypePlayer.PLAYER1);
		players.getPlayer2().setType(TypePlayer.PLAYER2);
		return repository.insert(new Game(players.getPlayer1(), players.getPlayer2(),players.getSize()));
	}

	public Game saveBoard(Game board) {
		return repository.save(board);
	}

	public List<Game> getBoards() {
		return repository.findAll();
	}

	public Game getBoardsById(String id) throws NotFoundBoardException {
		return repository.findById(id).orElseThrow(() -> new NotFoundBoardException("Jogo de Id: " + id + " não encontrado"));
	}
	
}
