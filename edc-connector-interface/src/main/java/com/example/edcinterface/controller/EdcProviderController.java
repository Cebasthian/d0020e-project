package com.example.edcinterface.controller;

import com.example.edcinterface.json.asset.CreateAssetDTO;
import com.example.edcinterface.json.asset.GetAssetsDTO;
import com.example.edcinterface.json.odrl.Policy;
import com.example.edcinterface.json.util.CreateResponse;
import com.example.edcinterface.json.util.QuerySpec;
import com.example.edcinterface.provider.EdcProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("/edc-provider")
@CrossOrigin
@Tag(name = "Edc Provider", description = "Endpoints related to providing assets to an edc dataspace.")
public class EdcProviderController {

    @Autowired
    private EdcProvider edcProvider;


    @Operation(summary = "Create an asset", description = "Create an asset and attach a data address to it.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Asset created"),
    })
    @PostMapping("/assets/create")
    public CreateResponse createAsset(@RequestBody NewAssetDTO body) {
        CreateAssetDTO.DataAddress dataAddress = new CreateAssetDTO.DataAddress();
        dataAddress.name = body.name;
        dataAddress.proxyPath = "false";
        dataAddress.baseUrl = body.baseUrl;

        return edcProvider.createAsset(body.name, dataAddress);
    }

    @GetMapping("/assets")
    public Object getAssets() {
        QuerySpec spec = new QuerySpec(0, 1000, QuerySpec.SortOrder.ASC, "", List.of());

        return edcProvider.getAssets(new GetAssetsDTO());
    }


    @Operation(summary = "Create policy definition")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Policy definition created"),
    })
    @PostMapping("/policies/create")
    public CreateResponse createPolicy(@RequestBody NewPolicyDTO body) {
        return edcProvider.createPolicy(body.id, body.policy);
    }


    @Operation(summary = "Create contract definition")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contract definition created"),
    })
    @PostMapping("/contracts/create")
    public CreateResponse createContract(@RequestBody NewContractDTO body) {
        return edcProvider.createContract(body.id, body.accessPolicyId, body.contractPolicyId, Collections.emptyList());
    }


    public static class NewAssetDTO {
        public String name;
        public String baseUrl;
    }

    public static class NewContractDTO {
        public String id;
        public String accessPolicyId;
        public String contractPolicyId;
    }

    public static class NewPolicyDTO {
        public String id;
        public Policy policy;
    }
}
