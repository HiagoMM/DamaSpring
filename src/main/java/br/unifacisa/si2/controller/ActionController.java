package br.unifacisa.si2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.unifacisa.si2.dto.MovPecaDTO;
import br.unifacisa.si2.dto.PositionAndBoardDTO;
import br.unifacisa.si2.dto.ReturnPossiblePositionDTO;
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.models.exceptions.InvalidPieceException;
import br.unifacisa.si2.service.ActionService;
import br.unifacisa.si2.service.exception.InvalidPositionException;

@CrossOrigin
@Controller
@RequestMapping("/position")
public class ActionController {
	
	@Autowired
	private ActionService service;
	
	@PostMapping("check")
	public ResponseEntity<ReturnPossiblePositionDTO> positionValid(@RequestBody PositionAndBoardDTO posBoard) throws InvalidPieceException {
		return new ResponseEntity<ReturnPossiblePositionDTO>(service.getPossiblePosition(posBoard), HttpStatus.OK);
	}
	
	@PostMapping("mov")
	public ResponseEntity<Board> movPiece(@RequestBody MovPecaDTO posBoard) throws InvalidPositionException {
		return new ResponseEntity<>(service.movPiece(posBoard), HttpStatus.OK);
	}

}
