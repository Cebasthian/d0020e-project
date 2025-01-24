package com.example.json.dcat;

import com.example.json.odrl.HasPolicy;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataSet {

    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    @JsonProperty("odrl:hasPolicy")
    public HasPolicy policy;

    @JsonProperty("dcat:distribution")
    public List<Distribution> distribution;

    @JsonProperty("id")
    public String uuid;

    public String name;

    public String contenttype;

}
