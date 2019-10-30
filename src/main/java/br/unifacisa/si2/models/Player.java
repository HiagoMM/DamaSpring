package br.unifacisa.si2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	
	private String name;
	
	private TypePlayer type;
	
}
