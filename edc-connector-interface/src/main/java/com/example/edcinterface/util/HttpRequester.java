package com.example.edcinterface.util;

import com.example.edcinterface.json.BaseDTO;
import com.example.edcinterface.json.catalog.RequestCatalogDTO;
import com.example.edcinterface.json.catalog.RequestCatalogResponse;
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
}
