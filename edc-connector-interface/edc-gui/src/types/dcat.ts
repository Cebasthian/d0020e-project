import { OdrlHasPolicy } from "./odrl";

export type DcatDataset = {
    name: string;
    contenttype: "application/json";
    "@id": string;
    "@type": "Dataset";
    hasPolicy: OdrlHasPolicy;
    distribution: DcatDistribution[];
    id: string;
};

export type DcatService = {
    "@id": string;
    "@type": "DataService";
    terms: unknown;
    endpointUrl: unknown;
};

export type DcatDistribution = {
    "@type": "Distribution";
    format: {
        "@id": string;
    };
    accessService: {
        "@id": "HttpData-PUSH" | "HttpData-PULL";
        "@type": "DataService";
        endpointDescription: "connector";
        endpointUrl: string;
        endpointURL: string;
    };
};
