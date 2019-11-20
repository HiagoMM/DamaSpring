package br.unifacisa.si2.service;

import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.PlayersDTO;
import br.unifacisa.si2.models.Game;
import br.unifacisa.si2.models.TypePlayer;
import br.unifacisa.si2.models.exceptions.SizeBoardException;

@Service
public class ServiceBoard {
	

	public Game createBoard(PlayersDTO players) throws SizeBoardException {
		players.getPlayer1().setType(TypePlayer.PLAYER1);
		players.getPlayer2().setType(TypePlayer.PLAYER2);
		return new Game(players.getPlayer1(), players.getPlayer2(),players.getSize());
	}

}
