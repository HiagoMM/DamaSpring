package br.unifacisa.si2.models.sizeBoard;

import br.unifacisa.si2.models.Board;
import lombok.Data;

@Data
public class Board8x8 extends Board {
	
	public static final Integer SIZE = 8;
	
	public Integer getSize() {
		return SIZE;
	}
	
	public Board8x8() {}
	
}
