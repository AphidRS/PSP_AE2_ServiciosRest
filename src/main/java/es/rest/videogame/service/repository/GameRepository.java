package es.rest.videogame.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import es.rest.videogame.service.model.Game;

@Repository
public class GameRepository {

	private List<Game> lista = new ArrayList<Game>();

	public GameRepository(){
		lista.add(new Game("1", "Resident Evil", "Square Enix", 9));
		lista.add(new Game("2", "Metal Gear Solid", "Konami", 10));
		lista.add(new Game("3", "Counter-Strikel", "Valve", 9));
		lista.add(new Game("4", "Civilization VI", "2K Games", 8));
		lista.add(new Game("5", "DCS World", "Eagle Dynamics", 7));
	}

	public List<Game> buscarTodos() {
		return lista;
	}

	public Game findByName(String nombre) {
		System.out.println("Buscando juego: "+nombre);
		for (Game p : lista) {
			if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
				System.out.println("Juego Encontrado" + p.toString());
				return p;
			}
		}
		System.out.println("Juego NO encontrado");
		return null;
	}

	public Game save(Game game) {
		this.lista.add(game);

		return null;
	}

	public void deleteById(int id) {

	}

}