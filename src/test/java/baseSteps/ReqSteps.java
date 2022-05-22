package baseSteps;

import io.cucumber.java.bg.То;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReqSteps {

    @Когда("^что-то там")
    public void crUs(){

    }

    @Тогда("^создаем юзера")
    public static void createUser() throws IOException {

        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/1.json"))));
        body.put("name", "Tomato");
        body.put("job", "Eat market");
        Response response = given()
                .header("Content-type","application/json")
                .baseUri("https://reqres.in")
                .body(body.toString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .log().body()
                .extract()
                .response();

        String name = response.path("name");
        String job = response.path("job");

        Assert.assertEquals("Tomato", name);
        Assert.assertEquals("Eat market", job);

    }
}
