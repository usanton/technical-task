package api;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import model.api.requesets.ProjectRequest;
import model.api.responses.ProjectResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class CreateProjectTest {

  @Test(testName = "Login", description = "", suiteName = "Login")
  public void createProjectTest() {
    String projectCode = null;
    try {
      projectCode = ExcelReader.readFile("test_spreadsheet.xlsx");
    } catch (IOException e) {
      e.printStackTrace();
    }

    /*File file = new File("code.txt");
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      fileOutputStream.write(projectCode.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Expected code is:" + projectCode);*/

    ProjectRequest projectRequest = ProjectRequest.builder().
        title("SOme new title").
        code(projectCode).
        build();

    ProjectResponse projectResponse = given().
        baseUri("https://api.qase.io/v1/").
        header("Token", "cb32d9e2a156ddf83660dc641dc8074fd5aa247e").
        header("Content-Type", ContentType.JSON).
        when().
        body(projectRequest).
        post("project").
        then().
        log().all().
        statusCode(200).
        extract().body().as(ProjectResponse.class);
    System.out.println("Actual code is:" + projectResponse.getResult().getCode());

    /*StringBuilder stringBuilder = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      stringBuilder = new StringBuilder();
      int c = 0;
      while((c = fileInputStream.read()) != -1) {
             stringBuilder.append((char) c);
      }
      System.out.println(stringBuilder.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    Assert.assertEquals(projectResponse.getResult().getCode(), projectCode);
  }

  private static String generateProjectCode() {
    return RandomStringUtils.randomAlphabetic(2, 5).toUpperCase();
  }
}
