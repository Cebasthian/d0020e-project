package com.example.dto.dcat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Service {
    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    @JsonProperty("dct:terms")
    public String terms;

    @JsonProperty("dct.endpointUrl")
    public String endpointUrl;
}
