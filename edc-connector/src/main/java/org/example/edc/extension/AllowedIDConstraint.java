package org.example.edc.extension;

import org.eclipse.edc.connector.controlplane.contract.spi.policy.ContractNegotiationPolicyContext;
import org.eclipse.edc.policy.engine.spi.AtomicConstraintRuleFunction;
import org.eclipse.edc.policy.model.Operator;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.spi.monitor.Monitor;

import java.util.Collection;

public class AllowedIDConstraint implements AtomicConstraintRuleFunction<Permission, ContractNegotiationPolicyContext>{

    private Monitor monitor;

    public AllowedIDConstraint(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public boolean evaluate(Operator operator, Object rightvalue, Permission permission, ContractNegotiationPolicyContext context){
        var ID = context.participantAgent().getClaims().get("id");
        
        return switch (operator){
            case IN -> ((Collection<?>) rightvalue).contains(ID);
            default -> false;
        };
    }
}