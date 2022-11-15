package es.rest.videogame.service.controller;

import java.awt.*;
import java.util.List;
import es.rest.videogame.service.model.Game;
import es.rest.videogame.service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
	public class Controller {
	
	@Autowired
	private GameRepository repository;
	
	@GetMapping(path="/games", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> buscarTodos(){
		return new ResponseEntity<List<Game>>(repository.buscarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(path="/games/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> findByName(@PathVariable("nombre") String nombre) {
		Game g = repository.findByName(nombre);
		if(g ==null){
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND); // No encontrado, 404
		}
		return new ResponseEntity<Game>(g ,HttpStatus.OK); // Ok, 200
	}
	
	@PostMapping(path="/games")
	public Game createPerson(@RequestBody Game game) {
		return repository.save(game);
	}
	
	@PutMapping(path="/games/{id}")
	public Game updatePerson(@PathVariable int id ,@RequestBody Game game) {
		return repository.save(game);
	}
	
	@RequestMapping(path="/games/{id}", method={RequestMethod.DELETE})
	public ResponseEntity deletePerson(@PathVariable("id") int id) {
		boolean deleted = repository.deleteById(id);
		if(deleted){
			return new ResponseEntity(HttpStatus.NO_CONTENT); // Borrado, respuesta afirmativa sin contenido, 204
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND); // No encontrado, 404
		
	}
}
	
	