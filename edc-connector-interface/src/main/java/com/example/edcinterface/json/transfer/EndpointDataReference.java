package com.example.edcinterface.json.transfer;

import com.example.edcinterface.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EndpointDataReference extends BaseDTO {
    @JsonProperty("@type")
    public String type;

    @JsonProperty("type")
    public String dataType;

    public String endpoint;
    public String authType;
    public String endpointType;
    public String authorization;
}
/*
{
  '@type': 'DataAddress',
  type: 'https://w3id.org/idsa/v4.1/HTTP',
  endpoint: 'http://localhost:19291/public',
  authType: 'bearer',
  endpointType: 'https://w3id.org/idsa/v4.1/HTTP',
  authorization: 'auth-token'
  '@context': {
    '@vocab': 'https://w3id.org/edc/v0.0.1/ns/',
    edc: 'https://w3id.org/edc/v0.0.1/ns/',
    odrl: 'http://www.w3.org/ns/odrl/2/'
  }
}
 */