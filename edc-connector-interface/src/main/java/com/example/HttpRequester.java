package com.example;

import com.example.json.catalog.RequestCatalogDTO;
import com.example.json.catalog.RequestCatalogResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HttpRequester {

    @Value("${edc.port}")
    private String value;

    public String get() {
        return value;

    }

    public RequestCatalogResponse dto() {
        RequestCatalogDTO catalog = new RequestCatalogDTO("http://localhost:19194/protocol");
//        return catalog;

        RestClient client = RestClient.create();
        RequestCatalogResponse res = client.post().uri("http://localhost:29193/management/v3/catalog/request").header("Content-Type", "application/json").body(catalog).retrieve().body(RequestCatalogResponse.class);
        return res;
    }

}
