package br.unifacisa.si2.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PieceComun  extends Piece {
	
	private TipoPlayer tipo;
	
	@Override
	public String toString() {
		if (tipo == TipoPlayer.PLAYER1) {
			return "Player 1";
		} else {
			return "Player 2";
		}

	}
	
}
