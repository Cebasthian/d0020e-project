package com.example.dto.odrl;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permission {

    @JsonProperty("odrl:target")
    public String target;

    @JsonProperty("odrl:action")
    public Object action;

    @JsonProperty("odrl:constraint")
    public Object constraint;



}
