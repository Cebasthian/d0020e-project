import { DcatDataset, DcatService } from "./dcat";
import { OdrlHasPolicy } from "./odrl";

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

/* PROVIDING */

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

export type EdcPolicyDefinition = Base & {
    "@id": string;
    "@type": "PolicyDefinition",
    createdAt: number,
    policy: OdrlHasPolicy
};

export type EdcContractDefinition = Base & {
    "@id": string;
    "@type": "ContractDefinition";
    accessPolicyId: string;
    contractPolicyId: string;
    assetsSelector: AssetsSelector[];
};

export type AssetsSelector = {
    "@type": "https://w3id.org/edc/v0.0.1/ns/Criterion";
    operandLeft: string;
    operator: string;
    operandRight: string;
};

/* CONSUMING */

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


export type EdcTransfer = Base & {
    "@id": string,
    "@type": "TransferProcess",
    "state": "REQUESTED" | "STARTED" | "SUSPENDED" | "RESUMED" | "COMPLETED" | "TERMINATED",
    "stateTimestamp": number,
    "type": string,
    "callbackAddresses": unknown[],
    "correlationId": string,
    "assetId": string,
    "contractId": string,
    "transferType": string,
}