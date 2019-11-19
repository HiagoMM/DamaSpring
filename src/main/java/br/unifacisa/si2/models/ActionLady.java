package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public class ActionLady implements Action {

	@Override
	public List<PositionDTO> prevision(Game board, PositionDTO begin) throws InvalidPieceException {
		Piece[][] matriz = board.getBoard().getBoard();

		Piece p1 = matriz[begin.getPositionX()][begin.getPositionY()];
		if (p1 == null) {
			throw new InvalidPieceException("Piece not found!");
		}
		Integer posX = begin.getPositionX();
		Integer posY = begin.getPositionY();

		return getPosition(matriz, posX, posY);
	}

	public List<PositionDTO> getPosition(Piece[][] matriz, Integer posX, Integer posY) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		int posXrightDiagonal = posX;
		int posYrightDiagonal = posY;
		int posXleftDiagonal = posX;
		int posYleftDiagonal = posY;
		int posXrightBackDiagonal = posX;
		int posYrightBackDiagonal = posY;
		int posXleftBackDiagonal = posX;
		int posYleftBackDiagonal = posY;

		Boolean rightDiagonalControl = true;
		Boolean leftDiagonalControl = true;
		Boolean rightBackDiagonalControl = true;
		Boolean leftBackDiagonalControl = true;

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
				}
			} else {
				leftBackDiagonalControl = false;
			}

		}

		return list;

	}

}
