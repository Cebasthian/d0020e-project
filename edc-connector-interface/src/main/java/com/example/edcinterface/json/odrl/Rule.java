package com.example.edcinterface.json.odrl;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    public String target;
    public String action;

    public List<Constraint> constraint = new ArrayList<>();

    public static class Constraint {
        public String leftOperand;
        public String operator;
        public Object rightOperand;
    }
}
