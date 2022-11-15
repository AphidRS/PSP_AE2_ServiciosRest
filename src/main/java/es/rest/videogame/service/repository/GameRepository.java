package es.rest.videogame.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import es.rest.videogame.service.model.Game;

@Repository
public class GameRepository {

	private List<Game> lista = new ArrayList<Game>();
	private int last_id = 0;

	public GameRepository() {
		lista.add(new Game(1, "Resident Evil", "Square Enix", 9));
		lista.add(new Game(2, "Metal Gear Solid", "Konami", 10));
		lista.add(new Game(3, "Counter-Strikel", "Valve", 9));
		lista.add(new Game(4, "Civilization VI", "2K Games", 8));
		lista.add(new Game(5, "DCS World", "Eagle Dynamics", 7));
		last_id = 5;
	}

	public List<Game> buscarTodos() {
		return lista;
	}


	public Game findByName(String nombre) {
		System.out.println("Buscando juego: " + nombre);
		for (Game p : lista) {
			if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
				System.out.println("Juego Encontrado" + p.toString());
				return p;
			}
		}
		System.out.println("Juego NO encontrado");
		return null;
	}

	public Boolean create(Game new_game) {
		for (Game p : lista) {
			if (new_game.getId() == p.getId() || new_game.getNombre().equals(p.getNombre())) { // NO PERMITE CREAR CON MISMA ID O MISMO NOMBRE
				return false;
			}
		}
		last_id += 1;
		new_game.setId(last_id);
		lista.add(new_game);
		return true;
	}

	public Game update(Game new_game) {
		int index = idExist(new_game.getId());
		if (index >= 0 && nombreExist(new_game.getNombre()) == -1){
			Game p = lista.get(index);
			p.setNombre(new_game.getNombre());
			p.setCompania(new_game.getCompania());
			p.setNota(new_game.getNota());
			return p;
		}
		System.out.println("Juego NO encontrado");
		return null;
	}

	public boolean deleteById(int id) {
		System.out.println("Borrando juego por id: " + id);
		int index = idExist(id);
		if (index >= 0){
			System.out.println("Juego Borrado");
			lista.remove(lista.get(index));
			return true;
		}
		System.out.println("Juego NO encontrado");
		return false;
	}

	public int nombreExist(String nombre) {
		for (Game p : lista) {
			if (p.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return lista.indexOf(p);
			}
		}
		return -1;
	}

	public int idExist(int id) {
		for (Game p : lista) {
			if (p.getId() == id) {
				return lista.indexOf(p);
			}
		}
		return -1;
	}
}
