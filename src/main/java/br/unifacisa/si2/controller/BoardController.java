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
import br.unifacisa.si2.models.Board;
import br.unifacisa.si2.service.ServiceBoard;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private ServiceBoard service;
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Board> createBoard(@RequestBody PlayersDTO players) {
		return new ResponseEntity<Board>(service.createBoard(players), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping("save")
	@ResponseStatus(value = HttpStatus.OK)
	public void saveBoard(@RequestBody Board board) {
		 service.saveBoard(board);
	}
	
	@CrossOrigin
	@GetMapping("{playerName}")
	public ResponseEntity<List<Board>> getBoards(@PathVariable String playerName) {
		return new ResponseEntity<List<Board>>(service.getBoards(), HttpStatus.OK);
	}
	
	
}
