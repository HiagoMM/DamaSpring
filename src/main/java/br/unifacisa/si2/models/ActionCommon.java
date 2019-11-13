package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public class ActionCommon implements Action {

	public List<PositionDTO> prevision(Board board, PositionDTO begin) throws InvalidPieceException {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Piece[][] matriz = board.getTable();

		Piece p1 = matriz[begin.getPositionX()][begin.getPositionY()];
		if (p1 == null) {
			throw new InvalidPieceException("Piece not found!");
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
			throw new InvalidPieceException("Piece is not yours");
		}
		return list;
	}
	
	// PLAYER 2
	private List<PositionDTO> getPlayer1(Integer posX, Integer posY, Piece[][] matriz) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		int posXright = posX;
		int posYright = posY;
		int posXleft = posX;
		int posYleft = posY;
		
		posXright++;
		posYright--;
		posXleft--;
		posYleft--;
		
		if (posXright < 8 && posYright >= 0) {
			Piece right = matriz[posXright][posYright];
			if (right != null) {
				posXright++;
				posYright--;
				if (posXright < 8 && posYright >= 0) {
					right = matriz[posXright][posYright];
					if (right != null) {
						list.add(new PositionDTO(posXright, posYright));
					}
				}
			} else {
				list.add(new PositionDTO(posXright, posYright));
			}
		}
		
		if (posXleft >= 0 && posYleft >= 0) {
			Piece left = matriz[posXleft][posYright];
			if (left != null) {
				posXleft--;
				posYleft--;
				if (posXleft >= 0 && posYleft >= 0) {
					left = matriz[posXleft][posYleft];
					if (left != null) {
						list.add(new PositionDTO(posXleft, posYleft));
					}
				}
			} else {
				list.add(new PositionDTO(posXleft, posYleft));
			}
		}
		return list;
	}
	
	// PLAYER 2
	private List<PositionDTO> getPlayer2(Integer posX, Integer posY, Piece[][] matriz) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		int posXright = posX;
		int posYright = posY;
		int posXleft = posX;
		int posYleft = posY;
		
		posXright--;
		posYright++;
		posXleft++;
		posYleft++;
		
		if (posXright >= 0 && posYright < 8) {
			Piece right = matriz[posXright][posYright];
			if (right != null) {
				posXright++;
				posYright--;
				if (posXright >= 0 && posYright < 8) {
					right = matriz[posXright][posYright];
					if (right != null) {
						list.add(new PositionDTO(posXright, posYright));
					}
				}
			} else {
				list.add(new PositionDTO(posXright, posYright));
			}
		}
		
		if (posXleft < 8 && posYleft < 8) {
			Piece left = matriz[posXleft][posYright];
			if (left != null) {
				posXleft--;
				posYleft--;
				if (posXleft < 8 && posYleft < 8) {
					left = matriz[posXleft][posYleft];
					if (left != null) {
						list.add(new PositionDTO(posXleft, posYleft));
					}
				}
			} else {
				list.add(new PositionDTO(posXleft, posYleft));
			}
		}
		return list;
	}

}