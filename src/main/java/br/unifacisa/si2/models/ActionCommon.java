package br.unifacisa.si2.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.NewProxyInstance;

import br.unifacisa.si2.dto.PositionAndBoardDTO;
import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.dto.ReturnPossiblePositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;
import br.unifacisa.si2.service.ActionService;
import net.bytebuddy.asm.Advice.Return;

public class ActionCommon implements Action {

	public List<PositionDTO> prevision(Board board, PositionDTO begin) throws InvalidPieceException {
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		Piece[][] matriz = board.getTable();

		Piece p1 = matriz[begin.getPositionY()][begin.getPositionX()];
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
			Piece right = matriz[posYright][posXright];
			if (right != null) {
				if (right.getType() == TypePlayer.PLAYER2) {
					posXright++;
					posYright--;
					if (posXright < 8 && posYright >= 0) {
						right = matriz[posYright][posXright];
						if (right != null) {
							list.add(new PositionDTO(posYright, posXright));
						}
					}
				}
			} else {
				list.add(new PositionDTO(posYright, posXright));
			}
		}
		
		if (posXleft >= 0 && posYleft >= 0) {
			Piece left = matriz[posYleft][posXleft];
			if (left != null) {
				if (left.getType() == TypePlayer.PLAYER2) {
					posXleft--;
					posYleft--;
					if (posXleft >= 0 && posYleft >= 0) {
						left = matriz[posYleft][posXleft];
						if (left != null) {
							list.add(new PositionDTO(posYleft, posXleft));
						}
					}
				}
			} else {
				list.add(new PositionDTO(posYleft, posXleft));
			}
		}
		return list;
	}
	
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
			Piece right = matriz[posYright][posXright];
			if (right != null) {
				if (right.getType() == TypePlayer.PLAYER1) {
					posXright--;
					posYright++;
					if (posXright >= 0 && posYright < 8) {
						right = matriz[posYright][posXright];
						if (right != null) {
							list.add(new PositionDTO(posYright, posXright));
						}
					}
				}
			} else {
				list.add(new PositionDTO(posYright, posXright));
			}
		}
		
		if (posXleft < 8 && posYleft < 8) {
			Piece left = matriz[posYright][posXleft];
			if (left != null) {
				if (left.getType() == TypePlayer.PLAYER1) {
					posXleft++;
					posYleft++;
					if (posXleft < 8 && posYleft < 8) {
						left = matriz[posYleft][posXleft];
						if (left != null) {
							list.add(new PositionDTO(posYleft, posXleft));
						}
					}
				}
			} else {
				list.add(new PositionDTO(posYleft, posXleft));
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws InvalidPieceException {
		
		Board board = new Board(new Player("James Marcos", TypePlayer.PLAYER1), new Player("Marcos James", TypePlayer.PLAYER2));
		board.setCurrentPlayer(new Player("Marcos James", TypePlayer.PLAYER2));
		ActionService s = new ActionService();
		
		ReturnPossiblePositionDTO  r = s.getPossiblePosition(new PositionAndBoardDTO(board, new PositionDTO(2, 2)));
		
		for (PositionDTO c : r.getPosition()) {
			System.out.println(c.getPositionX() + " " + c.getPositionY());
		}
		
		
		
		
	}

}