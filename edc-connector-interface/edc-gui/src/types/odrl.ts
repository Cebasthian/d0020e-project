export type OdrlHasPolicy = {
    "@context": "http://www.w3.org/ns/odrl.jsonld";
    "@type": "Offer" | "Set";
    "@id": string;
    permission?: OdrlPermission[];
    prohibition?: unknown[];
    obligation?: unknown[];
};

export type OdrlPermission = {
    action: "use";
    constraint: OdrlConstraint;
};

export type OdrlConstraint = {
    "@type": "AtomicConstraint";
    leftOperand: string;
    operator: {
        "@id": string;
    };
    rightOperand: string;
};
