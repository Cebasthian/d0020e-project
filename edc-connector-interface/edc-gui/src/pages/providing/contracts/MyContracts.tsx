import { useCallback, useMemo, useState } from "react";
import { useImmer } from "use-immer";
import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import Modal from "../../../components/modal/Modal";
import { AssetsSelector, EdcContractDefinition, EdcPolicyDefinition } from "../../../types/edc";
import { createContract } from "../../../util/edc_interface";
import { PolicyToString } from "../../../util/lib";
import { useGetter } from "../../../util/useGetter";
import styles from "./my-contracts.module.css";

type NewSelector = {
    operandLeft: string;
    operator: string;
    operandRight: string;
};

const defaultNewSelector = (): NewSelector => ({
    operandLeft: "",
    operator: "=",
    operandRight: "",
});

export default function MyContracts() {
    const { data: contracts, refresh } = useGetter<EdcContractDefinition[]>(
        "/edc-provider/contracts",
        []
    );
    const { data: policies } = useGetter<EdcPolicyDefinition[]>(
        "/edc-provider/policies",
        []
    );

    const [contractId, setContractId] = useState("");
    const [contractPolicyId, setContractPolicyId] = useState("");
    const [accessPolicyId, setAccessPolicyId] = useState("");

    const [error, setError] = useState("");

    const [showModal, setShowModal] = useState(false);

    const [selector, setSelector] = useImmer<NewSelector>(defaultNewSelector());

    const show = useCallback(() => {
        setContractId("");
        setContractPolicyId("");
        setAccessPolicyId("");
        setSelector(defaultNewSelector());
        setError("");

        setShowModal(true);
    }, [setShowModal, setSelector]);

    const generatePolicyOptions = useCallback(() => {
        return policies.map((p) => (
            <option
                value={p["@id"]} key={p["@id"]}
                title={PolicyToString(p.policy, "no policy")}
            >
                {p["@id"]}
            </option>
        ));
    }, [policies]);

    const create = useCallback(async () => {

        setError("")

        if(!contractId) {
            setError("Invalid contract id")
            return
        }

        if(!contractPolicyId) {
            setError("Invalid contract policy")
            return
        }

        if(!accessPolicyId) {
            setError("Invalid access policy")
            return
        }
        

        const assetsSelector = [{
            "@type": "https://w3id.org/edc/v0.0.1/ns/Criterion",
            operandLeft: selector.operandLeft,
            operator: selector.operator,
            operandRight: selector.operandRight,
        }]

        const json = await createContract({
            "@id": contractId,
            accessPolicyId: accessPolicyId,
            contractPolicyId: contractPolicyId,
            assetsSelector: (selector.operandLeft && selector.operandRight) ? assetsSelector : []
        })

        console.log(json)
        
        setShowModal(false);
        refresh()

    }, [contractId, contractPolicyId, accessPolicyId, setError, selector, refresh]);

    const modal = useMemo(() => {
        return (
            <Modal setShow={setShowModal}>
                <div className={styles.modal}>
                    <h3>Create Contract</h3>
                    <span className={styles["modal-error"]}>{error}</span>
                    <label>
                        <span>Contract Id</span>
                        <input
                            required
                            placeholder="Contract Id *"
                            value={contractId}
                            onChange={(e) => setContractId(e.target.value)}
                        />
                    </label>
                    <label>
                        <span>Contract Policy</span>
                        <select
                            value={contractPolicyId}
                            onChange={(e) =>
                                setContractPolicyId(e.target.value)
                            }
                        >
                            <option value="" disabled>Select Policy</option>
                            {generatePolicyOptions()}
                        </select>
                    </label>

                    <label>
                        <span>Access Policy</span>
                        <select
                            value={accessPolicyId}
                            onChange={(e) => setAccessPolicyId(e.target.value)}
                        >
                            <option value="" disabled>Select Policy</option>
                            {generatePolicyOptions()}
                        </select>
                    </label>

                    <label>
                        <span>Assets Selector</span>
                        <label>
                            <span>Attribute</span>
                            <input
                                placeholder="ex. id"
                                value={selector.operandLeft}
                                onChange={(e) =>
                                    setSelector((s) => {
                                        s.operandLeft = e.target.value;
                                    })
                                }
                            />
                        </label>
                        <label>
                            <span>Operand</span>
                            <select>
                                <option>EQ</option>
                            </select>
                            <span style={{ fontSize: "x-small" }}>
                                Currently only supports EQ
                            </span>
                        </label>
                        <label>
                            <span>Value</span>
                            <input
                                placeholder="ex. asset-id"
                                value={selector.operandRight}
                                onChange={(e) =>
                                    setSelector((s) => {
                                        s.operandRight = e.target.value;
                                    })
                                }
                            />
                        </label>
                        <span>Selector</span>
                        <span>
                            <b>
                                {(selector.operandLeft && selector.operandRight) ? `${selector.operandLeft} ${selector.operator} ${selector.operandRight}` : "all assets"}
                            </b>
                        </span>
                    </label>

                    <div className={styles["modal-buttons"]}>
                        <button onClick={() => setShowModal(false)}>
                            Cancel
                        </button>
                        <button
                            onClick={() => {
                                create();
                            }}
                        >
                            Create
                        </button>
                    </div>
                </div>
            </Modal>
        );
    }, [
        error,
        contractId,
        setContractId,
        contractPolicyId,
        setContractPolicyId,
        accessPolicyId,
        setAccessPolicyId,
        setShowModal,
        create,
        generatePolicyOptions,
        selector,
        setSelector
    ]);
    return (
        <>
            <SubLayout title="My Contracts">
                <div className={styles.container}>
                    <div className={styles["create-contract"]}>
                        <button
                            className={styles["create-contract-button"]}
                            onClick={show}
                        >
                            <Icon>add_circle</Icon> Create New Contract
                        </button>
                    </div>
                    <div className={styles["my-contracts"]}>
                        {contracts.map((e) => (
                            <ContractComponent
                                key={e["@id"]}
                                contract={e}
                                accessPolicy={policies.find(
                                    (p) => p["@id"] === e.accessPolicyId
                                )}
                                contractPolicy={policies.find(
                                    (p) => p["@id"] === e.contractPolicyId
                                )}
                            />
                        ))}
                    </div>
                </div>
                {showModal ? modal : ""}
            </SubLayout>
        </>
    );
}

type PolicyProps = {
    contract: EdcContractDefinition;
    accessPolicy: EdcPolicyDefinition | undefined;
    contractPolicy: EdcPolicyDefinition | undefined;
};

function ContractComponent({
    contract,
    accessPolicy,
    contractPolicy,
}: PolicyProps) {
    const accessP = useMemo(() => {
        if (!accessPolicy) return <>&nbsp;</>;
        return PolicyToString(accessPolicy.policy);
    }, [accessPolicy]);
    const contractP = useMemo(() => {
        if (!contractPolicy) return <>&nbsp;</>;
        return PolicyToString(contractPolicy.policy);
    }, [contractPolicy]);

    const selector = useMemo(() => {
        const s = contract.assetsSelector as AssetsSelector | AssetsSelector[];
        if(Array.isArray(s)) {
            if(s.length === 0) return "all assets"
            return s.map(e => `${e.operandLeft} ${e.operator} ${e.operandRight}`).join(" | ")
        } else {
            return `${s.operandLeft} ${s.operator} ${s.operandRight}`
        }
    }, [contract])

    return (
        <>
            <div className={"card " + styles.contract}>
                <h3>{contract["@id"]}</h3>
                <Fields>
                    <Attribute
                        icon="policy"
                        text="CONTRACT POLICY"
                        value={contractP}
                    />
                    <Attribute
                        icon="policy"
                        text="ACCESS POLICY"
                        value={accessP}
                    />
                </Fields>
                <Fields>
                    <Attribute
                        icon="check_box"
                        text="ASSET SELECTOR"
                        value={selector}
                    />
                </Fields>
            </div>
        </>
    );
}
