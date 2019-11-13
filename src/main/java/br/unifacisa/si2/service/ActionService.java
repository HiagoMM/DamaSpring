package br.unifacisa.si2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.MovPecaDTO;
import br.unifacisa.si2.dto.PositionAndBoardDTO;
import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.dto.ReturnPossiblePositionDTO;
import br.unifacisa.si2.models.Action;
import br.unifacisa.si2.models.ActionCommon;
import br.unifacisa.si2.models.ActionLady;
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.models.Piece;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;
import br.unifacisa.si2.service.exception.InvalidPositionException;

@Service
public class ActionService {
	

	public ReturnPossiblePositionDTO getPossiblePosition(PositionAndBoardDTO position) throws InvalidPieceException {
		Board board = position.getBoard();
		PositionDTO begin = position.getPosition();
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Piece[][] table = board.getTable();
		
		int posx = begin.getPositionX();
		int posy = begin.getPositionY();
		
		if (table[posx][posx] != null) {

			if (table[posx][posy].isDama()) {
				list = new ActionLady().prevision(board, begin);
			} else {
				list = new ActionCommon().prevision(board, begin);
			}

			return new ReturnPossiblePositionDTO(board, list);
		}
		throw new InvalidPieceException("Peça não existe");

	}

	public Board movPiece(MovPecaDTO posBoard) throws InvalidPositionException {
		Board board = posBoard.getBoard();

		if (posBoard.getList().contains(posBoard.getEnd())) {
			//TODO IMPLEMENTACAO DE COMER A PEÇA
			return board;
		} else {
			throw new InvalidPositionException("Posicão Invalida!");
		}
	}

}
