package com.example.dto.dcat;

import com.example.dto.dct.Format;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Distribution {
    @JsonProperty("@type")
    public String type;

    @JsonProperty("dct:format")
    public Format format;

    @JsonProperty("dcat:accessService")
    public String accessService;
}
