package com.example;

import com.example.dto.FetchCatalogDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRequester {

    @Value("${edc.port}")
    private String value;

    public String get() {
        return value;

//        RestClient client = RestClient.create();
//        return client.get().uri("http://localhost:6464").retrieve().body(String.class);
    }

    public Object dto() {
        FetchCatalogDTO catalog = new FetchCatalogDTO("http://localhost:19194/protocol");
        RestClient client = RestClient.create();
        return client.post().uri("http://localhost:29193/management/v3/catalog/request").header("Content-Type", "application/json").body(catalog).retrieve().body(Object.class);
    }

}
