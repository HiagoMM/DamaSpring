package br.unifacisa.si2.dto;

import br.unifacisa.si2.models.Player;
import lombok.Data;

@Data
public class PlayersDTO {
	
	private Player player1;
	
	private Player player2;
	
	private Integer size;
	
}
