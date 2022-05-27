package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatImageResponse {

  private Integer id;
  @JsonProperty("image_id")
  private String imageId;
  @JsonProperty("sub_id")
  private String subId;
  @JsonProperty("created_at")
  private String createdAt;
  private Integer value;
  @JsonProperty("country_code")
  private String countryCode;

}
