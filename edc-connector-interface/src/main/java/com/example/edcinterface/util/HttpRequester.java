package com.example.edcinterface.util;

import com.example.edcinterface.json.BaseDTO;
import com.example.edcinterface.json.catalog.RequestCatalogDTO;
import com.example.edcinterface.json.catalog.RequestCatalogResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class HttpRequester {

    @Value("${edc.connector.management-port}")
    private String connectorPort;
    @Value("${edc.connector.hostname}")
    private String connectorHostname;
    @Value("${edc.connector.management-path}")
    private String managementPath;
    @Value("${edc.connector.api-path}")
    private String apiPath;
    @Value("${edc.connector.metadata-path}")
    private String metadataPath;
    @Value("${edc.dataspace.connectors}")
    private List<String> targetConnectors;


    private String getConnectorAddress() {
        return "http://"+connectorHostname+":"+connectorPort+managementPath;
    }

    public RestClient.ResponseSpec post(String endpoint, BaseDTO body) {
        RestClient client = RestClient.builder()
                .baseUrl(getConnectorAddress())
                .defaultHeader("Content-Type", "application/json")
                .build();
        return client.post().uri(endpoint).body(body).retrieve();
    }

    public RestClient.ResponseSpec get(String endpoint) {
        RestClient client = RestClient.builder()
                .baseUrl(getConnectorAddress())
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

    public Object getConnectorsMetadata() {
        RestClient client = RestClient.builder()
                .defaultHeader("Content-Type", "application/json")
                .build();
        List<Object> metadata = new ArrayList<>();
        for(String host : targetConnectors) {
            try {
                Object info = client.get().uri("http://"+host+metadataPath).retrieve().body(Object.class);
                metadata.add(info);
            } catch (Exception exception) {
                // Probably that the connector is not running.
            }
        }
        return metadata;
    }
}
