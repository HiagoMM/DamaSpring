package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public class ActionLady implements Action {
	public static final int[] TOP_LEFT = { -1, -1 };
	public static final int[] TOP_RIGHT = { -1, 1 };

	public static final int[] BOTTOM_LEFT = { 1, -1 };
	public static final int[] BOTTOM_RIGHT = { 1, 1 };

	@Override
	public List<PositionDTO> prevision(Game board, PositionDTO begin) throws InvalidPieceException {
		Piece[][] matriz = board.getBoard().getBoard();

		Piece p1 = matriz[begin.getPositionY()][begin.getPositionX()];
		if (p1 == null) {
			throw new InvalidPieceException("Peça não existe");
		}
		Integer posX = begin.getPositionX();
		Integer posY = begin.getPositionY();

		return getPosition(matriz, posX, posY, board.getCurrentPlayer());
	}

	public List<PositionDTO> getPosition(Piece[][] matriz, Integer posX, Integer posY, Player player) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();

		int[] rightDiagonalControl = { posY, posX };
		int[] leftDiagonalControl = { posY, posX };
		int[] rightBackDiagonalControl = { posY, posX };
		int[] leftBackDiagonalControl = { posY, posX };

<<<<<<< HEAD
		while (rightBackDiagonalControl || rightDiagonalControl || leftDiagonalControl || leftBackDiagonalControl) {
			if (posXrightDiagonal < 8 && posYrightDiagonal >= 0 && rightBackDiagonalControl) {
				if (matriz[posYrightDiagonal++][posXrightDiagonal--] == null) {
					list.add(new PositionDTO(posYrightDiagonal, posXrightDiagonal));
				}
			} else {
				rightDiagonalControl = false;
			}
			if (posXleftDiagonal >= 0 && posYleftDiagonal >= 0 && leftDiagonalControl) {
				if (matriz[posYleftDiagonal--][posXleftDiagonal--] == null) {
					list.add(new PositionDTO(posYleftDiagonal,posXleftDiagonal ));
				}
			} else {
				leftDiagonalControl = false;
			}
			if (posXrightBackDiagonal >= 0 && posYrightBackDiagonal < 8 && rightBackDiagonalControl) {
				if (matriz[posYrightBackDiagonal--][posXrightBackDiagonal] == null) {
					list.add(new PositionDTO(posYrightBackDiagonal, posXrightBackDiagonal));
				}
			} else {
				rightBackDiagonalControl = false;
			}
			if (posXleftBackDiagonal < 8 && posYleftBackDiagonal < 8 && leftBackDiagonalControl) {
				if (matriz[posYleftBackDiagonal++][posXleftBackDiagonal++] == null) {
					list.add(new PositionDTO(posYleftBackDiagonal, posXleftBackDiagonal));
=======
		while (rightBackDiagonalControl.length > 0 || rightDiagonalControl.length > 0 || leftDiagonalControl.length > 0
				|| leftBackDiagonalControl.length > 0) {
			rightDiagonalControl = checkDiagonal(player, rightDiagonalControl, TOP_RIGHT, matriz, list);
			rightBackDiagonalControl = checkDiagonal(player, rightBackDiagonalControl, BOTTOM_RIGHT, matriz, list);
			leftDiagonalControl = checkDiagonal(player, leftDiagonalControl, TOP_LEFT, matriz, list);
			leftBackDiagonalControl = checkDiagonal(player, leftBackDiagonalControl, BOTTOM_LEFT, matriz, list);
			System.out.println(rightBackDiagonalControl.length);
			System.out.println(rightDiagonalControl.length);
			System.out.println(leftDiagonalControl.length);
			System.out.println(leftBackDiagonalControl.length);
		}
		return list;

	}

	private int[] checkDiagonal(Player player, int[] initial, int[] pos, Piece[][] matriz, List<PositionDTO> list) {
		if (initial.length > 0) {
			int posY = initial[0];
			int posX = initial[1];
			int[] vars = new int[2];
			posY += pos[0];
			posX += pos[1];
			if (inBoard(posX, posY)) {
				if (matriz[posY][posX] == null) {
					list.add(new PositionDTO(posY, posX));
					vars[0] = posY;
					vars[1] = posX;
				} else {
					Piece p = matriz[posY][posX];
					posY += pos[0];
					posX += pos[1];
					if (p.getType() == getOpponent(player)) {
						if (inBoard(posX, posY) && matriz[posY][posX] == null) {
							list.add(new PositionDTO(posY, posX));
							vars = new int[0];
						}
					}
					vars = new int[0];
>>>>>>> 221a65cd56ed8f8b3bed9b783e72cc4682dee083
				}
			}
			return vars;
		}
		return initial;
	}

	private TypePlayer getOpponent(Player player) {
		return player.getType() == TypePlayer.PLAYER1 ? TypePlayer.PLAYER2 : TypePlayer.PLAYER1;
	}

	private boolean inBoard(int posX, int posY) {
		return posX >= 0 && posY < 8 && posY >= 0 && posX < 8;
	}

}
