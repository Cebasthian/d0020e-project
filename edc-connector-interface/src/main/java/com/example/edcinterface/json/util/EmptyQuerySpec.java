package com.example.edcinterface.json.util;

import com.example.edcinterface.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmptyQuerySpec extends BaseDTO {
    @JsonProperty("@type")
    public String type = "QuerySpec";
}
