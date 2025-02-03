package com.example.edcinterface.json.util;

import com.example.edcinterface.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateResponse extends BaseDTO {

    @JsonProperty("@type")
    public String type;

    @JsonProperty("@id")
    public String id;

    public Long createdAt;

}
