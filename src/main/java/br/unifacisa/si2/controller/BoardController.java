package br.unifacisa.si2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.unifacisa.si2.dto.PlayersDTO;
import br.unifacisa.si2.models.Game;
import br.unifacisa.si2.models.exceptions.SizeBoardException;
import br.unifacisa.si2.service.ServiceBoard;
import br.unifacisa.si2.service.exception.NotFoundBoardException;

@Controller
@RequestMapping("/board")
@CrossOrigin
public class BoardController {
	
	@Autowired
	private ServiceBoard service;
	

	@PostMapping
	public ResponseEntity<Game> createBoard(@RequestBody PlayersDTO players) throws SizeBoardException {
		return new ResponseEntity<Game>(service.createBoard(players), HttpStatus.CREATED);
	}
	

	
	
	
}
