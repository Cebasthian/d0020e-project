package com.example.json.util;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateResponse extends BaseDTO {

    @JsonProperty("@type")
    public String type;

    @JsonProperty("@id")
    public String id;

    public Long createdAt;

}
