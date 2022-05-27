Feature: Test "thecatapi"

  Scenario: GET /votes retrieve votes and store the response. You will have to create a proper object to unmarshal the result in it
    When Get all votes
    Then verify 200 response status code
    Then verify length of the response result is more than 0

  Scenario: From the array object stored at previous point get a random index vote element and  using the id attribute value, DO api call to GET /votes/{id}
    When Get a particular vote
    Then verify 200 response status code
  #combined into one check because it is verified in one place at the same time:
    Then verify response is not empty and field values in response SHOULD match to the random index vote element’s fields

  Scenario: Create a new vote POST /votes
    When Create a new vote
    Then verify 200 response status code
    Then verify body response match expected value "message": "SUCCESS"
    Then verify {id} response is not empty

  Scenario: Use the new {id} created at previous point to GET /votes/{id}
    When Get details on just created vote
    Then verify 200 response status code
    Then verify {id} response match {id} request

  Scenario: DELETE /votes/{vote_id} specifying the same {id} at previous point
    When Delete just created vote
    Then verify body response match expected value "message": "SUCCESS"

  Scenario: GET /votes/{id} using the {id} deleted at previous point
    When Get details on deleted just created vote
    Then verify body response match expected value "message": "NOT_FOUND"
    Then verify 404 response status code