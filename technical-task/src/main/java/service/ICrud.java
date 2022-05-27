package service;

import io.restassured.response.Response;

public interface ICrud<Request> {

  Response create(Request request);

  Response getAll();

  Response getOneById(int id);

  Response delete(int id);

}
