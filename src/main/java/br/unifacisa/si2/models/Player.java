package br.unifacisa.si2.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
	
	private String name;
	
	private TipoPlayer tipo;
	
}
