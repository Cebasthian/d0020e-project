import { OdrlConstraint, OdrlHasPolicy } from "../types/odrl";

export function PolicyToString(policy: OdrlHasPolicy): string | React.ReactNode;

export function PolicyToString(policy: OdrlHasPolicy, fallback: string): string;

export function PolicyToString(policy: OdrlHasPolicy, fallback?: string): string | React.ReactNode {
    const perm = policy.permission;

    const fall = fallback || (<>&nbsp;</>)

    if (!perm) return fall

    let constraints: OdrlConstraint | OdrlConstraint[];
    let constraint: OdrlConstraint;

    // IF-nesting hell
    if (Array.isArray(perm)) {
        if (perm.length > 0) {
            constraints = perm[0].constraint;
        } else {
            return fall
        }
    } else {
        constraints = perm.constraint;
    }

    if (Array.isArray(constraints)) {
        if (constraints.length > 0) {
            constraint = constraints[0];
        } else {
            return fall
        }
    } else {
        constraint = constraints;
    }

    let left = constraint.leftOperand;
    if (
        typeof constraint.leftOperand === "object" &&
        constraint.leftOperand["@id"]
    )
        left = constraint.leftOperand["@id"];

    let op = constraint.operator;
    if (
        typeof constraint.operator === "object" &&
        constraint.operator["@id"]
    )
        op = constraint.operator["@id"];

    let right = constraint.rightOperand;
    if (
        typeof constraint.rightOperand === "object" &&
        constraint.rightOperand["@id"]
    )
        right = constraint.rightOperand["@id"];

    return `${left} ${op.toLocaleUpperCase()} ${right}`;
}
