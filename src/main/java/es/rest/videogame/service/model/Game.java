package es.rest.videogame.service.model;

public class Game {
	
	private int id;
	private String nombre;
	private String compania;
	private int  nota;
	
	public Game(int id, String nombre, String compania, int nota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.compania = compania;
		this.nota = nota;
	}

	public Game() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", nombre=" + nombre + ", compania=" + compania + ", nota=" + nota + "]";
	}

	

}