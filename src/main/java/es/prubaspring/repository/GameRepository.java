package es.prubaspring.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import es.prubaspring.model.Game;

@Repository
public class GameRepository {
	
	private List<Game> lista= new ArrayList<Game>();
	
	public List<Game> buscarTodos() {
		
		lista.add(new Game ("1","Resident Evil","Square Enix",9));
		lista.add(new Game ("2","Metal Gear Solid","Konami",10));
		lista.add(new Game ("3","Counter-Strikel","Valve",9));
		lista.add(new Game ("4","Civilization VI","2K Games",8));
		lista.add(new Game ("5","DCS World","Eagle Dynamics",7));
    
		return lista;
    
	}

	public List<Game> findByName(String nombre) {
		
		
		return null;
	}

	public Game save(Game game) {
		this.lista.add(game);
		
		
		return null;
	}

	public void deleteById(int id) {

		
	}
	
	
	
	
}