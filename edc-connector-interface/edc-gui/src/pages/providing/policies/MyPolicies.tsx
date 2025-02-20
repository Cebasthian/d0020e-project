import { useCallback, useMemo, useState } from "react";
import { useImmer } from "use-immer";
import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import Modal from "../../../components/modal/Modal";
import { EdcPolicyDefinition } from "../../../types/edc";
import { createPolicy } from "../../../util/edc_interface";
import { jsonLd } from "../../../util/json_ld";
import { PolicyToString } from "../../../util/lib";
import { useGetter } from "../../../util/useGetter";
import styles from "./my-policies.module.css";

type NewPermission = {
    left: string;
    operator: string;
    right: string;
};

const defaultNewPermission = (): NewPermission => ({
    left: "",
    operator: "odrl:eq",
    right: "",
});

export default function MyPolicies() {
    const { data: policies, refresh } = useGetter<EdcPolicyDefinition[]>(
        "/edc-provider/policies",
        []
    );

    const [showModal, setShowModal] = useState(false);

    const [policyId, setPolicyId] = useState("");
    const [perm, setPerm] = useImmer<NewPermission>(defaultNewPermission());

    const [error, setError] = useState("");

    const show = useCallback(() => {
        setShowModal(true);
        setPolicyId("");
        setPerm(defaultNewPermission());
        setError("")
    }, [setShowModal, setPerm, setPolicyId]);

    const create = useCallback(async () => {
        if (!policyId) {
            setError("Invalid policy id");
            return;
        }

        if (!perm.left || !perm.right) {
            await createPolicy(policyId, null);
            setShowModal(false);
            refresh();
        } else {
            await createPolicy(policyId, {
                "@type": "AtomicConstraint",
                leftOperand: perm.left,
                // operator: {
                //     "@id": perm.operator
                // },
                operator: perm.operator,
                rightOperand: perm.right,
            });
            setShowModal(false);
            refresh();
        }
    }, [policyId, perm, refresh]);

    const modal = useMemo(() => {
        return (
            <Modal setShow={setShowModal}>
                <div className={styles.modal}>
                    <h3>Create Policy</h3>
                    <span className={styles["modal-error"]}>{error}</span>
                    <label>
                        <span>Policy Id</span>
                        <input
                            required
                            placeholder="Policy Id *"
                            value={policyId}
                            onChange={(e) => setPolicyId(e.target.value)}
                        />
                    </label>
                    <label>
                        <span>Policy Enforcement</span>
                        <label>
                            <span>Attribute</span>
                            <input
                                placeholder="ex. identity"
                                value={perm.left}
                                onChange={(e) =>
                                    setPerm((p) => {
                                        p.left = e.target.value;
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
                                placeholder="ex. trusted"
                                value={perm.right}
                                onChange={(e) =>
                                    setPerm((p) => {
                                        p.right = e.target.value;
                                    })
                                }
                            />
                        </label>
                        <span>Policy</span>
                        <span>
                            <b>
                                {`${perm.left || "null"} ${jsonLd
                                    .splitNamespace(perm.operator)
                                    .toUpperCase()} ${perm.right || "null"}`}
                            </b>
                        </span>
                    </label>
                    <div className={styles["modal-buttons"]}>
                        <button onClick={() => setShowModal(false)}>
                            Cancel
                        </button>
                        <button onClick={() => create()}>Create</button>
                    </div>
                </div>
            </Modal>
        );
    }, [create, perm, error, setPerm, policyId]);

    return (
        <>
            <SubLayout title="My Policies">
                <div className={styles.container}>
                    <div className={styles["create-policy"]}>
                        <button
                            className={styles["create-policy-button"]}
                            onClick={show}
                        >
                            <Icon>add_circle</Icon> Create New Policy
                        </button>
                    </div>
                    <div className={styles["my-policies"]}>
                        {policies.map((e) => (
                            <PolicyComponent key={e["@id"]} policy={e} />
                        ))}
                    </div>
                </div>
                {showModal ? modal : ""}
            </SubLayout>
        </>
    );
}

type PolicyProps = {
    policy: EdcPolicyDefinition;
};

function PolicyComponent({ policy }: PolicyProps) {
    const policyText = useMemo(() => {
        return PolicyToString(policy.policy)

        // const perm = policy.policy.permission;
        // if (!perm) return "";

        // let constraints: OdrlConstraint | OdrlConstraint[];
        // let constraint: OdrlConstraint;

        // // IF-nesting hell
        // if (Array.isArray(perm)) {
        //     if (perm.length > 0) {
        //         constraints = perm[0].constraint;
        //     } else {
        //         return "";
        //     }
        // } else {
        //     constraints = perm.constraint;
        // }

        // if (Array.isArray(constraints)) {
        //     if (constraints.length > 0) {
        //         constraint = constraints[0];
        //     } else {
        //         return "";
        //     }
        // } else {
        //     constraint = constraints;
        // }

        // let left = constraint.leftOperand;
        // if (
        //     typeof constraint.leftOperand === "object" &&
        //     constraint.leftOperand["@id"]
        // )
        //     left = constraint.leftOperand["@id"];

        // let op = constraint.operator;
        // if (
        //     typeof constraint.operator === "object" &&
        //     constraint.operator["@id"]
        // )
        //     op = constraint.operator["@id"];

        // let right = constraint.rightOperand;
        // if (
        //     typeof constraint.rightOperand === "object" &&
        //     constraint.rightOperand["@id"]
        // )
        //     right = constraint.rightOperand["@id"];

        // return `${left} ${op.toLocaleUpperCase()} ${right}`;
    }, [policy]);

    return (
        <>
            <div className={"card " + styles.policy}>
                <h3>{policy["@id"]}</h3>
                <Fields>
                    <Attribute
                        icon="calendar_month"
                        text="CREATED ON"
                        value={new Date(policy.createdAt || 0).toISOString()}
                    />
                    <Attribute icon="policy" text="POLICY" value={policyText} />
                </Fields>
            </div>
        </>
    );
}
