export type EdcMetadata = {
    description: string;
    hostname: string;
    name: string;
    participantId: string;
    protocolAddress: string;
};

export type Base = {
    "@context": {
        "@vocab": "https://w3id.org/edc/v0.0.1/ns/";
        dcat: "http://www.w3.org/ns/dcat/";
        dct: "http://purl.org/dc/terms/";
        odrl: "http://www.w3.org/ns/odrl/2/";
        dspace: "https://w3id.org/dspace/v0.8/";
        edc: "https://w3id.org/edc/v0.0.1/ns/";
    };
};

export type EdcAsset = Base & {
    "@id": string
    "@type": "Asset",
    properties: {
        "name": string,
        "id": string,
        "contenttype": "application/json"
    },
    "dataAddress": {
        "@type": "DataAddress",
        "proxyPath": string,
        "type": "HttpData",
        "name": string,
        "baseUrl": string,
    }
};
