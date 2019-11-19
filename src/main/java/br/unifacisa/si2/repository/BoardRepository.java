package br.unifacisa.si2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.unifacisa.si2.models.Game;

@Repository
public interface BoardRepository extends MongoRepository<Game, String> {

}
