package com.example.edcinterface.json.odrl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HasPolicy {
    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    @JsonProperty("odrl:permission")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Permission> permission;

    @JsonProperty("odrl:prohibition")
    public List<Object> prohibition;

    @JsonProperty("odrl:obligation")
    public List<Object> obligation;

}
