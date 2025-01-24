package com.example.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDTO {

    @JsonProperty("@context")
    public Context context = new Context();

    public static class Context {
        @JsonProperty("@vocab")
        public String vocab = "https://w3id.org/edc/v0.0.1/ns/";

        public String dcat = "http://www.w3.org/ns/dcat/";
        public String dct = "http://purl.org/dc/terms/";
        public String odrl = "http://www.w3.org/ns/odrl/2/";
        public String dspace = "https://w3id.org/dspace/v0.8/";
        public String edc = "https://w3id.org/edc/v0.0.1/ns/";

    }
}

/*

"@context": {
  "dcat": "http://www.w3.org/ns/dcat#",
  "dct": "http://purl.org/dc/terms/",
  "odrl": "http://www.w3.org/ns/odrl/2/",
  "dspace": "https://w3id.org/dspace/v0.8/",
  "@vocab": "https://w3id.org/edc/v0.0.1/ns/",
  "edc": "https://w3id.org/edc/v0.0.1/ns/"
}
 */