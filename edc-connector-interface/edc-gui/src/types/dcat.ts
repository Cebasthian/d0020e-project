import { OdrlHasPolicy } from "./odrl";

export type DcatDataset = {
    name: string;
    contenttype: "application/json";
    "@id": string;
    "@type": "dcat:Dataset";
    "odrl:hasPolicy": OdrlHasPolicy;
    "dcat:distribution": DcatDistribution[];
    id: string;
};

export type DcatService = {
    "@id": string;
    "@type": "dcat:DataService";
    "dct:terms": unknown;
    "dct:endpointUrl": unknown;
};

export type DcatDistribution = {
    "@type": "dcat:Distribution";
    "dct:format": {
        "@id": string;
    };
    "dcat:accessService": {
        "@id": "HttpData-PUSH" | "HttpData-PULL";
        "@type": "dcat:DataService";
        "dcat:endpointDescription": "dspace:connector";
        "dcat:endpointUrl": string;
        "dcat:endpointURL": string;
    };
}