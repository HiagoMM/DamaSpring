package br.unifacisa.si2.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.unifacisa.si2.models.Game;
import br.unifacisa.si2.models.Player;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
	
	Optional<Game> findByPlayer1AndPlayer2(Player player1,Player player2);
}
