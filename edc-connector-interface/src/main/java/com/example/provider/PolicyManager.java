package com.example.provider;

import com.example.json.policy.CreatePolicyDTO;
import com.example.util.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PolicyManager {

    @Autowired
    private HttpRequester httpRequester;

    // TODO: Add so we can modify the request. For example permission, prohibitions, obligations.

    /**
     * @deprecated This will need to be updated, method signature will be different once fully implemented.
     * @param id
     * @return
     */
    @Deprecated
    public Object createPolicy(String id) {
        String url = "/v3/policydefinitions";

        CreatePolicyDTO dto = new CreatePolicyDTO();
        dto.id = id;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(Object.class);
    }
}
