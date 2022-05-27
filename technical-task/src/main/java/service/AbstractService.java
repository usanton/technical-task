package service;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static constants.Constants.BASE_URI;

import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractService<Req> implements ICrud<Req> {

  protected abstract String uri();

  @Override
  public Response create(Req req) {
    return client().when().body(req).log().all(true).post(uri());
  }

  @Override
  public Response getAll() {
    return client().get(uri());
  }

  @Override
  public Response getOneById(int id) {
    return client().get(uri().concat("/" + id));
  }

  @Override
  public Response delete(int id) {
    return client().delete(uri().concat("/" + id));
  }

  private RequestSpecification client() {
    return given()
        .config(config().encoderConfig(
        EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        .log()
        .all(true)
        .header("x-api-key", "DEMO-API-KEY")
        .contentType(ContentType.JSON)
        .baseUri(BASE_URI);
  }
}
