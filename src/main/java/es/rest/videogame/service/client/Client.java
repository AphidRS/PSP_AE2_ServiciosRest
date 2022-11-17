package es.rest.videogame.service.client;
import es.rest.videogame.service.model.Game;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        System.out.println("###############################");
        System.out.println("      CLIENTE VIDEOJUEGOS      ");
        System.out.println("###############################");
        try (Scanner sc = new Scanner(System.in)) {

            String option;
            do {
                System.out.println("Escoja opcion: ");
                System.out.println("===============================");
                System.out.println("1 - ALTA VIDEOJUEGOS");
                System.out.println("2 - BAJA VIDEOJUEGO POR ID");
                System.out.println("3 - MODIFICAR VIDEOJUEGO POR ID");
                System.out.println("4 - OBTENER VIDEOJUEGO POR ID");
                System.out.println("5 - LISTAR TODOS LOS VIDEOJUEGOS");
                System.out.println("6 - SALIR");
                System.out.println("===============================");

                option = sc.next();
                RestTemplate restTemplate = new RestTemplate();

                switch (option) {

                    case "1":
                        System.out.println("Introduzca NOMBRE");
                        String nombre = sc.next();
                        System.out.println("Introduzca COMPANIA");
                        String compania = sc.next();
                        System.out.println("Introduzca NOTA");
                        int nota = sc.nextInt();

                        String resourceUrl = "http://localhost:8080/games";
                        try{
                            HttpEntity<Game> request = new HttpEntity<>(new Game(0, nombre, compania, nota));
                            String gameCreateResponse = restTemplate.postForObject(resourceUrl, request, String.class);
                            System.out.println(gameCreateResponse);
                        } catch (HttpStatusCodeException e) {
                            if (e.getStatusCode().value() == 409){
                                System.out.println("Error 409: El nombre de juego ya está en uso.");
                            }
                            else {
                                System.out.println("Error "+e.getStatusCode()+": Excepción en el servidor.");
                            }
                        }
                        break;

                    case "2":
                        System.out.println("Introduzca ID para ELIMINAR");
                        int id = sc.nextInt();
                        resourceUrl = "http://localhost:8080/games/" + id;
                        try{
                            restTemplate.delete(resourceUrl, HttpMethod.DELETE);
                        } catch (HttpStatusCodeException e) {
                            if ( e.getStatusCode().value() == 404 ){
                                System.out.println("Error 404: El id de juego no existe.");
                            }
                            else {
                                System.out.println("Error "+e.getStatusCode()+": Excepción en el servidor.");
                            }
                        }
                        System.out.println("Borrado juego con ID :" + id);
                        break;

                    case "3":
                        System.out.println("Introduzca ID");
                        id = sc.nextInt();
                        System.out.println("Introduzca NOMBRE");
                        nombre = sc.next();
                        System.out.println("Introduzca COMPANIA");
                        compania = sc.next();
                        System.out.println("Introduzca NOTA");
                        nota = sc.nextInt();

                        try {
                            resourceUrl = "http://localhost:8080/games/" + id;
                            HttpEntity<Game> request = new HttpEntity<>(new Game(id, nombre, compania, nota));
                            restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, Void.class);
                        } catch (HttpStatusCodeException e) {
                            if (e.getStatusCode().value() == 404){
                                System.out.println("Error 404: El id de juego no existe.");
                            }
                            else if (e.getStatusCode().value() == 409){
                                System.out.println("Error 409: El nombre de juego ya está en uso.");
                            }
                            else {
                                System.out.println("Error "+e.getStatusCode()+": Excepción en el servidor.");
                            }
                        }
                        break;

                    case "4":
                        System.out.println("Introduzca ID a BUSCAR");
                        id = sc.nextInt();
                        resourceUrl = "http://localhost:8080/games/" + id;
                        try {
                            ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
                            String respuesta = response.getBody();
                            System.out.println(respuesta);
                        } catch (HttpStatusCodeException e) {
                            if (e.getStatusCode().value() == 404){
                                System.out.println("Error 404: El id de juego no existe.");
                            }
                            else if (e.getStatusCode().value() == 409){
                                System.out.println("Error 409: El nombre de juego ya está en uso.");
                            }
                            else {
                                System.out.println("Error "+e.getStatusCode()+": Excepción en el servidor.");
                            }
                        }

                        break;

                    case "5":
                        resourceUrl = "http://localhost:8080/games";
                        try{
                            ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
                            System.out.println(response.getBody());
                        } catch (HttpStatusCodeException e) {
                            System.out.println("Error "+e.getStatusCode()+": Excepción en el servidor.");
                        }
                        break;

                    case "6":
                        System.out.println("Saliendo del programa");
                        try {
                            ResponseEntity<String> response = restTemplate.getForEntity("https://api.chucknorris.io/jokes/random", String.class);
                            System.out.println("-------------- Free Chuck Joke --------------");
                            System.out.println(response.getBody());
                            System.out.println("-------------- Have a nice day --------------");
                        }catch (HttpStatusCodeException e) {

                        }
                        break;
                }
            } while (!option.equals("6"));
        }
    }
}