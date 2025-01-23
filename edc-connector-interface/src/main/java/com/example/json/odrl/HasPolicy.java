package com.example.json.odrl;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HasPolicy {
    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    @JsonProperty("odrl:permission")
    public Permission permission;

    @JsonProperty("odrl:prohibition")
    public List<Object> prohibition;

    @JsonProperty("odrl:obligation")
    public List<Object> obligation;

}
