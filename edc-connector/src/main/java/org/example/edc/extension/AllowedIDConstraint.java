package org.example.edc.extension;

import org.eclipse.edc.connector.controlplane.catalog.spi.policy.CatalogPolicyContext;
import org.eclipse.edc.connector.controlplane.contract.spi.policy.ContractNegotiationPolicyContext;
import org.eclipse.edc.policy.engine.spi.AtomicConstraintRuleFunction;
import org.eclipse.edc.policy.model.Operator;
import org.eclipse.edc.policy.model.Permission;
import org.eclipse.edc.spi.monitor.Monitor;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AllowedIDConstraint implements AtomicConstraintRuleFunction<Permission, CatalogPolicyContext>{

    private final Monitor monitor;

    public AllowedIDConstraint(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public boolean evaluate(Operator operator, Object rightvalue, Permission permission, CatalogPolicyContext context){
        String ID = context.participantAgent().getIdentity();

        monitor.info(String.format("Identity Constraint: %s %s %s", ID, operator, rightvalue.toString()));

        if(operator.equals(Operator.EQ)) {
            if( rightvalue instanceof String val ) {
                boolean list = val.contains(",");
                if(list) {
                    return List.of(val.split(",")).contains(ID);
                }
            }
            return Objects.equals(ID, rightvalue);
        }

        return false;
    }
}