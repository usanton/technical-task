package model.api.requesets;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {

  String title;
  String code;
  String description;
  String access;
  String group;
}
