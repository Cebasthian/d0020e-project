package com.example.dto.catalog;

import com.example.dto.BaseDTO;
import com.example.dto.dcat.DataSet;
import com.example.dto.dcat.Service;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestCatalogResponse extends BaseDTO {
    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    @JsonProperty("dcat:dataset")
    public Object dataset;

    @JsonProperty("dcat:service")
    public Service service;

    @JsonProperty("dspace:participantId")
    public String participantId;


    @JsonGetter
    public List<DataSet> getDataset() {
        if(dataset instanceof DataSet) {
            return Arrays.asList((DataSet) this.dataset);
        } else if(dataset instanceof List<?>) {
            return (List<DataSet>) this.dataset;
        }
        return null;
    }
}


/*
{
  "@id": "7df65569-8c59-4013-b1c0-fa14f6641bf2",
  "@type": "dcat:Catalog",
  "dcat:dataset": {
    "@id": "bcca61be-e82e-4da6-bfec-9716a56cef35",
    "@type": "dcat:Dataset",
    "odrl:hasPolicy": {
      "@id": "OGU0ZTMzMGMtODQ2ZS00ZWMxLThmOGQtNWQxNWM0NmI2NmY4:YmNjYTYxYmUtZTgyZS00ZGE2LWJmZWMtOTcxNmE1NmNlZjM1:NDY2ZTZhMmEtNjQ1Yy00ZGQ0LWFlZDktMjdjNGJkZTU4MDNj",
      "@type": "odrl:Set",
      "odrl:permission": {
        "odrl:target": "bcca61be-e82e-4da6-bfec-9716a56cef35",
        "odrl:action": {
          "odrl:type": "http://www.w3.org/ns/odrl/2/use"
        },
        "odrl:constraint": {
          "odrl:and": [
            {
              "odrl:leftOperand": "https://w3id.org/edc/v0.0.1/ns/inForceDate",
              "odrl:operator": {
                "@id": "odrl:gteq"
              },
              "odrl:rightOperand": {}
            },
            {
              "odrl:leftOperand": "https://w3id.org/edc/v0.0.1/ns/inForceDate",
              "odrl:operator": {
                "@id": "odrl:lteq"
              },
              "odrl:rightOperand": {}
            }
          ]
        }
      },
      "odrl:prohibition": [],
      "odrl:obligation": []
    },
    "dcat:distribution": [
      {
        "@type": "dcat:Distribution",
        "dct:format": {
          "@id": "HttpData"
        },
        "dcat:accessService": "5e839777-d93e-4785-8972-1005f51cf367"
      }
    ],
    "description": "description",
    "id": "bcca61be-e82e-4da6-bfec-9716a56cef35"
  },
  "dcat:service": {
    "@id": "5e839777-d93e-4785-8972-1005f51cf367",
    "@type": "dcat:DataService",
    "dct:terms": "connector",
    "dct:endpointUrl": "http://localhost:16806/protocol"
  },
  "dspace:participantId": "urn:connector:provider",
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/",
    "dct": "http://purl.org/dc/terms/",
    "edc": "https://w3id.org/edc/v0.0.1/ns/",
    "dcat": "http://www.w3.org/ns/dcat#",
    "odrl": "http://www.w3.org/ns/odrl/2/",
    "dspace": "https://w3id.org/dspace/v0.8/"
  }
}


{
  "@id": "7e90525d-4a08-4ca7-83cc-60c7eb78e592",
  "@type": "dcat:Catalog",
  "dcat:dataset": [
    {
      "@id": "assetId",
      "@type": "dcat:Dataset",
      "odrl:hasPolicy": {
        "@id": "MQ==:YXNzZXRJZA==:MThhMTY2MDAtYzY2NS00MTk1LTlmMGMtMjU4OTQ3YWZmYjlj",
        "@type": "odrl:Offer",
        "odrl:permission": [],
        "odrl:prohibition": [],
        "odrl:obligation": []
      },
      "dcat:distribution": [
        {
          "@type": "dcat:Distribution",
          "dct:format": {
            "@id": "HttpData-PULL"
          },
          "dcat:accessService": {
            "@id": "5aec6237-f40c-4f03-8eaf-984d09152551",
            "@type": "dcat:DataService",
            "dcat:endpointDescription": "dspace:connector",
            "dcat:endpointUrl": "http://localhost:19194/protocol",
            "dcat:endpointURL": "http://localhost:19194/protocol"
          }
        },
        {
          "@type": "dcat:Distribution",
          "dct:format": {
            "@id": "HttpData-PUSH"
          },
          "dcat:accessService": {
            "@id": "5aec6237-f40c-4f03-8eaf-984d09152551",
            "@type": "dcat:DataService",
            "dcat:endpointDescription": "dspace:connector",
            "dcat:endpointUrl": "http://localhost:19194/protocol",
            "dcat:endpointURL": "http://localhost:19194/protocol"
          }
        }
      ],
      "name": "product description",
      "id": "assetId",
      "contenttype": "application/json"
    },
    {
      "@id": "assetId2",
      "@type": "dcat:Dataset",
      "odrl:hasPolicy": {
        "@id": "MQ==:YXNzZXRJZDI=:YzYzOTI4MmUtYzUwNy00NzQ1LWExOTUtOTQ4MDkxNTQ0M2Nl",
        "@type": "odrl:Offer",
        "odrl:permission": [],
        "odrl:prohibition": [],
        "odrl:obligation": []
      },
      "dcat:distribution": [
        {
          "@type": "dcat:Distribution",
          "dct:format": {
            "@id": "HttpData-PULL"
          },
          "dcat:accessService": {
            "@id": "5aec6237-f40c-4f03-8eaf-984d09152551",
            "@type": "dcat:DataService",
            "dcat:endpointDescription": "dspace:connector",
            "dcat:endpointUrl": "http://localhost:19194/protocol",
            "dcat:endpointURL": "http://localhost:19194/protocol"
          }
        },
        {
          "@type": "dcat:Distribution",
          "dct:format": {
            "@id": "HttpData-PUSH"
          },
          "dcat:accessService": {
            "@id": "5aec6237-f40c-4f03-8eaf-984d09152551",
            "@type": "dcat:DataService",
            "dcat:endpointDescription": "dspace:connector",
            "dcat:endpointUrl": "http://localhost:19194/protocol",
            "dcat:endpointURL": "http://localhost:19194/protocol"
          }
        }
      ],
      "name": "product description 2",
      "id": "assetId2",
      "contenttype": "application/json"
    }
  ],
  "dcat:catalog": [],
  "dcat:distribution": [],
  "dcat:service": {
    "@id": "5aec6237-f40c-4f03-8eaf-984d09152551",
    "@type": "dcat:DataService",
    "dcat:endpointDescription": "dspace:connector",
    "dcat:endpointUrl": "http://localhost:19194/protocol",
    "dcat:endpointURL": "http://localhost:19194/protocol"
  },
  "dspace:participantId": "provider",
  "@context": {
    "dcat": "http://www.w3.org/ns/dcat#",
    "dct": "http://purl.org/dc/terms/",
    "odrl": "http://www.w3.org/ns/odrl/2/",
    "dspace": "https://w3id.org/dspace/v0.8/",
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/",
    "edc": "https://w3id.org/edc/v0.0.1/ns/"
  }
}
 */