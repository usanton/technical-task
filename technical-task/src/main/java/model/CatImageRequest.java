package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CatImageRequest {

  @JsonProperty("image_id")
  private String imageId;
  @JsonProperty("sub_id")
  private String subId;
  private Integer value;

}
