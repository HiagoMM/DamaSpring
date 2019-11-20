package br.unifacisa.si2.models;

import java.util.List;

import br.unifacisa.si2.dto.PositionDTO;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;

public interface Action {

	public List<PositionDTO> prevision(Game board, PositionDTO begin) throws InvalidPieceException;
}
