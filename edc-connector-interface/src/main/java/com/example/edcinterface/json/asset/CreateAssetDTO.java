package com.example.edcinterface.json.asset;

import com.example.edcinterface.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class CreateAssetDTO extends BaseDTO {

    @JsonProperty("@id")
    public String id;

    public HashMap<String, String> properties = new HashMap<>();
//    public Properties properties = new Properties();
//    public static class Properties {
//        public String name;
//        public String contenttype = "application/json";
//    }

    public HashMap<String, String> privateProperties = new HashMap<>();

    public DataAddress dataAddress = new DataAddress();
    public static class DataAddress {
        public String type = "HttpData";
        public String name;
        public String baseUrl;
        public String proxyPath;
    }

}

/*

{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@id": "assetId",
  "properties": {
    "name": "product description",
    "contenttype": "application/json"
  },
  "dataAddress": {
    "type": "HttpData",
    "name": "Test asset",
    "baseUrl": "https://jsonplaceholder.typicode.com/users",
    "proxyPath": "true"
  }
}


 */