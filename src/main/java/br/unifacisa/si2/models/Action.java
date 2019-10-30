package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public class Action {

	public static List<PositionDTO> getActionComum(Board board, PositionDTO begin) throws InvalidPieceException {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Piece[][] matriz = board.getTable();

		Piece p1 = matriz[begin.getPositionX()][begin.getPositionY()];
		if (p1 == null) {
			throw new InvalidPieceException("Piece not found!");
		}
		Integer posX = begin.getPositionX();
		Integer posY = begin.getPositionY();

		if (p1.getType() == board.getCurrentPlayer().getType()) {
			PositionDTO right = getRight(posX, posY, matriz);
			PositionDTO left = getLeft(posX, posY, matriz);
			if (right != null) {
				list.add(right);
			}
			if (left != null) {
				list.add(left);
			}
		} else {
			PositionDTO left = getRightPlayer2(posX, posY, matriz);
			PositionDTO right = getLeftPlayer2(posX, posY, matriz);
			if (right != null) {
				list.add(right);
			}
			if (left != null) {
				list.add(left);
			}
		}
		return list;
	}
	// METODO PARA PLAYER 1 IR PARA DIREITA ----------------------------------------------
	private static PositionDTO getRight(Integer posX, Integer posY, Piece[][] matriz) {
		posX++;
		posY--;
		
		if (posX < 7 && posY >= 0) {
			Piece right = matriz[posX][posY];
			if (right != null) {
				posX += 2;
				posY -= 2;
				if (posX > 7 && posY > 0) {
					Piece rightNext = matriz[posX][posY];
					if (rightNext != null) {
						return new PositionDTO(posX, posY);
					}
				}
			} else {
				return new PositionDTO(posX, posY);
			}
		}
		return null;
	}
	
	// METODO PARA PLAYER 1 IR PARA ESQUERDA ----------------------------------------------
	private static PositionDTO getLeft(Integer posX, Integer posY, Piece[][] matriz) {
		// Direction left
		posX--;
		posY--;
		if (posX >= 0 && posY >= 0) {
			Piece left = matriz[posX][posY];
			if (left != null) {
				posX -= 2;
				posY -= 2;
				if (posX >= 0 || posY >= 0) {
					Piece leftNext = matriz[posX][posY];
					if (leftNext != null) {
						return new PositionDTO(posX -= 2, posY -= 2);
					}
				}
			} else {
				return new PositionDTO(posX, posY);
			}
		} 
		return null;
	}
	
	// METODO PARA PLAYER 2 IR PARA ESQUERDA ----------------------------------------------
	private static PositionDTO getLeftPlayer2(Integer posX, Integer posY, Piece[][] matriz) {
		// Direction left
		posX++;
		posY++;
		if (posX < 8 && posY < 8) {
			Piece left = matriz[posX][posY];
			if (left != null) {
				posX += 2;
				posY += 2;
				if (posX < 8 || posY < 8) {
					Piece leftNext = matriz[posX][posY];
					if (leftNext != null) {
						return new PositionDTO(posX, posY);
					}
				}
			} else {
				return new PositionDTO(posX, posY);
			}
		} 
		return null;
	}
	
	// METODO PARA PLAYER 2 IR PARA DIREITA ----------------------------------------------
	private static PositionDTO getRightPlayer2(Integer posX, Integer posY, Piece[][] matriz) {
		// Direction left
		posX--;
		posY++;
		if (posX >= 0 && posY < 8) {
			Piece left = matriz[posX][posY];
			if (left != null) {
				posX -= 2;
				posY += 2;
				if (posX >= 0 || posY < 8) {
					Piece leftNext = matriz[posX][posY];
					if (leftNext != null) {
						return new PositionDTO(posX, posY);
					}
				}
			} else {
				return new PositionDTO(posX, posY);
			}
		} 
		return null;
	}
	
	public static List<PositionDTO> getActionLady(Board board, PositionDTO begin) throws InvalidPieceException {
		List<PositionDTO> list = new ArrayList<PositionDTO>();

		if (board.getTable()[begin.getPositionX()][begin.getPositionY()] == null) {
			throw new InvalidPieceException("Piece not Found!");
		}

		if (board.getCurrentPlayer().getType() == board.getTable()[begin.getPositionX()][begin.getPositionY()]
				.getType()) {

			List<PositionDTO> listPosFront = getFront(board.getTable(), begin);
			List<PositionDTO> listPosBack = getBack(board.getTable(), begin);
			
			list.addAll(listPosFront);
			list.addAll(listPosBack);

		}

		return list;
	}

	private static List<PositionDTO> getFront(Piece[][] board, PositionDTO begin) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Integer right = begin.getPositionX();
		Integer right2 = begin.getPositionY();
		Integer left = begin.getPositionX();
		Integer left2 = begin.getPositionY();
		boolean buscar = true;

		while (buscar) {

			if (board[right][right2] != null) {
				list.add(new PositionDTO(right, right2));
				if (right < 8) {
					right++;
				}
				if (right2 > 0) {
					right2--;
				}
			}

			if (board[left][left2] != null) {
				list.add(new PositionDTO(left, left2));
				if (left > 0) {
					left--;
				}
				if (left2 > 0) {
					left2--;
				}
			}

			if ((right > 7 || right2 > 7) && (left < 0 || left2 < 0)) {
				buscar = false;
			}

		}
		return list;
	}

	private static List<PositionDTO> getBack(Piece[][] board, PositionDTO begin) {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Integer right = begin.getPositionX();
		Integer right2 = begin.getPositionY();
		Integer left = begin.getPositionX();
		Integer left2 = begin.getPositionY();
		boolean buscar = true;

		while (buscar) {

			list.add(new PositionDTO(right, right2));
			if (right > 0) {
				right--;
			}
			if (right < 8) {
				right2++;
			}

			if (board[left][left2] != null) {
				list.add(new PositionDTO(left, left2));
				if (left < 8) {
					left++;
				}
				if (left2 < 8) {
					left2++;
				}
			}

			if (right == 0 && right2 == 8 && left == 8 && left2 == 8) {
				buscar = false;
			}

		}
		return list;
	}

}
