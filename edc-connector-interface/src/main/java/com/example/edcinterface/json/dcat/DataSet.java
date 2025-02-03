package com.example.edcinterface.json.dcat;

import com.example.edcinterface.json.odrl.Policy;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataSet {

    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    @JsonProperty("odrl:hasPolicy")
    public Policy policy;

    @JsonProperty("dcat:distribution")
    public List<Distribution> distribution;

    @JsonProperty("id")
    public String uuid;

    public String name;

    public String contenttype;

}
