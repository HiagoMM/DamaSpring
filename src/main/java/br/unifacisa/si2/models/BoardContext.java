package br.unifacisa.si2.models;

import br.unifacisa.si2.models.sizeBoard.Board10x10;
import br.unifacisa.si2.models.sizeBoard.Board12x12;
import br.unifacisa.si2.models.sizeBoard.Board8x8;

public class BoardContext {

	public Board board;
	
	public void set8x8() {
		board = new Board8x8();
	}
	
	public void set10x10() {
		board = new Board10x10();
	}
	
	public void set12x12() {
		board = new Board12x12();
	}
	
	public Board getBoard() {
		return board;
	}
}
