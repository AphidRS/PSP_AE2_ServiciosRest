package es.prubaspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.prubaspring.model.Game;
import es.prubaspring.repository.GameRepository;

	@RestController
	public class Controller {
	
	@Autowired
	private GameRepository repository;
	
	@GetMapping("/games")
	public List<Game> buscarTodos(){
		return repository.buscarTodos();
	}
	
	@GetMapping("/game/{nombre}")
	public List<Game> findByName(@PathVariable("nombre") String nombre) {
		return repository.findByName(nombre);
	}
	
	@PostMapping("/game")
	public Game createPerson(@RequestBody Game game) {
		return repository.save(game);
	}
	
	@PutMapping("/game/{id}")
	public Game updatePerson(@PathVariable int id ,@RequestBody Game game) {
		return repository.save(game);
	}
	
	@DeleteMapping("/game/{id}")
	public void deletePerson(@PathVariable("id") int id) {
		repository.deleteById(id);
		
	}
}
	
	