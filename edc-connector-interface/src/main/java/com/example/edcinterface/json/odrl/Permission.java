package com.example.edcinterface.json.odrl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Permission {

    @JsonProperty("odrl:target")
    public String target;

    @JsonProperty("odrl:action")
    public Object action;

    @JsonProperty("odrl:constraint")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Object> constraint;

    @JsonProperty("odrl:assigner")
    public String assigner;


}
