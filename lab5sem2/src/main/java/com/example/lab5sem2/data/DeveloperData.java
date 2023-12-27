package com.example.lab5sem2.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeveloperData {
  public String id;
  public String name;
  public Integer salary;
}
