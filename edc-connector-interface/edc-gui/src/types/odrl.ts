export type OdrlHasPolicy = {
    "@context": "http://www.w3.org/ns/odrl.jsonld";
    "@type": "Offer" | "Set";
    "@id": string;
    permission?: OdrlPermission | OdrlPermission[];
    prohibition?: unknown[];
    obligation?: unknown[];
};

export type OdrlPermission = {
    action: "use";
    constraint: OdrlConstraint | OdrlConstraint[];
};

export type OdrlConstraint = {
    "@type": "AtomicConstraint";
    leftOperand: string;
    // operator: {
    //     "@id": string;
    // };
    operator: string;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    rightOperand: any;
};
