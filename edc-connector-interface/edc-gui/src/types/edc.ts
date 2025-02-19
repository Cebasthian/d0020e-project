import { DcatDataset, DcatService } from "./dcat";

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
    "@id": string;
    "@type": "Asset";
    properties: {
        name: string;
        id: string;
        contenttype: "application/json";
    };
    dataAddress: {
        "@type": "DataAddress";
        proxyPath: string;
        type: "HttpData";
        name: string;
        baseUrl: string;
    };
};

export type EdcCatalog = Base & {
    "@id": string;
    "@type": "Catalog";
    dataset: DcatDataset[];
    service: DcatService;
    participantId: string;
};

export type EdcNegotiation = Base & {
    "@type": "ContractNegotiation";
    "@id": string;
    type: string;
    protocol: "dataspace-protocol-http";
    state: string;
    counterPartyId: string;
    counterPartyAddress: string;
    callbackAddresses: unknown[];
    createdAt: number;
    contractAgreementId?: string;
};

export type EdcAgreeement = Base & {
    "@type": "https://w3id.org/edc/v0.0.1/ns/ContractAgreement";
    "@id": "negotiation-id";
    providerId: "provider-id";
    consumerId: "consumer-id";
    assetId: "asset-id";
    contractSigningDate: 1688465655;
    policy: {
        "@context": "http://www.w3.org/ns/odrl.jsonld";
        "@type": "Set";
        "@id": "offer-id";
        permission: [
            {
                target: "asset-id";
                action: "display";
            }
        ];
    };
};
