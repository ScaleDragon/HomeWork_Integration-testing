package ru.netology.homework_spring_boot_purpose_internal_structure_v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final static GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private final static GenericContainer<?> proapp = new GenericContainer<>("proapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        proapp.start();

    }

    @Test
    void testDev() {
        Integer devappPort = devapp.getMappedPort(8080);
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devappPort, String.class);
        assertEquals(forEntityDev.getBody(), "Current profile is dev");
        System.out.println("devapp: " + forEntityDev.getBody());

    }

    @Test
    void testProd() {
        Integer proappPort = proapp.getMappedPort(8081);
        ResponseEntity<String> forEntityPro = restTemplate.getForEntity("http://localhost:" + proappPort, String.class);
        assertEquals(forEntityPro.getBody(), "Current profile is production");
        System.out.println("proapp: " + forEntityPro.getBody());
    }
}
