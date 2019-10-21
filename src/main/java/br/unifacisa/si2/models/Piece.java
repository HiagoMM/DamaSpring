package br.unifacisa.si2.models;

public abstract class Piece {
	
	abstract TipoPlayer getTipo();

	@Override
	public String toString() {
		return "Piece [getTipo()=" + getTipo() + "]";
	}
	
	
}
