package br.unifacisa.si2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.unifacisa.si2.dto.MovPecaDTO;
import br.unifacisa.si2.dto.PositionAndBoardDTO;
import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.dto.ReturnPossiblePositionDTO;
import br.unifacisa.si2.models.ActionCommon;
import br.unifacisa.si2.models.ActionLady;
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.models.Piece;
import br.unifacisa.si2.models.TypePlayer;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

@Service
public class ActionService {

	public ReturnPossiblePositionDTO getPossiblePosition(PositionAndBoardDTO position) throws InvalidPieceException {
		Board board = position.getBoard();
		PositionDTO begin = position.getPosition();
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Piece[][] table = board.getTable();

		int posx = begin.getPositionX();
		int posy = begin.getPositionY();

		if (table[posy][posx] != null) {

			if (table[posy][posx].isDama()) {
				list = new ActionLady().prevision(board, begin);
			} else {
				list = new ActionCommon().prevision(board, begin);
			}

			return new ReturnPossiblePositionDTO(board, list);
		}
		throw new InvalidPieceException("Peça não existe");

	}

	public Board movPiece(MovPecaDTO posBoard) throws InvalidPieceException {
		Board board = posBoard.getPositionAndBoardDTO().getBoard();
		Piece[][] table = board.getTable();
		List<PositionDTO> predictions = getPossiblePosition(posBoard.getPositionAndBoardDTO()).getPosition();
		PositionDTO begin = posBoard.getPositionAndBoardDTO().getPosition();
		PositionDTO end = posBoard.getEnd();

		predictions = predictions.stream()
				.filter(pre -> pre.getPositionX() == end.getPositionX() && pre.getPositionY() == end.getPositionY())
				.collect(Collectors.toList());

		PositionDTO eat = predictions.size() > 0 ? predictions.get(0).getEat() : null;

		if (predictions.size() > 0) {
			table[end.getPositionY()][end.getPositionX()] = table[begin.getPositionY()][begin.getPositionX()];
			table[begin.getPositionY()][begin.getPositionX()] = null;
			eat(table, board, eat);
			setDama(table, board, end);
			board = swapPlayer(board);
		}
		return board;
	}

	private void setDama(Piece[][] table, Board board, PositionDTO end) {
		if (board.getCurrentPlayer().getType() == TypePlayer.PLAYER1) {
			if (end.getPositionY() == 0) {
				table[end.getPositionY()][end.getPositionX()].setDama(true);
			}
		} else {
			if (end.getPositionY() == 7) {
				table[end.getPositionY()][end.getPositionX()].setDama(true);
			}
		}
	}

	private Board eat(Piece[][] table, Board board, PositionDTO eat) {
		while (eat != null) {
			Piece eatPiece = table[eat.getPositionY()][eat.getPositionX()];
			board = incCounter(board, eatPiece);
			table[eat.getPositionY()][eat.getPositionX()] = null;
			eat = eat.getEat();
		}
		return board;
	}

	private Board incCounter(Board board, Piece piece) {
		if (piece.getType().equals(TypePlayer.PLAYER1)) {
			board.setP1Counter(board.getP1Counter() + 1);
		} else {
			board.setP2Counter(board.getP2Counter() + 1);
		}
		return board;
	}

	private Board swapPlayer(Board board) {
		if (board.getCurrentPlayer().equals(board.getPlayer1())) {
			board.setCurrentPlayer(board.getPlayer2());
		} else {
			board.setCurrentPlayer(board.getPlayer1());
		}
		return board;
	}

}
