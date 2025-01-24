package com.example.util;

import com.example.json.BaseDTO;
import com.example.json.catalog.RequestCatalogDTO;
import com.example.json.catalog.RequestCatalogResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HttpRequester {

    @Value("${edc.connector.address}")
    private String connectorAddress;

    public RestClient.ResponseSpec post(String endpoint, BaseDTO body) {
        RestClient client = RestClient.builder()
                .baseUrl(connectorAddress)
                .defaultHeader("Content-Type", "application/json")
                .build();
        return client.post().uri(endpoint).body(body).retrieve();
    }

    public RestClient.ResponseSpec get(String endpoint) {
        RestClient client = RestClient.builder()
                .baseUrl(connectorAddress)
                .defaultHeader("Content-Type", "application/json")
                .build();
        return client.get().uri(endpoint).retrieve();
    }

    public RestClient.ResponseSpec retrieveData(String endpoint, String authorization) {
        System.out.println("URL: " + endpoint + " | Auth: " + authorization);

        RestClient client = RestClient.builder()
//                .defaultHeader("Content-Type", "application/json")
                .build();
        return client.get().uri(endpoint).header("Authorization", authorization).retrieve();
    }


    public Object dto() {
        RequestCatalogDTO catalog = new RequestCatalogDTO("http://localhost:19194/protocol");
//        return catalog;

        RestClient client = RestClient.create();
        Object res = client.post().uri("http://localhost:29193/management/v3/catalog/request").header("Content-Type", "application/json").body(catalog).retrieve().body(RequestCatalogResponse.class);
        return res;
    }

}
