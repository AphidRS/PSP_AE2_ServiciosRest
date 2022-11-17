package es.rest.videogame.service.client;
import es.rest.videogame.service.model.Game;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        System.out.println(">>>> CLIENTE VIDEOJUEGOS INICIADO <<<<");
        try (Scanner sc = new Scanner(System.in)) {

            do {
                System.out.println("Escoja opcion : ");
                System.out.println("===============================");
                System.out.println("1 - ALTA VIDEOJUEGOS");
                System.out.println("2 - BAJA VIDEOJUEGO POR ID");
                System.out.println("3 - MODIFICAR VIDEOJUEGO POR ID");
                System.out.println("4 - OBTENER VIDEOJUEGO POR ID");
                System.out.println("5 - LISTAR TODOS LOS VIDEOJUEGOS");
                System.out.println("6 - SALIR");
                System.out.println("===============================");

                String option = sc.nextLine();
                RestTemplate restTemplate = new RestTemplate();

                switch (option) {

                    case "1":
                        System.out.println("Introduzca ID");
                        int id = sc.nextInt();
                        System.out.println("Introduzca NOMBRE");
                        String nombre = sc.nextLine();
                        System.out.println("Introduzca COMPANIA");
                        String compania = sc.nextLine();
                        System.out.println("Introduzca NOTA");
                        int nota = sc.nextInt();

                        String resourceUrl = "http://localhost:8080/games";
                        HttpEntity<Game> request = new HttpEntity<>(new Game(id,nombre,compania,nota));

                        // Send the request body in HttpEntity for HTTP POST request
                        String gameCreateResponse = restTemplate.postForObject(resourceUrl, request, String.class);

                        System.out.println(gameCreateResponse);
                        break;

                    case "2":
                        System.out.println("Introduzca ID para ELIMINAR");
                        id = sc.nextInt();
                        resourceUrl = "http://localhost:8080/games/" + id;
                        restTemplate.delete(resourceUrl, HttpMethod.DELETE);
                        System.out.println("Borrado juego con ID :" + id);
                        break;
                    case "3":

                    case "4":

                    case "5":
                        resourceUrl = "http://localhost:8080/games";
                        // Fetch JSON response as String wrapped in ResponseEntity
                        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
                        String respuesta = response.getBody();
                        System.out.println(respuesta);
                        break;
                }

            } while (true);


        }
    }
}