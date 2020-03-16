package org.acme;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@MicroShedTest
@Disabled
public class MicroshedExampleResourceTest {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/");

//    @RESTClient
//    public static ExampleResource exampleResource;

    @Test
    public void test() {
//        exampleResource.hello();

        given()
                .when()
                .get("/hello")
                .then()
                .statusCode(200)
                .body(equalTo("Hello"));

    }

}
