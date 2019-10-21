package br.unifacisa.si2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.MovPecaDTO;
import br.unifacisa.si2.dto.PositionAndBoardDTO;
import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.dto.ReturnPossiblePositionDTO;
import br.unifacisa.si2.models.Action;
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.models.PieceComun;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;
import br.unifacisa.si2.service.exception.InvalidPositionException;

@Service
public class ActionService {
	
	
	public ReturnPossiblePositionDTO getPossiblePosition(PositionAndBoardDTO position) throws InvalidPieceException {
		Board board = position.getBoard();
		PositionDTO begin = position.getPosition();
		List<PositionDTO> list = new ArrayList();
		
		if ((board.getTable()[begin.getPositionX()][begin.getPositionY()]) instanceof PieceComun) {
			list = Action.getActionComum(board, begin);
		} else {
			list = Action.getActionLady(board,begin);
		}
		
		return new ReturnPossiblePositionDTO(board,list);
		
	}

	public Board movPiece(MovPecaDTO posBoard) throws InvalidPositionException {
		Board board = posBoard.getBoard();
		
		if (posBoard.getList().contains(posBoard.getEnd())) {
			
			
			return board;
		} else {
			throw new InvalidPositionException("Posicão Invalida!");
		}
	}
	
}
