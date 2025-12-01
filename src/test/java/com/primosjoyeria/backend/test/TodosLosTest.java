package com.primosjoyeria.backend.test;

import com.primosjoyeria.backend.PrimosJoyeriaApp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@SpringBootTest(
        classes = PrimosJoyeriaApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodosLosTests {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    // ============================
    //   TESTS ORO
    // ============================

    @Test
    void oro_endpointDebeResponder200YPrecioValido() {
        webTestClient.get()
                .uri(url("/api/oro"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.rate.price").isNumber();
    }

    // ============================
    //   TESTS DÓLAR
    // ============================

    @Test
    void dolar_endpointDebeResponder200YValorValido() {
        webTestClient.get()
                .uri(url("/api/dolar"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.valor").isNumber();
    }

    // ============================
    //   TESTS PRODUCTOS (CRUD básico)
    // ============================

    @Test
    void productos_listarDebeRetornarArreglo() {
        webTestClient.get()
                .uri(url("/api/productos"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray();
    }

    @Test
    void productos_crearDebeRetornar201EId() throws Exception {
        // Ajusta los campos JSON al body que espera tu backend
        var nuevoProductoJson = """
                {
                  "nombre": "Anillo Prueba Test",
                  "precio": 19990,
                  "descripcion": "Producto creado desde test",
                  "imagenUrl": "https://ejemplo.com/imagen.jpg"
                }
                """;

        webTestClient.post()
                .uri(url("/api/productos"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(nuevoProductoJson)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CREATED)
                .expectBody()
                .jsonPath("$.id").isNumber()
                .jsonPath("$.nombre").isEqualTo("Anillo Prueba Test");
    }

    // Si sabes que existe un producto con id 1, puedes usar este test.
    // Si no, cambia el 1 por un id real de tu BD.
    @Test
    void productos_obtenerPorIdDebeRetornar200() {
        long idExistente = 1L; // ⚠️ AJUSTA ESTE VALOR SI TU BD USA OTRO ID
        webTestClient.get()
                .uri(url("/api/productos/" + idExistente))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo((int) idExistente);
    }

    // ============================
    //   TESTS LOGIN
    // ============================

    @Test
    void login_adminDebeRetornarRolAdmin() {
        // ⚠️ Ajusta correo/password a un usuario que exista en tu BD
        var loginJson = """
                {
                  "correo": "admin@joyeria.cl",
                  "password": "1234"
                }
                """;

        webTestClient.post()
                .uri(url("/api/auth/login"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.rol").isEqualTo("ADMIN");
    }

    @Test
    void login_credencialesInvalidasDebeRetornar4xx() {
        var loginJson = """
                {
                  "correo": "noexiste@joyeria.cl",
                  "password": "passwordIncorrecta"
                }
                """;

        webTestClient.post()
                .uri(url("/api/auth/login"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginJson)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    // ============================
    //   TEST INTEGRACIÓN GENERAL
    // ============================

    @Test
    void flujoCompleto_listarProductosYLlamarIndicadoresNoFalla() {
        // 1) listar productos
        webTestClient.get()
                .uri(url("/api/productos"))
                .exchange()
                .expectStatus().isOk();

        // 2) obtener oro
        webTestClient.get()
                .uri(url("/api/oro"))
                .exchange()
                .expectStatus().isOk();

        // 3) obtener dólar
        webTestClient.get()
                .uri(url("/api/dolar"))
                .exchange()
                .expectStatus().isOk();
    }
}
