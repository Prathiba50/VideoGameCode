package StepDefinitions;

import Utils.APIResource;
import Utils.HelperBase;
import Utils.TestDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class VideoGameAPITest {

    public static RequestSpecification givenRes;
    HelperBase helperbase = new HelperBase();
    TestDataBuild data = new TestDataBuild();
    public static Response response;


    @When("verify {string} receiving proper response from {string} http method")
    public void verify_receiving_proper_response_from_http_method(String resource, String httpMethod) {
        System.out.println("validate receiving proper response from Video Game DB ");
        System.out.println("validate receiving proper response from Video Game DB  after branching");
        givenRes = given().spec(helperbase.reqSpec());
        APIResource resourceapi = APIResource.valueOf(resource);
        System.out.println("-------------------------------");
        System.out.println(resourceapi.getResource());
        if (httpMethod.equalsIgnoreCase("GET")) {
            response = givenRes.when().get(resourceapi.getResource());
        } else if (httpMethod.equalsIgnoreCase("POST")) {
            response = givenRes.when().post(resourceapi.getResource());
        } else if (httpMethod.equalsIgnoreCase("DELETE")) {
            response =  givenRes.when().delete(resourceapi.getResource());
        }
    }

    @Then("the API call got success with status {int}")
    public void the_api_call_got_success_with_status(int int1) {
        System.out.println("Validating Status code ");
        System.out.println("Validating Status code after branching ");
        assertEquals(response.getStatusCode(), int1);
    }

    @Given("Add VideoGame payload {int} and {string} and {string} and {int} and {string} and {string}")
    public void add_video_game_payload_and_and_and_and_and(int id, String name, String releaseDate, int reviewScore, String category, String rating) {
        System.out.println("Add payload.....");
        System.out.println("Add payload.....after branching");
        Response Res = given().contentType("application/json")
                .accept("application/json")
                .body(data.addVideoGame(id,name,releaseDate,reviewScore,category,rating))
                .when().post("http://localhost:8080/app/videogames")
                .then().statusCode(200).log().body()
                .extract().response();

        String jsonString = Res.asString();
        Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);
    }


    @Given("verify user will be able to fetch single game data {int}")
    public void verify_user_will_be_able_to_fetch_single_game_data(int id) {
        System.out.println("Verify user is able to fetch single record");
        System.out.println("Verify user is able to fetch single record after branching");
        given().when().get("http://localhost:8080/app/videogames/10")
                .then().statusCode(200).log().body()
                .body("videoGame.id",equalTo("10"))
                .body("videoGame.name", equalTo("Grand Theft Auto III"));
    }

    @Given("Update an existing video game in the DB {int} and {string} and {string} and {int} and {string} and {string}")
    public void update_an_existing_video_game_in_the_db_and_and_and_and_and
            (int id, String name, String releaseDate, int reviewScore, String category, String rating){
        System.out.println("Update video game details in the db for existing record");
        System.out.println("Update video game details in the db for existing record after branching");
        given().contentType("application/json").body(data.addVideoGame(id,name,releaseDate,reviewScore,category,rating))
                .when().put("http://localhost:8080/app/videogames/102")
                .then().statusCode(200).log().body()
                .body("videoGame.id",equalTo("102"))
                .body("videoGame.name", equalTo("PacMan1"));

    }

    @Given("Validate Video Game is deleted successfully from DB by ID {int} and {string}")
    public void validate_video_game_is_deleted_successfully_from_db_by_id_and(int id, String status) {
        System.out.println("Verify Video game is deleted successfully");
        System.out.println("Verify Video game is deleted successfully after branching");
        Response response = given()
                .when().delete("http://localhost:8080/app/videogames/102")
                .then().log().body().extract().response();
        String jsonString = response.asString();

        Assert.assertEquals(jsonString.contains(status),true);


        System.out.println("THis is from dev2");
        System.out.println("THis is from develop");

        System.out.println("Checking Pull request...");

    }
}


