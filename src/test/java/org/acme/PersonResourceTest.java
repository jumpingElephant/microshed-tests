package org.acme;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicroShedTest
public class PersonResourceTest {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/");

    @Container
    public static PostgreSQLContainer<?> db = new PostgreSQLContainer<>();

    @Test
    public void testCreatePerson() {
        given()
                .queryParam("first", "Bob")
                .queryParam("last", "Bobington")
                .when()
                .post("/people")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetPerson() {
        long calID = given()
                .queryParam("first", "Cal")
                .queryParam("last", "Ifornia")
                .when()
                .post("/people")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(long.class);

        Person cal = given()
                .pathParam("id", calID)
                .when()
                .get("/people/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(Person.class);
        assertEquals("Cal", cal.firstName);
        assertEquals("Ifornia", cal.lastName);
        assertEquals(calID, cal.id);
    }
}