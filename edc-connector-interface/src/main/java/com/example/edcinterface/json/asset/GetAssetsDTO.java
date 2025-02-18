package com.example.edcinterface.json.asset;

import com.example.edcinterface.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAssetsDTO extends BaseDTO {
    @JsonProperty("@type")
    public String type = "QuerySpec";
}
