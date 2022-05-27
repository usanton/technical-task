package stepsDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.CatImageRequest;
import model.CatImageResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;
import service.CatImageService;

public class CatImageTestStepsDefinition {

  private static Response response;
  private static List<CatImageResponse> catImageResponseList;
  private static Integer createdCatImageId;
  private static CatImageResponse actualCatImageResponse;
  private static CatImageResponse expectedCatImageResponse;
  private static Map<String, Object> imageCreationResponse;

  private static final CatImageService catImageService = CatImageService.getInstance();;
  private static final SoftAssertions softAssert = new SoftAssertions();

  @When("Get all votes")
  public void get_all_votes() throws InstantiationException, IllegalAccessException {

    response = catImageService.getAll();
    catImageResponseList = response.as(new TypeRef<List<CatImageResponse>>() {
    });
  }

  @Then("verify {int} response status code")
  public void verify_response_status_code(Integer expectedStatusCode) {
    softAssert.assertThat(response.getStatusCode()).as(String.format("Status code is not %d", expectedStatusCode))
        .isEqualTo(expectedStatusCode);
  }

  @Then("verify length of the response result is more than {int}")
  public void verify_length_of_response_result_is_more_than(int expectedLengthValue) {
    softAssert.assertThat(catImageResponseList).as("No objects in response, array is empty")
        .hasSizeGreaterThan(expectedLengthValue);
  }

  @When("Get a particular vote")
  public void get_a_particular_vote() {
    expectedCatImageResponse = catImageResponseList.stream().findAny().get();
    response = catImageService.getOneById(expectedCatImageResponse.getId());
  }

  @Then("verify response is not empty and field values in response SHOULD match to the random index vote element’s fields")
  public void verify_response_is_not_empty_and_field_values_in_response_should_match_to_the_random_index_vote_elements_fileds() {
    actualCatImageResponse = response.as(CatImageResponse.class);
    softAssert.assertThat(actualCatImageResponse).as("Data in the response doesn't correspond"
        + " to expected").isEqualTo(expectedCatImageResponse);
  }

  @When("Create a new vote")
  public void create_a_new_vote() throws InstantiationException, IllegalAccessException {
    CatImageRequest catImageRequest = generateRandomObject(CatImageRequest.class);
    response = catImageService.create(catImageRequest);
  }

  @Then("verify body response match expected value {string}: {string}")
  public void verify_body_response_match_expected_value(String key, String value) {
    imageCreationResponse = response.as(Map.class);
    softAssert.assertThat(imageCreationResponse).as("Response body doesn't contain expected entry")
        .containsEntry(key, value);
  }

  @Then("verify \\{id} response is not empty")
  public void verify_response_is_not_empty() {
    createdCatImageId = (int) imageCreationResponse.get("id");
    softAssert.assertThat(createdCatImageId).as("'id' parameter is missed").isNotNull();
  }

  @When("Get details on just created vote")
  public void get_details_on_just_created_vote() {
    response = catImageService.getOneById(createdCatImageId);
  }

  @Then("verify \\{id} response match \\{id} request")
  public void verify_response_match_request() {
    actualCatImageResponse = response.as(CatImageResponse.class);
    softAssert.assertThat(actualCatImageResponse.getId()).as("'id' value from response doesn't"
        + " match the one of just created images").isEqualTo(createdCatImageId);
  }

  @When("Delete just created vote")
  public void delete_just_created_vote() {
    response = catImageService.delete(createdCatImageId);
  }

  @When("Get details on deleted just created vote")
  public void get_details_on_deleted_just_created_vote() {
    response = catImageService.getOneById(createdCatImageId);
  }

  private <T> T generateRandomObject(Class<T> clazz)
      throws IllegalAccessException, InstantiationException {
    T classInstance = clazz.newInstance();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      Class fieldClass = field.getType();
      if (fieldClass.equals(String.class)) {
        field.set(classInstance, RandomStringUtils.randomAlphanumeric(10));
      } else if (fieldClass.equals(Integer.class)) {
        field.set(classInstance, new Random().nextInt(10));
      } else {
        field.set(classInstance, generateRandomObject(fieldClass));
      }
    }
    return classInstance;
  }

}
