package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public class ActionCommon implements Action {

	public static final int[] TOP_LEFT = { -1, -1 };
	public static final int[] TOP_RIGHT = { -1, 1 };

	public static final int[] BOTTOM_LEFT = { 1, -1 };
	public static final int[] BOTTOM_RIGHT = { 1, 1 };

	public static final int[][] ALL_SIDES = { BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT, TOP_LEFT };

	public List<PositionDTO> prevision(Game board, PositionDTO begin) throws InvalidPieceException {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Piece[][] matriz = board.getBoard().getTable();

		Piece p1 = matriz[begin.getPositionY()][begin.getPositionX()];
		if (p1 == null) {
			throw new InvalidPieceException("Peça não existe");
		}
		Integer posX = begin.getPositionX();
		Integer posY = begin.getPositionY();

		if (p1.getType() == board.getCurrentPlayer().getType()) {
			if (board.getCurrentPlayer().getType() == TypePlayer.PLAYER1) {
				list = getPlayer1(posX, posY, matriz);
			} else {
				list = getPlayer2(posX, posY, matriz);
			}
		} else {
			throw new InvalidPieceException("Esta peça não é sua");
		}
		return list;
	}

	private List<PositionDTO> getPlayer1(Integer posX, Integer posY, Piece[][] matriz) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();

		checkPlay(posX, posY, matriz, list, TOP_RIGHT, TypePlayer.PLAYER2);
		checkPlay(posX, posY, matriz, list, TOP_LEFT, TypePlayer.PLAYER2);

		return list;
	}

	private List<PositionDTO> getPlayer2(Integer posX, Integer posY, Piece[][] matriz) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();

		checkPlay(posX, posY, matriz, list, BOTTOM_RIGHT, TypePlayer.PLAYER1);
		checkPlay(posX, posY, matriz, list, BOTTOM_LEFT, TypePlayer.PLAYER1);

		return list;
	}

	private void checkPlay(int posX, int posY, Piece[][] matriz, List<PositionDTO> list, int[] pos, TypePlayer player) {
		int anchorPosX = posX;
		int anchorPosY = posY;
		posX += pos[1];
		posY += pos[0];
		if (inBoard(posX, posY,matriz)) {
			Piece piece = matriz[posY][posX];
			if (piece == null) {
				list.add(new PositionDTO(posY, posX));
			}
		}
		checkEat(anchorPosX, anchorPosY, matriz, list, ALL_SIDES, player, null);

	}

	private void checkEat(int posX, int posY, Piece[][] matriz, List<PositionDTO> list, int[][] sides,
			TypePlayer player,PositionDTO eatablePosPrev) {
		int anchorPosX = posX;
		int anchorPosY = posY;
	
		for (int[] side : sides) {
			posY += side[0];
			posX += side[1];
			if (inBoard(posX,posY,matriz)) {
				Piece piece = matriz[posY][posX];
				if (piece != null && piece.getType() == player) {
					PositionDTO eat;
					if (eatablePosPrev != null ) {
						eat = new PositionDTO(posY, posX,eatablePosPrev.getEat());
					} else {
						eat = new PositionDTO(posY, posX);
					}
					posY += side[0];
					posX += side[1];
					if (inBoard(posX, posY,matriz)) {
						piece = matriz[posY][posX];
						if (piece == null) {
							PositionDTO eatablePos = new PositionDTO(posY, posX, eat);
							list.add(eatablePos);
							
							checkEat(posX, posY, matriz, list, player, side.clone(), eatablePos);
						}
					}
				}
			}
			posX = anchorPosX;
			posY = anchorPosY;
		}
	}

	private void checkEat(int posX, int posY, Piece[][] matriz, List<PositionDTO> list, 
			TypePlayer player, int[] exclude,PositionDTO eatablePosPrev) {
		int[][] newSides = new int[3][2];
		int cont = 0;
		exclude[0] = exclude[0] * -1;
		exclude[1] = exclude[1] * -1;
		for (int[] side : ALL_SIDES.clone()) {
			if (!(side[0] == exclude[0] && side[1] == exclude[1])) {
				newSides[cont] = side;
				cont++;
			}	
		}
		checkEat(posX, posY, matriz, list, newSides, player, eatablePosPrev);
		
	}

	private boolean inBoard(int posX, int posY, Piece[][] matriz) {
		return posX >= 0 && posY < matriz.length && posY >= 0 && posX < matriz.length;
	}

}