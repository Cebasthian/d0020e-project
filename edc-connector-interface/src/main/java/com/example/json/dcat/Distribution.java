package com.example.json.dcat;

import com.example.json.dct.Format;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Distribution {
    @JsonProperty("@type")
    public String type;

    @JsonProperty("dct:format")
    public Format format;

    @JsonProperty("dcat:accessService")
    public String accessService;
}
