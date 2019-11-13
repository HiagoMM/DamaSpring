package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public class ActionLady implements Action {

	@Override
	public List<PositionDTO> prevision(Board board, PositionDTO begin) throws InvalidPieceException {
		Piece[][] matriz = board.getTable();

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
				if (matriz[posXrightDiagonal++][posYrightDiagonal--] == null) {
					list.add(new PositionDTO(posXrightDiagonal, posYrightDiagonal));
				}
			} else {
				rightDiagonalControl = false;
			}
			if (posXleftDiagonal >= 0 && posYleftDiagonal >= 0 && leftDiagonalControl) {
				if (matriz[posXleftDiagonal--][posYleftDiagonal--] == null) {
					list.add(new PositionDTO(posXleftDiagonal, posYleftDiagonal));
				}
			} else {
				leftDiagonalControl = false;
			}
			if (posXrightBackDiagonal >= 0 && posYrightBackDiagonal < 8 && rightBackDiagonalControl) {
				if (matriz[posXrightBackDiagonal--][posYrightBackDiagonal] == null) {
					list.add(new PositionDTO(posXrightBackDiagonal, posYrightBackDiagonal));
				}
			} else {
				rightBackDiagonalControl = false;
			}
			if (posXleftBackDiagonal < 8 && posYleftBackDiagonal < 8 && leftBackDiagonalControl) {
				if (matriz[posXleftBackDiagonal++][posYleftBackDiagonal++] == null) {
					list.add(new PositionDTO(posXleftBackDiagonal, posYleftBackDiagonal));
				}
			} else {
				leftBackDiagonalControl = false;
			}

		}

		return list;

	}

}
