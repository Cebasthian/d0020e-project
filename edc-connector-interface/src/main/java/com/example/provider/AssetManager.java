package com.example.provider;

import com.example.json.asset.CreateAssetDTO;
import com.example.util.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AssetManager {

    @Autowired
    private HttpRequester httpRequester;


    public Object createAsset(String id, String name, CreateAssetDTO.DataAddress dataAddress) {
        String url = "/v3/assets";

        CreateAssetDTO dto = new CreateAssetDTO();
        dto.id = id;
        dto.properties.name = name;
        dto.dataAddress = dataAddress;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(Object.class);
    }

}
