package es.rest.videogame.service.controller;

import java.util.List;
import es.rest.videogame.service.model.Game;
import es.rest.videogame.service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
	public class Controller {
	
	@Autowired
	private GameRepository repository;

	@GetMapping(path="/games", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> buscarTodos(){
		return new ResponseEntity<List<Game>>(repository.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(path="/games/name/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> findByName(@PathVariable("nombre") String nombre) {
		Game g = repository.findByName(nombre);
		if(g ==null){
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND); // No encontrado, 404
		}
		return new ResponseEntity<Game>(g ,HttpStatus.OK); // Ok, 200
	}

	@GetMapping(path="/games/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> findById(@PathVariable("id") int id) {
		Game g = repository.findById(id);
		if(g ==null){
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND); // No encontrado, 404
		}
		return new ResponseEntity<Game>(g ,HttpStatus.OK); // Ok, 200
	}
	
	@PostMapping(path="/games")
	public ResponseEntity<Game> createGame(@RequestBody Game game) {
		boolean created = repository.create(game);
		if (created) {
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		}
		return new ResponseEntity<Game>(HttpStatus.CONFLICT);
	}

	@PutMapping(path="/games/{id}")
	public ResponseEntity<Game> updateGame(@PathVariable int id , @RequestBody Game game) {

		// 200 OK -> ID existe, Nombre no colisiona, cambio realizado
		// 404 NF -> ID no existe
		// 409 CL -> ID existe pero nombre colisiona

		//return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
		if (repository.idExist(game.getId()) == -1){
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}

		Game updated = repository.update(game);
		if (updated == null) {
			return new ResponseEntity<Game>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Game>(updated, HttpStatus.OK);
	}

	@RequestMapping(path="/games/{id}", method={RequestMethod.DELETE})
	public ResponseEntity deleteGame(@PathVariable("id") int id) {
		boolean deleted = repository.deleteById(id);
		if(deleted){
			return new ResponseEntity(HttpStatus.NO_CONTENT); // Borrado, respuesta afirmativa sin contenido, 204
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND); // No encontrado, 404
		
	}
}
	
	