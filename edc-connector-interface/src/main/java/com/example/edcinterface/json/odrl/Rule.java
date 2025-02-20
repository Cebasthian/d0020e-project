package com.example.edcinterface.json.odrl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String target;

    public Object action;


    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Constraint> constraint;

    public static class Constraint {
        public Object leftOperand;
        public Object operator;
        public Object rightOperand;
    }

    public static class Operator {
        @JsonProperty("@id")
        public String id;
    }
}
